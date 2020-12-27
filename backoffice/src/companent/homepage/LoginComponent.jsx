import React, {Component} from 'react';
import UserService from "../../services/UserService";
import LoginHeaderComponent from "./LoginHeaderComponent";

import BackofficeContext from '../../BackofficeContext';
import FullPageLoading from "../loading/FullPageLoading";


class LoginComponent extends Component {
    static contextType = BackofficeContext;

    constructor(props) {
        super(props);
        this.state = {
            id: this.props.match.params.id,
            username: '',
            password: '',
            userslist: [],
            label: '',
            loginCheck: "false",
            checkTrue: false,
            token: '',
            loading: false
        }
    }
    changeInput = (e) => {
        this.setState({
            [e.target.name]: e.target.value
        })
    }
    chargeCheckHandler = (event) => {
        this.setState({checkTrue: !this.state.checkTrue})
    }

    componentDidMount() {
        if (localStorage.getItem("token") !== null) {
            this.props.history.push('/user-table');
        }
    }


    signIn = (e) => {
        this.state.token = 'Basic ' + btoa(this.state.username + ':' + this.state.password)
        this.setState({loading: true})
        UserService.getLogin(this.state.token).then(() => {

            if (this.state.checkTrue === true) {
                localStorage.setItem("token", 'Basic ' + btoa(this.state.username + ':' + this.state.password))
                localStorage.setItem("username", this.state.username);
            }
            const {setToken} = this.context
            setToken('Basic ' + btoa(this.state.username + ':' + this.state.password))
            const {setUsername} = this.context
            setUsername(this.state.username)
            this.props.history.push('/user-table');
        }).catch(() => {
            this.state.username = '';
            this.state.password = '';
            this.setState({
                label: "Kullanıcı adı veya şifre yanlış",
            })
        })
        if (this.state.username.length > 0) {
            this.setState({loading: false})
        }
        e.preventDefault();
    }
    loginForm =()=>{
        return(
            <div className="card col-md-4 offset-md-4 offset-md-4  ">
                <h3 className="text-center kullanicigiris">Kullanıcı Girişi</h3>
                <div className="card-body">
                    <form>
                        <div className="form-group">
                            <label>Kullanıcı Adı</label>
                            <input placeholder="Kullanıcı Adı" name="username"
                                   className="form-control"
                                   value={this.state.username}
                                   onChange={this.changeInput}/>
                        </div>
                        <div className="form-group">
                            <label>Parola</label>
                            <input type="password" placeholder="Parola" name="password"
                                   className="form-control"
                                   value={this.state.password}
                                   onChange={this.changeInput}/>
                            <div className="form-group form-check ">
                                <input type="checkbox" className="form-check-input" id="exampleCheck1"
                                       onChange={this.chargeCheckHandler}/>
                                <p>Beni Hatırla</p>
                            </div>
                            <button className="btn btn-success btn-girisyap"
                                    onClick={this.signIn}>Giriş Yap
                            </button>
                        </div>
                    </form>
                    <div className="card-body text-center">
                        <h3>{this.state.label}</h3>
                    </div>
                </div>
            </div>
        )
    }


    render() {
        return (
            <div>
                <LoginHeaderComponent/>
                <div className="login">
                    {this.loginForm()}
                </div>
                {
                    this.state.loading ? <FullPageLoading/> : null
                }
            </div>
        );
    }
}

export default LoginComponent;