import React, {Component} from 'react';
import ProductService from "../services/ProductService";
import Table from "react-bootstrap/Table";

import {BrowserRouter as Router, Link} from 'react-router-dom';
import axios from 'axios';
import HeaderComponent from "./HeaderComponent";
import FooterComponent from "./FooterComponent";
import UserService from "../services/UserService";
import DropdownButton from 'react-bootstrap/DropdownButton'
import CategoryService from "../services/CategoryService";

class ListComponent extends Component {
    constructor(props) {
        super(props)
        this.state = {
            productslist: [],
            categorylist: []
        }

        this.editProduct=this.editProduct.bind(this);
        this.deleteProduct=this.deleteProduct.bind(this);
        this.ViewCategory=this.ViewCategory.bind(this);
    }
    componentDidMount() {


        ProductService.getProduct().then((res)=>{
            this.setState({ productslist:res.data});
        });
        CategoryService.getCategory().then((res)=>{
            this.setState({categorylist:res.data});
        });

        console.log(this.state.productslist)
    }


    editProduct(id){
        this.props.history.push('/update-product/'+id);



    }
    deleteProduct(id){
            ProductService.deleteProduct(id).then(res =>{
                this.setState({productslist:this.state.productslist.filter(product => product.id !==id)})

            })

    }
    viewProduct(id){
        this.props.history.push('/view-product/'+id);
    }
    ViewCategory=(category)=> {
        console.log(category)
        this.setState({
            productslist:this.state.productslist.filter(product => product.category==category)

        })
    }

    render() {
        return (
            <div>
                <HeaderComponent/>
                <Link to="/add-product">
                    <button className="btn btn-info addbutton">Ürün Ekle</button>

                </Link>
                <div className="dropdown show">
                    <a className="btn btn-secondary dropdown-toggle dropdown123" href="#" role="button" id="dropdownMenuLink"
                       data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        Kategoriler
                    </a>

                    <div className="dropdown-menu " aria-labelledby="dropdownMenuLink">
                        {
                            this.state.categorylist.map(
                                product =>
                                    <a className="dropdown-item" href={`/view-product-category/${product.id}`}>{product.name}</a>
                            )
                        }





                    </div>
                </div>
                <div className="container productlist">



                <h2 className="text-center ">Urun Listesi</h2>

                <div className="row">



                </div>
                <div className="row">
                    <Table striped bordered hover >
                        <thead>
                        <tr>
                            <th>Urun Adi</th>
                            <th>Urun Icerigi</th>
                            <th>Urun Kategorisi</th>
                            <th>Urun Resmi</th>
                            <th>Urun Fiyati</th>
                            <th className="actions123">Actions</th>
                        </tr>
                        </thead>
                        <tbody>
                        {
                            this.state.productslist.map(
                                product =>
                                    <tr key={product.id}>
                                        <td>{product.title}</td>
                                        <td>{product.description}</td>
                                        <td>

                                            <button className="btn btn-link" onClick={()=>this.ViewCategory(product.category)}>{product.category}</button>
                                        </td>
                                        <td>{product.urlToImage}</td>
                                        <td>{product.price}</td>
                                        <td>

                                            <button  onClick={()=>this.editProduct(product.id)} className=" btn btn-info">Güncelle</button>
                                            <button style={{marginLeft: "10px"}} onClick={()=>this.deleteProduct(product.id)} className="btn btn-danger">Sil</button>
                                            <button style={{marginLeft: "10px"}} onClick={()=>this.viewProduct(product.id)} className="btn btn-success">Görüntüle</button>
                                        </td>
                                    </tr>
                            )
                        }
                        </tbody>
                    </Table>
                </div>

                </div>

            </div>
        );

    }
}
export default ListComponent;