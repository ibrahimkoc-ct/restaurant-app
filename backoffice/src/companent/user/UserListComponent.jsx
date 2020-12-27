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
        }).catch(this.setState({loading:false}));

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

    viewUser(user) {
        this.props.history.push({
            pathname: `view-user/{user.id}`,
            state: {
                user: user
            }
        });
    }
    userListForm =()=>{
        if(!this.state.userlist){
            return <h2>Bir hata oluştu. Lütfen daha sonra tekrar deneyiniz</h2>
        }
        return(
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
                                                onClick={() => this.viewUser(user)}
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
        )
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
                    {this.userListForm()}
                </div>
                {this.state.loading ? <FullPageLoading/> : null}
            </div>


        );
    }
}

export default UserListComponent;