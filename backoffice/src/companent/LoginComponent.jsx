import React, {Component} from 'react';
import UserService from "../services/UserService";

class LoginComponent extends Component {
    constructor(props) {
        super(props);
        this.state = {
            id: this.props.match.params.id,
            username: '',
            password: ''
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

    render() {
        return (
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

                                    <button className="btn btn-success" >Giriş Yap</button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
        );
    }
}

export default LoginComponent;