import React, {Component} from 'react';
import ProductService from "../services/ProductService";
import RestaurantTableService from "../services/RestaurantTableService";
import HeaderComponent from "./HeaderComponent";
import {Link, Route} from "react-router-dom";
import Table from "react-bootstrap/Table";
import FooterComponent from "./FooterComponent";
import CategoryTable from "../services/CategoryTable";
import ViewCategoryTable from "./ViewCategoryTable";

class RestaurantTableListComponent extends Component {
    constructor(props) {
        super(props)
        this.state = {
            tablelist: [],

        }
        this.ViewCategory=this.ViewCategory.bind(this);
    }
    componentDidMount() {


        RestaurantTableService.getRestaurantTable().then((res) => {
            this.setState({tablelist: res.data});
        });
    }
    deleteTable(id) {
        RestaurantTableService.deleteRestaurantTable(id).then(res => {
            this.setState({tablelist: this.state.tablelist.filter(product => product.id !== id)})

        })

    }
    editTable(table){
        this.props.history.push('/update-restaurantable/'+table.id);


    }
    viewTable(table){
        this.props.history.push('/view-table/'+table.id);

    }
    ViewCategory=(category)=> {
        console.log(category)
        this.setState({
            tablelist:this.state.tablelist.filter(product => product.category==category)

        })
    }

        render() {
        return (
            <div>
                <HeaderComponent/>
                <Link to="/add-table">
                    <button className="btn btn-info addbutton">Masa Ekle</button>

                </Link>
                <div className="container productlist">



                    <h2 className="text-center ">Masa Listesi</h2>

                    <div className="row">



                    </div>
                    <div className="row">
                        <Table striped bordered hover >
                            <thead>
                            <tr>
                                <th>Masa Adi</th>
                                <th>Masa Açıklaması</th>
                                <th>Masa Kategorisi</th>
                                <th className="actions123">Actions</th>
                            </tr>
                            </thead>
                            <tbody>
                            {
                                this.state.tablelist.map(
                                    product =>
                                        <tr key={product.id}>
                                            <td>{product.title}</td>
                                            <td>{product.description}</td>
                                            <td>
                                                <button className="btn btn-link" onClick={()=>this.ViewCategory(product.category)}>{product.category}</button>
                                            </td>
                                            <button  onClick={()=>this.editTable(product)} className=" btn btn-info  ">Güncelle</button>
                                            <button style={{marginLeft: "10px"}} onClick={()=>this.deleteTable(product.id)} className="btn btn-danger">Sil</button>
                                            <button style={{marginLeft: "10px"}}  onClick={()=>this.viewTable(product)} className="btn btn-success">Görüntüle</button>
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

export default RestaurantTableListComponent;