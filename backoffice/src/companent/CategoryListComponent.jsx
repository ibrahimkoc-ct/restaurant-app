import React, {Component} from 'react';
import UserService from "../services/UserService";
import CategoryService from "../services/CategoryService";
import HeaderComponent from "./HeaderComponent";
import Table from "react-bootstrap/Table";
import FooterComponent from "./FooterComponent";
import {Link} from "react-router-dom";
import ProductService from "../services/ProductService";
import BackofficeContext from "../BackofficeContext";

class CategoryListComponent extends Component {
    static contextType = BackofficeContext;
    constructor(props) {
        super(props)
        this.state = {
            categorylist: []
        }

    }
    componentDidMount() {
        const token = this.context;
        console.log(token.token)

        CategoryService.getCategory(token.token).then((res) => {
            this.setState({categorylist: res.data});
        });
    }
    deleteCategory(id) {

        console.log(id)
        CategoryService.deleteCategory(id).then(res => {
            this.setState({categorylist: this.state.categorylist.filter(product => product.id !== id)})

        })
    }
    editCategory(user){
        this.props.history.push('/update-category/'+user.id);

    }
    viewCategory(id){
        this.props.history.push('/view-category/'+id);
        sessionStorage.setItem("view-category",id)

    }


        render() {
        return (
            <div>
                <HeaderComponent/>
                <Link to="/add-category">
                    <button className="btn btn-info addbutton">Kategori Ekle</button>
                </Link>
                <div className="container productlist">
                    <h2 className="text-center">Kategoriler</h2>
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


            </div>
        );
    }
}

export default CategoryListComponent;