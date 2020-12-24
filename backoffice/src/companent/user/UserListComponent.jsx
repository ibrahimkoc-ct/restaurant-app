import React, {Component} from 'react';
import Table from "react-bootstrap/Table";
import UserService from "../../services/UserService";
import HeaderComponent from "../homepage/HeaderComponent";
import {Link} from "react-router-dom";
import createBrowserHistory from 'history/createBrowserHistory';
import BackofficeContext from "../../BackofficeContext";
import FullPageLoading from "../loading/FullPageLoading";

const history = createBrowserHistory({forceRefresh: true});

class UserListComponent extends Component {
    static contextType = BackofficeContext;

    constructor(props) {
        super(props)
        this.state = {
            userlist: [],
            token: '',
            loading: false
        }

        this.editUser = this.editUser.bind(this);
        this.deleteUser = this.deleteUser.bind(this);
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

        UserService.getUser(this.state.token).then((res) => {
            this.setState({userlist: res.data, loading: false});
        });

    }

    deleteUser(id) {
        this.setState({loading: true})
        UserService.deleteUser(id, this.state.token).then(res => {
            this.setState({userlist: this.state.userlist.filter(user => user.id !== id), loading: false})
        })
    }

    editUser(id) {
        this.props.history.push('/update-user/' + id);

    }

    viewUser(id) {
        this.props.history.push('/view-user/' + id);
        sessionStorage.setItem("wiew", id)
    }

    render() {
        return (
            <div>
                <HeaderComponent/>
                <Link to="/add-users">
                    <button className="btn btn-info addbutton">Kullanıcı Ekle</button>
                </Link>
                <div className="container productlist">
                    <h2 className="text-center">Kullanıcılar</h2>
                    <div className="row">
                    </div>
                    <div className="row">
                        <Table striped bordered hover>
                            <thead>
                            <tr>
                                <th>Kullanıcı Adı</th>
                                <th>Parola</th>
                                <th>Aktif</th>
                                <th>Roller</th>
                                <th>Actions</th>
                            </tr>
                            </thead>
                            <tbody>
                            {
                                this.state.userlist.map(
                                    user =>
                                        <tr key={user.id}>
                                            <td>{user.username}</td>
                                            <td className="pass">{user.password}</td>
                                            <td>{user.enabled.toString()}</td>
                                            <td>{
                                                user.roles.map(
                                                    roles=>
                                                    <li>{roles.name}</li>
                                                )
                                            }</td>
                                            <td>
                                                <button onClick={() => this.editUser(user.id)}
                                                        className=" btn btn-info  ">Güncelle
                                                </button>
                                                <button style={{marginLeft: "10px"}}
                                                        onClick={() => this.viewUser(user.id)}
                                                        className="btn btn-success">Görüntüle
                                                </button>
                                                <button style={{marginLeft: "10px"}}
                                                        onClick={() => this.deleteUser(user.id)}
                                                        className="btn btn-danger ">Sil
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

export default UserListComponent;