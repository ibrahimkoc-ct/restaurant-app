
import React, {useState, useEffect, Component} from 'react';
import FooterComponent from "./FooterCompanent";
import HeaderComponent from "./HeaderComponent";
import Table from 'react-bootstrap/Table'
import axios from 'axios';
import ProductService from "../services/ProductService";
import nextId from "react-id-generator";
class ProductCompanent extends Component {
    constructor(props) {
        super(props)
        this.state = {
            productslist: [],
            categorylist:[],
            salelist:[],
            shoppinglistprice:0,
            cart:{
                cartId: 0,
                piece: 1,
                price: 0,
                title:'',
                total: 0,
                id: 0
            },

        }

    }
    componentDidMount() {
        ProductService.getCategory().then((res) => {
            this.setState({productslist: res.data});
        });

    }
    onClickSidebar =(category) => {

    axios.get("http://localhost:8080/client/product/" + category,{
    headers: {
        Authorization: sessionStorage.getItem("token")

    }}).then((res)=>{
        this.setState({categorylist: res.data});
            console.log(res.data)
        });
    }
    addProduct =(product)=>{
        this.state.shoppinglistprice+=Number(product.price)
        if(this.state.salelist.filter(cart=>cart.id==product.id).length>0){
            var cart=this.state.salelist.filter(cart=>cart.id==product.id)
            cart[0].piece+=1;
            cart[0].total=cart[0].total+cart[0].price;
            this.setState([{...this.state.salelist,[cart[0].id]:cart[0]}])
            console.log(cart)
        }
        else{
            this.setState({
                cart:{
                    cartId: nextId(),
                    id:product.id,
                    title:product.title,
                    price:Number(product.price),
                    piece:1,
                    total:Number(product.price)
                }
            },()=>this.setState({salelist:[...this.state.salelist,this.state.cart]}))
        }
        console.log(product.id)
    }
    addPrice =(product)=>{
        product.piece+=1;
        product.total=product.total+Number(product.price);
        this.state.shoppinglistprice+=Number(product.price);
        this.setState([{...this.state.salelist,[product.cartId]:product}])


    }
    minusPrice(product) {
        product.piece-=1;
        product.total =product.total-Number(product.price);
        this.state.shoppinglistprice-=Number(product.price);
        if(product.piece==0){
            this.setState(
                {salelist : this.state.salelist.filter(salelist =>salelist.cartId !== product.cartId)});
        }
        else{
            this.setState([{...this.state.salelist,[product.cartId]:product}])
        }

    }
    pay() {
        console.log(this.state.salelist)
        ProductService.pay(this.state.salelist).then(res=>{

        });
    }

    render() {
        return (
            <div>
                <HeaderComponent/>
                <Table bordered >
                    <tbody>
                    <th>
                        <div className="Sidebar">
                            <ul className="SidebarList">

                                {this.state.productslist.map(
                                    product => {
                                        return (
                                            <li
                                                className="row"
                                                id={window.location.pathname == product ? "active" : ""}
                                                onClick={() =>
                                                    this.onClickSidebar(product)}
                                            >
                                                <div>{product}</div>
                                            </li>

                                        )
                                    })}

                            </ul>

                        </div>
                    </th>
                    <th>
                        <div className="App">
                            {

                                this.state.categorylist.map(
                                    productl => {
                                        return (<div className="col-md-6 mb-4">
                                            <div className="card">
                                                <div className="card-header">
                                                    <h4 className="d-inline">{productl.title}</h4>
                                                </div>
                                                <div className="card-body">
                                                    <p className="card-text">Ürün Açıklaması : {productl.description}</p>
                                                    <p className="card-text">Ürün Kategorisi : {productl.category}</p>
                                                    <p className="card-text">Ürün Fiyatı : {productl.price}</p>
                                                    <button className="btn btn-success"onClick={()=>this.addProduct(productl)}>Sepete Ekle</button>
                                                </div>
                                            </div>
                                        </div>)
                                    }

                                )}
                        </div>
                    </th>
                    <th>
                        <tr>
                            <h3>Sepet</h3>
                        </tr>
                        <Table bordered >
                            <tbody>

                            <tr>

                                {



                                    this.state.salelist.map(
                                        v=> {
                                            return(
                                                <tr key={v.cartId}>
                                                    <th>
                                                        <button className=" btn btn-info btn-sm " onClick={()=>this.addPrice(v)}>+</button>
                                                    </th>
                                                    <th>
                                                        <label htmlFor="name">x{v.piece}</label>
                                                    </th>
                                                    <th>
                                                        <label htmlFor="name">{v.title}</label>
                                                    </th>
                                                    <th>
                                                        <label htmlFor="name">{v.total} TL</label>
                                                    </th>
                                                    <th>
                                                        <button style={{marginLeft: "10px"}} className="btn btn-danger btn-sm"onClick={()=>this.minusPrice(v)}>-</button>
                                                    </th>
                                                </tr>
                                            )
                                        })
                                }

                            </tr>
                            <tr>
                                <th>
                                    <label htmlFor="name">Toplam Tutar</label>
                                </th>
                                <th>
                                    <tr>
                                        <label htmlFor="name">{this.state.shoppinglistprice} ₺</label>

                                    </tr>
                                </th>
                            </tr>

                            </tbody>
                        </Table>
                        <button className="btn btn-info btn-block"onClick={()=>this.pay(this.state.salelist)}>Ode</button>

                    </th>
                    </tbody>
                </Table>

            </div>
        );
    }
}

export default ProductCompanent;