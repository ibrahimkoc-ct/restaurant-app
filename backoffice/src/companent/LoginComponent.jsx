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
            userslist: [],
            label:''
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
        UserService.oneAuth();
        UserService.oneUser();

    }

    signIn = (e) => {
        e.preventDefault()
        let us = {username: this.state.username, password: +this.state.password};

      if(this.state.userslist.filter(name => (name.username === this.state.username)&& (name.password.substring(6,name.password.size) === this.state.password)).length>0) {
            sessionStorage.setItem("token", 'Basic ' + btoa(this.state.username + ':' + this.state.password))
            sessionStorage.setItem("key", this.state.username)
            this.props.history.push('/user-table');


       }

           else{
           this.props.history.push('/');
               this.setState({label:"Kullanıcı adı veya şifre yanlış"})
       }
        }



    render() {
        return (
                    <div>
                    <LoginHeaderComponent/>

                    <div className="login">
                        <div className="card col-md-4 offset-md-4 offset-md-4  " >
                            <h3 className="text-center kullanicigiris">Kullanıcı Girişi</h3>
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
                                    <button className="btn btn-success btn-girisyap" onClick={this.signIn} >Giriş Yap</button>
                                    </div>
                                </form>
                                <div className="card-body text-center">
                                    <h3>{this.state.label}</h3>
                                </div>
                            </div>
                        </div>
                    <FooterComponent/>
                    </div>
                    </div>

        );
    }
}

export default LoginComponent;