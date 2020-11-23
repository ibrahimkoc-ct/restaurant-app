import React, {Component} from 'react';
import LoginHeaderComponent from "./LoginHeaderComponent";
import FooterComponent from "./FooterCompanent";
import UserService from "../services/UserService";
import './App2.css';
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
    }

    signIn = (e) => {
        e.preventDefault()
        if(this.state.userslist.filter(name => (name.username === this.state.username)&& (name.password.substring(6,name.password.size) === this.state.password)).length>0){

            sessionStorage.setItem("token", 'Basic ' + btoa(this.state.username + ':' + this.state.password))
        sessionStorage.setItem("key",this.state.username)
        this.props.history.push('/homepage');

    }
        else{
            this.setState({label:"Kullanıcı adı veya şifre yanlış"})
        }
    }

    render() {
        return (
           <div>
                <LoginHeaderComponent/>
                   <div className="login">
                <div className="card col-md-6 offset-md-3 offset-md-3 " >
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

            </div>
               </div>



        );
    }
}

export default LoginComponent;