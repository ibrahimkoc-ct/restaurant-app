import React, {useState, useEffect, Component} from 'react';
import FooterComponent from "./FooterCompanent";
import HeaderComponent from "./HeaderComponent";
import Table from 'react-bootstrap/Table'
import axios from 'axios';
import ProductService from "../services/ProductService";
import nextId from "react-id-generator";
import {Link} from "react-router-dom";
import "./App2.css";

class ResponsiveProduct extends Component {
    constructor(props) {
        super(props)
        this.state = {
            productslist: [],
            categorylist: [],
            salelist: [],
            shoppinglistprice: 0,

            cart: {
                cartId: 0,
                piece: 1,
                price: 0,
                title: '',
                total: 0,
                id: 0,
                selectedtable: '',
            },

        }

    }

    componentDidMount() {
        ProductService.getCategory().then((res) => {
            this.setState({productslist: res.data});
        });

    }

    onClickSidebar = (category) => {

        axios.get("http://localhost:8080/category/product/id/" + category.id, {
            headers: {
                Authorization: sessionStorage.getItem("token")

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
                    selectedtable: sessionStorage.getItem("product")
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
        console.log(this.state.salelist + "bu saledir")

        ProductService.pay(this.state.salelist).then(res => {
            this.props.history.push('/homepage')
        });
        sessionStorage.setItem("product", "Secili masa yok")

    }

    render() {
        return (
            <div>
                <HeaderComponent/>
                <Link to="/homepage">
                    <button className="btn btn-info backbutton fas fa-edit"></button>
                </Link>
                <h3 className="tablelable2">{sessionStorage.getItem("product")}</h3>
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
                                                    <div className="col-xl-5 col-lg-5">
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
                                            onClick={() => this.pay(this.state.salelist)}>Ode
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