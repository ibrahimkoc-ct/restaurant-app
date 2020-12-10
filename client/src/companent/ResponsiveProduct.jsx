import React, {useState, useEffect, Component} from 'react';
import FooterComponent from "./FooterCompanent";
import HeaderComponent from "./HeaderComponent";
import Table from 'react-bootstrap/Table'
import axios from 'axios';
import ProductService from "../services/ProductService";
import nextId from "react-id-generator";
import {Link} from "react-router-dom";
import "./App2.css";
import ClientContext from "../ClientContext";
import createBrowserHistory from 'history/createBrowserHistory';
const history = createBrowserHistory({forceRefresh:true});

class ResponsiveProduct extends Component {
    static contextType=ClientContext;
    constructor(props) {
        super(props)
        this.state = {
            productslist: [],
            categorylist: [],
            salelist: [],
            shoppinglistprice: 0,
            paymentText:'',
            cart: {
                cartId: 0,
                piece: 1,
                price: 0,
                title: '',
                total: 0,
                id: 0,
                selectedtable: '',
                waiterName:''

            },
            waiterName:'',

        }

    }

    componentDidMount() {
        const userToken = this.context;
        if(localStorage.getItem("token")==null){
            if(userToken.token.length>0){
                this.state.token=userToken.token;

                console.log(this.state.token)
            }
            else{
                history.push('/');
            }
        }

        else {
            this.state.token=localStorage.getItem("token")


        }

        const{waiter}=this.context
        this.state.waiterName=waiter;

        ProductService.getCategory(this.state.token).then((res) => {
            this.setState({productslist: res.data});
        });
        axios.get("http://localhost:8080/category/product/id/" + 1, {
            headers: {
                Authorization: sessionStorage.getItem("token")

            }
        }).then((res) => {


        });

        let orders = ResponsiveProduct.getOrderFromStorage();
        let table=[];
        for(let i=0; i<orders.length; i++){
            if(orders[i][0].selectedtable.indexOf(localStorage.getItem("product"))!==-1){
                for(let j=0; j<orders[i].length; j++){

                    table.push(orders[i][j])
                   this.state.shoppinglistprice+=(orders[i][j].piece*orders[i][j].price);


                }
                orders.splice(i,1);
            }

        }
        this.setState({salelist: table});
        localStorage.setItem("orders", JSON.stringify(orders));



    }

    onClickSidebar = (category) => {

        axios.get("http://localhost:8080/category/product/id/" + category.id, {
            headers: {
                Authorization: this.state.token

            }
        }).then((res) => {
            this.setState({categorylist: res.data});
            console.log(res.data)
        });

    }
    addProduct = (product) => {
        this.state.shoppinglistprice += Number(product.price)

        if (this.state.salelist.filter(cart => cart.id == product.id).length > 0) {
            var cart = this.state.salelist.filter(cart => cart.id == product.id)
            cart[0].piece += 1;

            cart[0].total = cart[0].total + cart[0].price;
            this.setState([{...this.state.salelist, [cart[0].id]: cart[0]}])
        } else {
            this.setState({
                cart: {
                    cartId: nextId(),
                    id: product.id,
                    title: product.title,
                    price: Number(product.price),
                    piece: 1,
                    total: Number(product.price),
                    selectedtable: localStorage.getItem("product"),
                    waiterName:this.state.waiterName
                }
            }, () => this.setState({salelist: [...this.state.salelist, this.state.cart]}))
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

            ProductService.pay(this.state.salelist,this.state.token).then(res => {
                this.props.history.push('/homepage')
            });



           localStorage.setItem("product", "Secili Masa Yok");

         const{waiter,setWaiter}=this.context
         setWaiter("Seçili Garson Yok")



    }

    onClickExit(){


        if(this.state.salelist.length>0){
            let orders=ResponsiveProduct.getOrderFromStorage();
            orders.push(this.state.salelist);
            localStorage.setItem("orders",JSON.stringify(orders));
        }

        localStorage.setItem("product", "Secili Masa Yok");
        const{waiter,setWaiter}=this.context
        setWaiter("Seçili Garson Yok")

        this.props.history.push('/homepage')

    }
    static getOrderFromStorage(){
        let orders;

        if(localStorage.getItem("orders") === null){
            orders = [];
        }else{
            orders = JSON.parse(localStorage.getItem("orders"));
        }

        return orders;
    }


    render() {
        return (
            <div>
                <HeaderComponent/>
                <Link to="/homepage">
                    <button className="btn btn-info backbutton fas fa-edit buttonhome" onClick={()=>
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
                        <div className="col-xl-2 col-lg-2 text-center border">
                            {this.state.productslist.map(
                                category => {
                                    return (

                                        <div>


                                            <button className="btn btn-secondary mt-3 btn-xl-6 buttoncategory"
                                                    onClick={() =>
                                                        this.onClickSidebar(category)}>{category.name}</button>
                                        </div>

                                    )
                                })}

                        </div>
                        <div className="col-xl-7 col-lg-7 text-center newproduct ">
                            <div className="row">

                                {
                                    this.state.categorylist.map(
                                        productl => {
                                            return (
                                                <div className="col-md-5 mb-4 ml-5">
                                                    <div className="card cardbodytable">
                                                        <div className="card-header">
                                                            <img src={productl.urlToImage} className="card-img-top" alt="..."/>
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
                                                    <div className="col-xl-2 col-lg-2" ><button
                                                        className="btn btn-danger btn-sm"
                                                        onClick={() => this.minusPrice(v)}>-
                                                    </button></div>





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
            </div>
        );
    }
}

export default ResponsiveProduct;