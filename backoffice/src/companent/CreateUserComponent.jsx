import React, {Component} from 'react';
import UserService from '../services/UserService';
import HeaderComponent from "./HeaderComponent";
import FooterComponent from "./FooterComponent";
import {BrowserRouter as Router} from "react-router-dom";

class CreateUserComponent extends Component {
    constructor(props) {
        super(props);
        this.state ={
            username:'',
            password:'',
            enabled:'',
            authority:'',
        }
        this.chargeUsernameHandler=this.chargeUsernameHandler.bind(this);
        this.chargePasswordHandler=this.chargePasswordHandler.bind(this);
        this.chargeRoleHandler=this.chargeRoleHandler.bind(this);
        this.saveUser=this.saveUser.bind(this);
        this.chargeauthHandler=this.chargeauthHandler.bind(this);

    }
    saveUser = (e) =>{
        e.preventDefault()
        let user={username: this.state.username,password:"{noop}"+ this.state.password,enabled: this.state.enabled};
        let auth={username: this.state.username,authority: this.state.authority};
        UserService.createAuth(auth).then(res =>{

        })

        UserService.createUser(user).then(res =>{
            this.props.history.push('/user-table');
        })
    }

    cancel(){
        this.props.history.push('/user-table');
    }
    chargeUsernameHandler =(event) =>{
        this.setState({username:event.target.value});
    }
    chargePasswordHandler =(event) =>{
        this.setState({password:event.target.value});
    }
    chargeRoleHandler =(event) =>{
        this.setState({authority:event.target.value});
    }
    chargeauthHandler =(event) =>{
        this.setState({enabled:event.target.value});

    }


    render() {
        return (
            <div>
                <HeaderComponent/>
                <div className="container">
                    <div className="row">
                        <div className="card col-md-6 offset-md-3 offset-md-3">
                            <h3 className="text-center">Kullanıcı Ekle</h3>
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

                                    </div>
                                    <div className="form-group">
                                        <label>Rol</label>
                                        <input placeholder="rol" name="role" className="form-control"
                                               value={this.state.authority} onChange={this.chargeRoleHandler}/>
                                    </div>
                                    <div className="form-group">
                                        <label>Aktiflik</label>
                                        <input type ="text" placeholder="atkiflik" name="enable" className="form-control"
                                               value={this.state.enabled} onChange={this.chargeauthHandler}/>
                                    </div>
                                    <button className="btn btn-success" onClick={this.saveUser}>Kaydet</button>
                                    <button className="btn btn-danger" onClick={this.cancel.bind(this)} style={{marginLeft:"10px"}}>Iptal</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
                <FooterComponent/>
            </div>
        );
    }
}

export default CreateUserComponent;