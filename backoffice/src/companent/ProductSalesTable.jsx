
import React, {Component} from 'react';
import ProductService from "../services/ProductService";
import Table from "react-bootstrap/Table";
import HeaderComponent from "./HeaderComponent";
import FooterComponent from "./FooterComponent";
import {BrowserRouter as Router} from "react-router-dom";
class ProductSalesTable extends Component {
    constructor(props) {
        super(props);
        this.state = {
            ProductSales: []
        }
    }
    componentDidMount(){
        ProductService.getProductSales().then((res)=>{
            this.setState({ ProductSales:res.data});
        });

    }


    render() {
        return (
            <div>
                <HeaderComponent/>
                <div className="container productlist">
                <h2 className="text-center">Urun Satış Tablosu</h2>
                <div className="row">
                </div>
                <div className="row">
                    <Table striped bordered hover >
                        <thead>
                        <tr>
                            <th>Siparis Numarası</th>
                            <th>Siparis Tarihi</th>
                            <th>Urun Adi</th>
                            <th>Urun ID</th>
                            <th>Urun Adeti</th>
                            <th>Urun Fiyatı</th>
                            <th>Toplam Fiyat</th>
                        </tr>
                        </thead>
                        <tbody>
                            {
                                this.state.ProductSales.map(
                                    product =>
                                        <tr key={product.orderId}>
                                            <td>{product.orderId}</td>
                                            <td>{product.createDate}</td>
                                            <td>{product.title}</td>
                                            <td>{product.id}</td>
                                            <td>{product.piece}</td>
                                            <td>{product.price}</td>
                                            <td>{product.price*product.piece}</td>

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

export default ProductSalesTable;