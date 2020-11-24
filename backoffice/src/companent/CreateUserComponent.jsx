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
            role_message:'Rol Seciniz',
            enabled_message:'Atkiflik Seciniz',
        }
        this.chargeUsernameHandler=this.chargeUsernameHandler.bind(this);
        this.chargePasswordHandler=this.chargePasswordHandler.bind(this);
        this.saveUser=this.saveUser.bind(this);


    }
    saveUser = (e) =>{
        e.preventDefault()
        let user={username: this.state.username,password:"{noop}"+ this.state.password,enabled: this.state.enabled};
        let auth={username: this.state.username,authority: this.state.authority};
        console.log(user);
        console.log(auth)
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

    onClickAdminItem =()=>{
        this.setState({authority:"ROLE_ADMIN",
            role_message:"ROLE_ADMIN",});
    }
    onClickUserItem =()=>{
        this.setState({authority:"ROLE_USER",
            role_message:"ROLE_USER",});
    }
    onClickTrueItem=()=>{
        this.setState({enabled_message:"TRUE",
            enabled:"true"});

    }
    onClickFalseItem=()=>{
        this.setState({enabled_message:"FALSE",
            enabled:"false"});

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
                                    <div className="dropdown show">
                                        <a className="btn btn-secondary btn-block dropdown-toggle" href="#" role="button" id="dropdownMenuLink"
                                           data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                            {this.state.role_message}
                                        </a>

                                        <div className="dropdown-menu btn-block" aria-labelledby="dropdownMenuLink">
                                            <a className="dropdown-item" onClick={this.onClickAdminItem.bind(this)}>ROLE_ADMIN</a>
                                            <a className="dropdown-item" onClick={this.onClickUserItem.bind(this)}>ROLE_USER</a>

                                        </div>
                                    </div>
                                    <hr/>
                                    <div className="dropdown show">
                                        <a className="btn btn-secondary btn-block dropdown-toggle" href="#" role="button" id="dropdownMenuLink"
                                           data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                            {this.state.enabled_message}
                                        </a>

                                        <div className="dropdown-menu btn-block" aria-labelledby="dropdownMenuLink">
                                            <a className="dropdown-item" onClick={this.onClickTrueItem.bind(this)}>TRUE</a>
                                            <a className="dropdown-item" onClick={this.onClickFalseItem.bind(this)}>FALSE</a>

                                        </div>
                                    </div>
                                    <hr/>




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