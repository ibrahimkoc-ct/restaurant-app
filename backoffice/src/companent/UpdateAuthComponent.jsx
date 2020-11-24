import React, {Component} from 'react';
import UserService from "../services/UserService";
import HeaderComponent from "./HeaderComponent";
import FooterComponent from "./FooterComponent";

class UpdateAuthComponent extends Component {
    constructor(props) {
        super(props);
        this.state = {
            username:'',
            authority:'',
            role_message:'Rol Seciniz',
        }
        this.chargeUsernameHandler=this.chargeUsernameHandler.bind(this);
        this.updateAuth=this.updateAuth.bind(this);
    }
    updateAuth = (e) =>{
        e.preventDefault()
        let auth={username: this.state.username,authority: this.state.authority};
        UserService.updateAuth(auth).then(res =>{

            console.log('user=>'+JSON.stringify(auth));
            this.props.history.push('/auth-table');
        })

    }
    cancel(){
        this.props.history.push('/auth-table');
    }
    chargeUsernameHandler =(event) =>{
        this.setState({username:event.target.value});
    }
    onClickAdminItem =()=>{
        this.setState({authority:"ROLE_ADMIN",
            role_message:"ROLE_ADMIN",});
    }
    onClickUserItem =()=>{
        this.setState({authority:"ROLE_USER",
            role_message:"ROLE_USER",});
    }
    render() {
        return (
            <div>
                <HeaderComponent/>
                <div className="container">
                    <div className="row">
                        <div className="card col-md-6 offset-md-3 offset-md-3">
                            <h3 className="text-center">Yetkinlik Düzenle</h3>
                            <div className="card-body">
                                <form>
                                    <div className="form-group">
                                        <label>Kullanıcı Adı</label>
                                        <input placeholder="Degiştirilmez." name="username" className="form-control"
                                               value={this.state.username} onChange={this.chargeUsernameHandler}
                                        />

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
                                    <button className="btn btn-success" onClick={this.updateAuth}>Guncelle</button>
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

export default UpdateAuthComponent;
