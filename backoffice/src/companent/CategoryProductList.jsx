import React, {Component} from 'react';
import UserService from "../services/UserService";
import CategoryService from "../services/CategoryService";
import HeaderComponent from "./HeaderComponent";
import {Link} from "react-router-dom";
import Table from "react-bootstrap/Table";
import FooterComponent from "./FooterComponent";

class CategoryProductList extends Component {
    constructor(props) {
        super(props)
        this.state = {
            id:this.props.match.params.id,
            productlist: [],
            categorylist:[]
        }


    }
    componentDidMount(){
        CategoryService.getCategoryProductId(this.state.id).then((res)=>{
            this.setState({ productlist:res.data});
        });
        CategoryService.viewCategory(this.state.id).then((res)=>{
            this.setState({categorylist:res.data});
        });

    }
    render() {
        return (
            <div>
                <HeaderComponent/>
                <div className="container productlist">

                    <h2 className="text-center ">{this.state.categorylist.name} Kategorisi</h2>

                    <div className="row">

                    </div>
                    <div className="row">
                        <Table striped bordered hover >
                            <thead>
                            <tr>
                                <th>Urun Adi</th>
                                <th>Urun Icerigi</th>
                                <th>Urun Fiyati</th>

                            </tr>
                            </thead>
                            <tbody>
                            {
                                this.state.productlist.map(
                                    product =>
                                        <tr key={product.id}>
                                            <td>{product.title}</td>
                                            <td>{product.description}</td>
                                            <td>{product.price}</td>
                                            <td>


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

export default CategoryProductList;