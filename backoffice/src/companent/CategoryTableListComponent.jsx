import React, {Component} from 'react';
import CategoryService from "../services/CategoryService";
import CategoryTable from "../services/CategoryTable";
import HeaderComponent from "./HeaderComponent";
import {Link} from "react-router-dom";
import Table from "react-bootstrap/Table";
import FooterComponent from "./FooterComponent";

class CategoryTableListComponent extends Component {
    constructor(props) {
        super(props)
        this.state = {
            categorylist: []
        }

    }
    componentDidMount() {
        CategoryTable.getCategory().then((res) => {
            this.setState({categorylist: res.data});
        });
    }
    deleteCategory(id) {
        CategoryTable.deleteCategory(id).then(res => {
            this.setState({categorylist: this.state.categorylist.filter(product => product.id !== id)})

        })

    }
    editCategory(user){
        this.props.history.push('/update-categorytable/'+user.id);

    }
    viewCategory(id){
        this.props.history.push('/view-categorytable/'+id);
        sessionStorage.setItem("view-categorytable",id)
    }

    render() {
        return (
            <div>
                    <HeaderComponent/>
                    <Link to="/add-categorytable">
                        <button className="btn btn-info addbutton">Kategori Ekle</button>
                    </Link>
                    <div className="container productlist">
                        <h2 className="text-center">Masa Kategorileri</h2>
                        <div className="row">
                        </div>
                        <div className="row">
                            <Table striped bordered hover >
                                <thead>
                                <tr>

                                    <th>Kategori Id</th>
                                    <th>Kategori Adı</th>
                                    <th>Kategori Bilgileri</th>
                                    <th>Fotograf</th>
                                    <th>Butonlar</th>
                                </tr>
                                </thead>
                                <tbody>
                                {
                                    this.state.categorylist.map(
                                        user =>
                                            <tr >
                                                <td>{user.id}</td>
                                                <td>{user.name}</td>
                                                <td>{user.description}</td>
                                                <td>{user.imageToUrl}</td>

                                                <td>

                                                    <button  onClick={()=>this.editCategory(user)} className=" btn btn-info  ">Güncelle</button>
                                                    <button style={{marginLeft: "10px"}} onClick={()=>this.deleteCategory(user.id)} className="btn btn-danger">Sil</button>
                                                    <button style={{marginLeft: "10px"}}  onClick={()=>this.viewCategory(user.id)} className="btn btn-success">Görüntüle</button>
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

export default CategoryTableListComponent;