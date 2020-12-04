
import React, {useState, useEffect, Component} from 'react';
import FooterComponent from "../companent/FooterCompanent";
import HeaderComponent from "../companent/HeaderComponent";
import Table from 'react-bootstrap/Table'
import axios from 'axios';
import ProductService from "../services/ProductService";
import nextId from "react-id-generator";
import {Link} from "react-router-dom";
import "../companent/App2.css";
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
                id: 0,
                selectedtable:'',
            },

        }

    }
    componentDidMount() {
        ProductService.getCategory().then((res) => {
            this.setState({productslist: res.data});
        });

    }
    onClickSidebar =(category) => {

    axios.get("http://localhost:8080/category/product/id/" + category.id,{
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
        }
        else{
            this.setState({
                cart:{
                    cartId: nextId(),
                    id:product.id,
                    title:product.title,
                    price:Number(product.price),
                    piece:1,
                    total:Number(product.price),
                    selectedtable:sessionStorage.getItem("product")
                }
            },()=>this.setState({salelist:[...this.state.salelist,this.state.cart]}))
        }

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
        console.log(this.state.salelist+"bu saledir")

        ProductService.pay(this.state.salelist).then(res=>{
            this.props.history.push('/homepage')
        });
        sessionStorage.setItem("product","Secili masa yok")

    }

    render() {
        return (
            <div>
                <HeaderComponent/>

                <Link to="/homepage">
                    <button className="btn btn-info backbutton fas fa-edit" ></button>
                </Link>
                <h3 className="tablelable2">{sessionStorage.getItem("product")}</h3>
                <Table bordered className="tableclass" >
                    <tbody>
                    <th>
                        <div className="categorytable">
                        <tr>
                            <div className="headertable">
                        <label>Kategori</label>
                            </div>
                        </tr>
                        <tr>

                                {this.state.productslist.map(
                                    category => {
                                        return (

                                                <div>




                                                <button className="btn btn-info categorybutton"onClick={() =>
                                                    this.onClickSidebar(category)}>{category.name}</button>
                                                </div>

                                        )
                                    })}

                        </tr>
                        </div>
                    </th>





                    <th className="cardtable">
                        <tr>
                            <div className="carheadertable">
                                <label>Ürün Listesi</label>
                            </div>
                        </tr>

                        <div className="card2for">
                                {

                                    this.state.categorylist.map(
                                        productl => {
                                            return (

                                                <div className="col-md-6 mb-4 ca">
                                                <div className="card cardbodytable">
                                                    <div className="card-header">
                                                        <h4 className="d-inline">{productl.title}</h4>
                                                    </div>
                                                    <div className="card-body">
                                                        <p className="card-text">Ürün Açıklaması : {productl.description}</p>
                                                        <p className="card-text">Ürün Kategorisi : {productl.category}</p>
                                                        <p className="card-text">Ürün Fiyatı : {productl.price} TL</p>
                                                        <button className="btn btn-success"onClick={()=>this.addProduct(productl)}>Sepete Ekle</button>
                                                    </div>
                                                    </div>

                                            </div>)
                                        }

                                    )}
                        </div>
                    </th>





                    <th className="baskettable">

                        <tr>
                            <div className="basketheader">
                          <lable >Sepet</lable>
                            </div>
                        </tr>
                        <Table>
                            <tbody>

                            <tr>
                                <div className="basketbody">
                                {
                                    this.state.salelist.map(
                                        v=> {
                                            return(

                                                <tr key={v.cartId}>

                                                    <th>
                                                        <button className=" btn btn-info  " onClick={()=>this.addPrice(v)}>+</button>
                                                    </th>
                                                    <th>
                                                        <label htmlFor="name">x{v.piece}</label>
                                                    </th>
                                                    <th>
                                                        <div className="baskettitle">
                                                        <label htmlFor="name">{v.title}</label>
                                                        </div>
                                                    </th>

                                                    <th>
                                                        <div className="basketprice">
                                                        <label htmlFor="name">{v.total} TL</label>
                                                        </div>
                                                    </th>
                                                    <th>
                                                        <button
                                                            style={{marginLeft: "10px"}} className="btn btn-danger "onClick={()=>this.minusPrice(v)}>-</button>

                                                    </th>

                                                </tr>

                                            )
                                        })
                                }
                                </div>
                            </tr>
                            <tr>
                                <div className="totalpaymentcss">

                                <th>
                                    <div className="totalpaycss">
                                    <label htmlFor="name">Toplam Tutar</label>
                                    </div>
                                </th>
                                <th>
                                    <tr>
                                        <div className="baskettotalprice">
                                        <label htmlFor="name">{this.state.shoppinglistprice} ₺</label>
                                        </div>
                                    </tr>
                                </th>
                                    <th>
                                        <div className="baskettotalbutton">
                                        <button className="btn btn-info btn-block paybutton"onClick={()=>this.pay(this.state.salelist)}>Ode</button>
                                        </div>
                                    </th>
                                </div>
                            </tr>

                            </tbody>
                        </Table>


                    </th>
                    </tbody>
                </Table>

            </div>
        );
    }
}

export default ProductCompanent;