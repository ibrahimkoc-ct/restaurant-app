import React, {Component} from 'react';
import HeaderComponent from "./HeaderComponent";
import ProductService from "../services/ProductService";
import nextId from "react-id-generator";
import {Link} from "react-router-dom";
import "./App2.css";
import ClientContext from "../ClientContext";
import createBrowserHistory from 'history/createBrowserHistory';
import FullPageLoading from './FullPageLoading'

const history = createBrowserHistory({forceRefresh: true});

class ResponsiveProduct extends Component {
    static contextType = ClientContext;

    constructor(props) {
        super(props)
        this.state = {
            productslist: [],
            categorylist: [],
            salelist: [],
            shoppinglistprice: 0,
            paymentText: '',
            cart: {
                cartId: 0,
                piece: 1,
                price: 0,
                title: '',
                total: 0,
                id: 0,
                selectedtable: '',
                waiterName: '',
                productId:0

            },
            hasNext: true,
            waiterName: '',
            token: '',
            loading: false,
            scrollTop: 0,
            CategoryId: 0,
            pageNum: 0,

        }
        this.myRef = React.createRef()

    }

    onScroll = () => {
        const scrollTop = this.myRef.current.scrollTop
        this.setState({
            scrollTop: scrollTop
        })

        if (scrollTop > (this.state.pageNum+1)*700) {
            if (this.state.hasNext.toString() === "false") {
                this.state.pageNum+=1;
                ProductService.getProduct(this.state.token, this.state.CategoryId, this.state.pageNum).then((res) => {
                    this.setState({hasNext: res.data.last,
                        categorylist:[...this.state.categorylist,...res.data.content]})

                })

            }


        }
    }

    async componentDidMount() {
        this.setState({loading: true})
        const userToken = this.context;
        if (localStorage.getItem("token") == null) {
            if (userToken.token.length > 0) {
                this.state.token = userToken.token;
            } else {
                history.push('/');
            }
        } else {
            this.state.token = localStorage.getItem("token")
            const {waiter, setWaiter} = this.context
        }
        await ProductService.getCategory(this.state.token).then((res) => {
            this.setState({productslist: res.data,CategoryId:1});
        });
        this.onClickSidebar(1)
        let orders = ResponsiveProduct.getOrderFromStorage();
        let table = [];
        for (let i = 0; i < orders.length; i++) {
            if (orders[i][0].selectedtable.indexOf(localStorage.getItem("product")) !== -1) {
                for (let j = 0; j < orders[i].length; j++) {

                    table.push(orders[i][j])
                    this.state.shoppinglistprice += (orders[i][j].piece * orders[i][j].price);

                }
                orders.splice(i, 1);
            }

        }
        await this.setState({salelist: table, loading: false});
        localStorage.setItem("orders", JSON.stringify(orders));
    }

    onClickSidebar = (id) => {
        this.setState({loading: true, hasNext: 0, CategoryId: id,pageNum:0})
        ProductService.getProduct(this.state.token,id, 0).then((res) => {
            this.setState({categorylist: res.data.content, hasNext: res.data.last, loading: false})
            this.myRef.current.scrollTop=0;
        });

    }
    addProduct = (product) => {
        this.setState({loading: true})
        this.state.shoppinglistprice += Number(product.price)

        if (this.state.salelist.filter(cart => cart.id == product.id).length > 0) {
            var cart = this.state.salelist.filter(cart => cart.id == product.id)
            cart[0].piece += 1;

            cart[0].total = cart[0].total + cart[0].price;
            this.setState([{...this.state.salelist, [cart[0].id]: cart[0]}])
            this.setState({loading: false})
        } else {
            this.setState({
                cart: {
                    cartId: nextId(),
                    id: Number(product.id),
                    productId: Number(product.id),
                    title: product.title,
                    price: Number(product.price),
                    piece: 1,
                    total: Number(product.price),
                    selectedtable: localStorage.getItem("product"),
                    waiterName: localStorage.getItem("waiter")
                }
            }, () => this.setState({salelist: [...this.state.salelist, this.state.cart], loading: false}))
        }

    }
    addPrice = (product) => {
        product.piece += 1;
        product.total = product.total + Number(product.price);
        this.state.shoppinglistprice += Number(product.price);
        this.setState([{...this.state.salelist, [product.cartId]: product}])


    }

    minusPrice(product) {
        product.piece -= 1;
        product.total = product.total - Number(product.price);
        this.state.shoppinglistprice -= Number(product.price);
        if (product.piece == 0) {
            this.setState(
                {salelist: this.state.salelist.filter(salelist => salelist.cartId !== product.cartId)});
        } else {
            this.setState([{...this.state.salelist, [product.cartId]: product}])
        }

    }

    pay() {
        this.setState({loading: true})

        ProductService.pay(this.state.salelist, this.state.token).then(res => {
            this.setState({loading: false})
            this.props.history.push('/homepage')
        });

        localStorage.setItem("product", "Secili Masa Yok");
        const {waiter, setWaiter} = this.context
        setWaiter("Seçili Garson Yok")
        localStorage.setItem("waiter", "Seçili Garson Yok")

    }


