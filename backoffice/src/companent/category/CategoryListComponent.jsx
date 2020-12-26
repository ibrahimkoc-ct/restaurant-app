import React, {Component} from 'react';
import CategoryService from "../../services/CategoryService";
import HeaderComponent from "../homepage/HeaderComponent";
import Table from "react-bootstrap/Table";
import {Link} from "react-router-dom";
import BackofficeContext from "../../BackofficeContext";
import FullPageLoading from "../loading/FullPageLoading";
import {createBrowserHistory} from 'history';

const history = createBrowserHistory();

class CategoryListComponent extends Component {
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


        CategoryService.getCategory(this.state.token).then((res) => {
            this.setState({categorylist: res.data, loading: false});
        });
    }

    deleteCategory(id) {

        this.setState({loading: true})
        CategoryService.deleteCategory(id, this.state.token).then(res => {
            this.setState({categorylist: this.state.categorylist.filter(product => product.id !== id), loading: false})

        })
    }

    editCategory(user) {
        this.props.history.push('/update-category/' + user.id);

    }

    viewCategory(category) {
        this.props.history.push({
            pathname: `view-category/{category.id}`,
            state: {
                category: category
            }
        });
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
                        <Table striped bordered hover>
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
                                    category =>
                                        <tr key={category.id}>
                                            <td>{category.id}</td>
                                            <td>{category.name}</td>
                                            <td>{category.description}</td>
                                            <td align="center"><img
                                                src={'data:image/png;base64,' + category.mediaDTO.fileContent}
                                                width="100"/></td>

                                            <td>

                                                <button onClick={() => this.editCategory(category)}
                                                        className=" btn btn-info  ">Güncelle
                                                </button>
                                                <button style={{marginLeft: "10px"}}
                                                        onClick={() => this.deleteCategory(category.id)}
                                                        className="btn btn-danger">Sil
                                                </button>
                                                <button style={{marginLeft: "10px"}}
                                                        onClick={() => this.viewCategory(category)}
                                                        className="btn btn-success">Görüntüle
                                                </button>
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

export default CategoryListComponent;