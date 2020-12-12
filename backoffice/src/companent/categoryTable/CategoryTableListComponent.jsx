import React, {Component} from 'react';
import CategoryService from "../../services/CategoryService";
import CategoryTable from "../../services/CategoryTable";
import HeaderComponent from "../homepage/HeaderComponent";
import {Link} from "react-router-dom";
import Table from "react-bootstrap/Table";
import BackofficeContext from "../../BackofficeContext";
import createBrowserHistory from 'history/createBrowserHistory';
import FullPageLoading from "../loading/FullPageLoading";

const history = createBrowserHistory({forceRefresh:true});

class CategoryTableListComponent extends Component {
    static contextType = BackofficeContext;
    constructor(props) {
        super(props)
        this.state = {
            categorylist: [],
            token:'',
            loading:false
        }

    }
    componentDidMount() {
        this.setState({loading: true})
        const userToken = this.context;
        if(localStorage.getItem("token")==null){
            if(userToken.token.length>0){
                this.state.token=userToken.token;

                console.log(this.state.token)
            }
            else{
                history.push('/');
            }
        }
        else {
            this.state.token=localStorage.getItem("token")
        }

        CategoryTable.getCategory(this.state.token).then((res) => {
            this.setState({categorylist: res.data,loading:false});
        });
    }
    deleteCategory(id) {
        this.setState({loading: true})
        CategoryTable.deleteCategory(id,this.state.token).then(res => {
            this.setState({categorylist: this.state.categorylist.filter(product => product.id !== id),loading:false})

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
                                    <th>Masa Sayısı</th>
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
                                                <td>{user.tableAmount}</td>
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
                {
                    this.state.loading ? <FullPageLoading/> : null
                }

            </div>
        );
    }
}

export default CategoryTableListComponent;