    onClickExit() {

        if (this.state.salelist.length > 0) {
            let orders = ResponsiveProduct.getOrderFromStorage();
            orders.push(this.state.salelist);
            localStorage.setItem("orders", JSON.stringify(orders));
        }

        localStorage.setItem("product", "Secili Masa Yok");
        const {waiter, setWaiter} = this.context
        setWaiter("Seçili Garson Yok")
        localStorage.setItem("waiter", "Seçili Garson Yok")

        this.props.history.push('/homepage')

    }

    static getOrderFromStorage() {
        let orders;

        if (localStorage.getItem("orders") === null) {
            orders = [];
        } else {
            orders = JSON.parse(localStorage.getItem("orders"));
        }

        return orders;
    }

    render() {
        const {
            scrollTop
        } = this.state
        return (
            <div>
                <HeaderComponent/>
                <Link to="/homepage">
                    <button className="btn btn-info backbutton fas fa-edit buttonhome" onClick={() =>
                        this.onClickExit()}></button>
                </Link>
                <h3 className="tablelable2">{localStorage.getItem("product")}</h3>
                <div className="container-fluid">
                    <div className="row mt-4">
                        <div className="col-xl-2 col-lg-2 text-center my-3">
                            <h4>Kategoriler</h4>
                        </div>
                        <div className="col-xl-7 col-lg-7 text-center my-3">
                            <h4>Ürün Listesi</h4>
                        </div>
                        <div className="col-xl-3 col-lg-3 text-center my-3">
                            <h4>Sepet</h4>
                        </div>

                    </div>
                    <div className="row border productbody">
                        <div className="col-xl-2 col-lg-2 text-center border categoryBody">
                            {this.state.productslist.map(
                                category => {
                                    return (

                                        <div key={category.name}>
                                            <button className="btn btn-secondary buttoncategory"
                                                    onClick={() =>
                                                        this.onClickSidebar(category.id)}>

                                                {category.name} <br/>
                                                <img src={'data:image/png;base64,' + category.mediaDTO.fileContent}
                                                     width="120" style={{margin: 1}}/>

                                            </button>
                                        </div>

                                    )
                                })}
                        </div>

                        <div className="col-xl-7 col-lg-7 text-center newproduct" ref={this.myRef}
                             onScroll={this.onScroll}>
                            <div className="row">

                                {
                                    this.state.categorylist.map(
                                        productl => {
                                            return (
                                                <div key={productl.id} className="col-md-5 mb-4 ml-5">
                                                    <div className="card cardbodytable">
                                                        <div className="card-header">

                                                            <img src={'data:image/png;base64,' + productl.mediaDTO.fileContent}
                                                                width="100"/>
                                                        </div>
                                                        <div className="card-body">
                                                            <h5 className="d-inline">{productl.title}</h5>
                                                            <p className="card-text">Ürün Açıklaması
                                                                : {productl.description}</p>
                                                            <p className="card-text">Ürün Kategorisi
                                                                : {productl.category}</p>
                                                            <p className="card-text">Ürün Fiyatı
                                                                : {productl.price} TL</p>
                                                            <button className="btn btn-success"
                                                                    onClick={() => this.addProduct(productl)}>Sepete
                                                                Ekle
                                                            </button>
                                                        </div>
                                                    </div>

                                                </div>)
                                        }
                                    )}


                            </div>

                        </div>
                        <div className="col-xl-3 col-lg-3 text-center border newproduct">
                            {
                                this.state.salelist.map(
                                    v => {
                                        return (
                                            <div className="row  mt-3" key={v.cartId}>

                                                <div className="col-xl-2 col-lg-2">
                                                    <button className=" btn btn-info btn-sm "
                                                            onClick={() => this.addPrice(v)}>+
                                                    </button>
                                                </div>
                                                <div className="col-xl-1 col-lg-1">
                                                    <h5 htmlFor="name">x{v.piece}</h5>
                                                </div>
                                                <div className="col-xl-5 col-lg-5 text-left">
                                                    <h5 htmlFor="name">{v.title}</h5>
                                                </div>
                                                <div className="col-xl-2 col-lg-2">
                                                    <h6 htmlFor="name">{v.total} TL</h6>

                                                </div>
                                                <div className="col-xl-2 col-lg-2">
                                                    <button
                                                        className="btn btn-danger btn-sm"
                                                        onClick={() => this.minusPrice(v)}>-
                                                    </button>
                                                </div>


                                            </div>


                                        )
                                    })}

                        </div>


                    </div>


                    <div className="row">
                        <div className="col-xl-3 col-lg-3 text-center  ml-auto my-3">
                            <div className="row">
                                <div className="col xl-4 col-lg-4 text-center ">
                                    <h5>Toplam Tutar</h5>
                                </div>
                                <div className="col xl-3 col-lg-3 text-center ">
                                    <label htmlFor="name">{this.state.shoppinglistprice} ₺</label></div>
                                <div className="col xl-5 col-lg-5 text-center ">
                                    <button className="btn btn-info btn-block paybutton"
                                            onClick={() => this.pay(this.state.salelist)}>Öde
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>


                </div>
                {this.state.loading ? <FullPageLoading/> : null}
            </div>
        );
    }
}

export default ResponsiveProduct;