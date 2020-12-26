import React, {Component} from 'react';
import UserService from "../../services/UserService";
import HeaderComponent from "../homepage/HeaderComponent";
import Table from "react-bootstrap/Table";
import BackofficeContext from "../../BackofficeContext";
import createBrowserHistory from 'history/createBrowserHistory';
import {Link} from "react-router-dom";
import FullPageLoading from "../loading/FullPageLoading";

const history = createBrowserHistory({forceRefresh: true});

class AuthListComponent extends Component {
    static contextType = BackofficeContext;

    constructor(props) {
        super(props)
        this.state = {
            authlist: [],
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


        UserService.getAuth(this.state.token).then((res) => {
            this.setState({authlist: res.data, loading: false});
        });

    }

    editAuth(id) {
        this.props.history.push('/update-auth/' + id);


    }

    viewAuth(role) {
        this.props.history.push({
            pathname:`view-auth/{role.id}`,
            state:{
                role:role
            }
        });

    }

    deleteRole(id) {
        this.setState({loading: true})
        UserService.deleteAuth(id, this.state.token)
        this.setState({authlist: this.state.authlist.filter(auth => auth.id !== id), loading: false})
    }

    render() {
        return (
            <div>
                <HeaderComponent/>
                <Link to="/role-add">
                    <button className="btn btn-info addbutton">Yetki Ekle</button>
                </Link>
                <div className="container productlist">
                    <h2 className="text-center">Yetkiler</h2>
                    <div className="row">
                    </div>
                    <div className="row">
                        <Table striped bordered hover>
                            <thead>
                            <tr>


                                <th>Rol Id</th>
                                <th>Rol Adı</th>
                                <th>Actions</th>
                            </tr>
                            </thead>
                            <tbody>
                            {
                                this.state.authlist.map(
                                    role =>
                                        <tr key={role.id}>
                                            <td>{role.id}</td>
                                            <td>{role.name}</td>
                                            <td>

                                                <button onClick={() => this.editAuth(role.id)}
                                                        className=" btn btn-info  ">Güncelle
                                                </button>
                                                <button style={{marginLeft: "10px"}}
                                                        onClick={() => this.viewAuth(role)}
                                                        className="btn btn-success">Görüntüle
                                                </button>
                                                <button className="btn btn-danger" style={{marginLeft: "10px"}}
                                                        onClick={() => this.deleteRole(role.id)}>Sil
                                                </button>

                                            </td>
                                        </tr>
                                )
                            }
                            </tbody>
                        </Table>
                    </div>

                </div>
                {this.state.loading ? <FullPageLoading/> : null}
            </div>
        );
    }
}

export default AuthListComponent;