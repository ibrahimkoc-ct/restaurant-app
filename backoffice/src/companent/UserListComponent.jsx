import React, {Component} from 'react';
import Table from "react-bootstrap/Table";
import UserService from "../services/UserService";
import ProductService from "../services/ProductService";
import HeaderComponent from "./HeaderComponent";
import FooterComponent from "./FooterComponent";
import {BrowserRouter as Router, Link} from "react-router-dom";
import createBrowserHistory from 'history/createBrowserHistory';
import BackofficeContext from "../BackofficeContext";
const history = createBrowserHistory({forceRefresh:true});

class UserListComponent extends Component {
    static contextType = BackofficeContext;
    constructor(props) {
        super(props)
        this.state = {
            userlist: [],
            token:''
        }

        this.editUser=this.editUser.bind(this);
        this.deleteUser=this.deleteUser.bind(this);
    }
    componentDidMount(){

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

        UserService.getUser(this.state.token).then((res)=>{
            this.setState({ userlist:res.data});
        });

    }
    deleteUser(username){
        UserService.deleteAuth(username,this.state.token);
        UserService.deleteUser(username,this.state.token).then(res =>{
            this.setState({userlist:this.state.userlist.filter(product => product.username !==username)})
        })


    }
    editUser(id){
        this.props.history.push('/update-user/'+id);
        console.log(id)

    }
    viewUser(id){
        this.props.history.push('/view-user/'+id);
        sessionStorage.setItem("wiew",id)
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
                    <Table striped bordered hover >
                        <thead>
                        <tr>
                            <th>Kullanıcı Adı</th>
                            <th>Parola</th>
                            <th>Aktif</th>
                            <th >Actions</th>
                        </tr>
                        </thead>
                        <tbody>
                        {
                            this.state.userlist.map(
                                user =>
                                    <tr >
                                        <td>{user.username}</td>
                                        <td className="pass">{user.password}</td>
                                        <td>{user.enabled.toString()}</td>
                                        <td>
                                            <button  onClick={()=>this.editUser(user.username)} className=" btn btn-info  ">Güncelle</button>
                                            <button style={{marginLeft: "10px"}} onClick={()=>this.viewUser(user.username)} className="btn btn-success">Görüntüle</button>
                                            <button style={{marginLeft: "10px"}} onClick={()=>this.deleteUser(user.username)} className="btn btn-danger ">Sil</button>
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

export default UserListComponent;