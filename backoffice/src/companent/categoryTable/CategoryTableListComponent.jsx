import React, {Component} from 'react';
import CategoryTable from "../../services/CategoryTable";
import HeaderComponent from "../homepage/HeaderComponent";
import {Link} from "react-router-dom";
import Table from "react-bootstrap/Table";
import BackofficeContext from "../../BackofficeContext";
import createBrowserHistory from 'history/createBrowserHistory';
import FullPageLoading from "../loading/FullPageLoading";

const history = createBrowserHistory({forceRefresh: true});

class CategoryTableListComponent extends Component {
    static contextType = BackofficeContext;

    constructor(props) {
        super(props)
        this.state = {
            categorylist: [],
            token: '',
            loading: false
        }

    }

    componentDidMount() {
        this.setState({loading: true})
        const userToken = this.context;
        if (localStorage.getItem("token") == null) {
            if (userToken.token.length > 0) {
                this.state.token = userToken.token;
            } else {
                history.push('/');
            }
        } else {
            this.state.token = localStorage.getItem("token")
        }

        CategoryTable.getCategory(this.state.token).then((res) => {
            this.setState({categorylist: res.data, loading: false})
        }).catch(this.setState({loading:false}));
    }

    deleteCategory(id) {
        this.setState({loading: true})
        CategoryTable.deleteCategory(id, this.state.token).then(res => {
            this.setState({categorylist: this.state.categorylist.filter(product => product.id !== id), loading: false})
        })

    }

    editCategory(categoryList) {
        this.props.history.push({
            pathname: `update-categorytable/{categoryList.id}`,
            state: {
                categoryList: categoryList
            }
        });
    }

    viewCategory(categoryList) {
        this.props.history.push({
            pathname: `view-categorytable/{categoryList.id}`,
            state: {
                categoryList: categoryList
            }
        });
    }

    categoryTable = () => {
        if (!this.state.categorylist) {
            return(<h3>Masa Kategorisi verisi yok! Lütfen masa kategorisi yükleyin.</h3>)
        }
        return (
            <div className="row">
                <Table striped bordered hover>
                    <thead>
                    <tr>

                        <th>Kategori Id</th>
                        <th>Kategori Adı</th>
                        <th>Kategori Bilgileri</th>
                        <th>Masa Resmi</th>
                        <th>Masa Sayısı</th>
                        <th>Butonlar</th>
                    </tr>
                    </thead>
                    <tbody>
                    {
                        this.state.categorylist.map(
                            categoryList =>
                                <tr key={categoryList.id}>
                                    <td>{categoryList.id}</td>
                                    <td>{categoryList.name}</td>
                                    <td>{categoryList.description}</td>
                                    <td align="center"><img
                                        src={'data:image/png;base64,' + categoryList.mediaDTO.fileContent}
                                        width="100"/></td>
                                    <td>{categoryList.tableAmount}</td>
                                    <td>
                                        <button onClick={() => this.editCategory(categoryList)}
                                                className=" btn btn-info  ">Güncelle
                                        </button>
                                        <button style={{marginLeft: "10px"}}
                                                onClick={() => this.deleteCategory(categoryList.id)}
                                                className="btn btn-danger">Sil
                                        </button>
                                        <button style={{marginLeft: "10px"}}
                                                onClick={() => this.viewCategory(categoryList)}
                                                className="btn btn-success">Görüntüle
                                        </button>
                                    </td>
                                </tr>
                        )
                    }
                    </tbody>
                </Table>
            </div>
        )
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
                    {this.categoryTable()}
                </div>
                {
                    this.state.loading ? <FullPageLoading/> : null
                }
            </div>
        );
    }
}

export default CategoryTableListComponent;