import React, {Component} from 'react';
import UserService from "../services/UserService";
import LoginHeaderComponent from "./LoginHeaderComponent";
import FooterComponent from "./FooterComponent";
import ProductService from "../services/ProductService";
import {Link} from "react-router-dom";
import {BrowserRouter as Router,Route,Switch} from 'react-router-dom';
import axios from "axios";
class LoginComponent extends Component {
    constructor(props) {
        super(props);
        this.state = {
            id: this.props.match.params.id,
            username: '',
            password: '',
            userslist: []
        }
        this.chargeUsernameHandler=this.chargeUsernameHandler.bind(this);
        this.chargePasswordHandler=this.chargePasswordHandler.bind(this);
    }
    chargeUsernameHandler =(event) =>{
        this.setState({username:event.target.value});

    }
    chargePasswordHandler =(event) =>{
        this.setState({password:event.target.value});
    }
    componentDidMount() {
        UserService.getList().then((res)=>{
            this.setState({ userslist:res.data});
        });
    }
    findUser(user) {
       return  user.isim === this.state.username;
    }
    signIn = (e) => {
        e.preventDefault()
        let us = {username: this.state.username, password: this.state.password};




        sessionStorage.setItem("token", 'Basic ' + btoa(this.state.username + ':' + this.state.password))
                this.props.history.push('/user-table');
                window.alert("Giriş Başarılı Hoşgeldiniz: " + this.state.username)
            }






    render() {
        return (
                <div className="Footer">
                    <LoginHeaderComponent/>


                        <div className="card col-md-6 offset-md-3 offset-md-3 " >
                            <h3 className="text-center">Kullanıcı Girişi</h3>
                            <div className="card-body">
                                <form>
                                    <div className="form-group">
                                        <label>Kullanıcı Adı</label>
                                        <input placeholder="Kullanıcı Adı" name="username" className="form-control"
                                               value={this.state.username} onChange={this.chargeUsernameHandler}/>
                                    </div>
                                    <div className="form-group">
                                        <label>Parola</label>
                                        <input type ="password" placeholder="Parola" name="password" className="form-control"
                                               value={this.state.password} onChange={this.chargePasswordHandler}/>
                                               <hr/>
                                    <button className="btn btn-success" onClick={this.signIn} >Giriş Yap</button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    <FooterComponent/>
                    </div>


        );
    }
}

export default LoginComponent;