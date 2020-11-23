import React, {Component} from 'react';
import UserService from "../services/UserService";
import HeaderComponent from "./HeaderComponent";
import FooterComponent from "./FooterComponent";

class UpdateUserComponent extends Component {
    constructor(props) {
        super(props);
        this.state ={
            username:'',
            password:'',
            enabled:''
        }
        this.chargeUsernameHandler=this.chargeUsernameHandler.bind(this);
        this.chargePasswordHandler=this.chargePasswordHandler.bind(this);
        this.chargeEnabledHandler=this.chargeEnabledHandler.bind(this);
        this.updateUser=this.updateUser.bind(this);

    }
    updateUser = (e) =>{
        e.preventDefault()
        let user={username: this.state.username,password: this.state.password,enabled: this.state.enabled};
        UserService.updateUser(user).then(res =>{

            console.log('user=>'+JSON.stringify(user));
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
    chargeEnabledHandler =(event) =>{
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
                                        <label>Aktiflik</label>
                                        <input placeholder="enable" name="enable" className="form-control"
                                               value={this.state.enabled} onChange={this.chargeEnabledHandler}/>
                                    </div>
                                    <button className="btn btn-success" onClick={this.updateUser}>Guncelle</button>
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

export default UpdateUserComponent;