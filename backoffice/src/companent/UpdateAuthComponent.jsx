import React, {Component} from 'react';
import UserService from "../services/UserService";
import HeaderComponent from "./HeaderComponent";
import FooterComponent from "./FooterComponent";

class UpdateAuthComponent extends Component {
    constructor(props) {
        super(props);
        this.state = {
            username:'',
            authority:''
        }
        this.chargeUsernameHandler=this.chargeUsernameHandler.bind(this);
        this.chargeAuhtoritydHandler=this.chargeAuhtoritydHandler.bind(this);
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
    chargeAuhtoritydHandler =(event) =>{
        this.setState({authority:event.target.value});
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
                                    <div className="form-group">
                                        <label>Yetkinlik</label>
                                        <input type ="text" placeholder="Yetkinlik" name="password" className="form-control"
                                               value={this.state.authority} onChange={this.chargeAuhtoritydHandler}/>
                                    </div>
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
