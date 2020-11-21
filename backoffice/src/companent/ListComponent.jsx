import React, {Component} from 'react';
import ProductService from "../services/ProductService";
import Table from "react-bootstrap/Table";

import {BrowserRouter as Router, Link} from 'react-router-dom';
import axios from 'axios';
import HeaderComponent from "./HeaderComponent";
import FooterComponent from "./FooterComponent";
import UserService from "../services/UserService";

class ListComponent extends Component {
    constructor(props) {
        super(props)
        this.state = {
            productslist: []
        }

        this.editProduct=this.editProduct.bind(this);
        this.deleteProduct=this.deleteProduct.bind(this);
    }
    componentDidMount() {


        ProductService.getProduct().then((res)=>{
            this.setState({ productslist:res.data});
        });


    }


    editProduct(id){
        this.props.history.push('/update-product/'+id);



    }
    deleteProduct(id){
        ProductService.deleteProduct(id).then(res =>{
            this.setState({productslist:this.state.productslist.filter(product => product.id !==id)})

        })

    }

    render() {
        return (
            <div>
                <HeaderComponent/>
                <div className="container">
                <h2 className="text-center">Urun Listesi</h2>
                <div className="row">



                </div>
                <div className="row">
                    <Table striped bordered hover >
                        <thead>
                        <tr>
                            <th>Urun Adi</th>
                            <th>Urun Icerigi</th>
                            <th>Urun Kategorisi</th>
                            <th>Urun Fiyati</th>
                            <th >Actions</th>
                        </tr>
                        </thead>
                        <tbody>
                        {
                            this.state.productslist.map(
                                product =>
                                    <tr key={product.id}>
                                        <td>{product.title}</td>
                                        <td>{product.description}</td>
                                        <td>{product.category}</td>
                                        <td>{product.price}</td>
                                        <td>
                                            <button  onClick={()=>this.editProduct(product.id)} className=" btn btn-info btn-sm ">Edit</button>
                                            <button style={{marginLeft: "10px"}} onClick={()=>this.deleteProduct(product.id)} className="btn btn-danger btn-sm">Sil</button>
                                        </td>
                                    </tr>
                            )
                        }
                        </tbody>
                    </Table>
                </div>

                </div>
                <FooterComponent/>
            </div>
        );

    }
}
export default ListComponent;