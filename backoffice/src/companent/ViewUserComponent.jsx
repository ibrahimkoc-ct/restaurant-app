import React, {Component} from 'react';
import UserService from "../services/UserService";
import HeaderComponent from "./HeaderComponent";
import createBrowserHistory from 'history/createBrowserHistory';
import BackofficeContext from "../BackofficeContext";
const history = createBrowserHistory({forceRefresh:true});

class ViewUserComponent extends Component {
    static contextType = BackofficeContext;
    constructor(props) {
        super(props);
        this.state= {

            username: "",
            enabled: "",
            password: "",

            user: [],
            token:''


        }
    }
    componentDidMount() {
        const userToken = this.context;
        if(localStorage.getItem("token")==null){
            if(userToken.token.length>0){
                this.state.token=userToken.token;

                console.log(this.state.token)
            }
            else{
                history.push('/');
            }
        }
        else {
            this.state.token=localStorage.getItem("token")
        }

        const {enabled }=this.state
        UserService.getUsersById(sessionStorage.getItem("wiew"),this.state.token).then(res =>{



            this.setState({
                user:res.data,
                enabled:res.data.enabled.toString()

            })
            console.log(enabled)

        })

    }


    render() {
        return (
            <div>
                <HeaderComponent/>
                <br></br>
                <div className="card col-md-6 offset-md-3" >
                    <h2 className="text-center">Kullanıcı Detayları</h2>
                    <div className="card-body">
                        <div className="row">
                            <h3>Kullanıcı Adı: {this.state.user.username}</h3>
                        </div>
                        <hr></hr>

                        <div className="row">

                            <h3>Parola:  {this.state.user.password}</h3>
                        </div>
                        <hr></hr>
                        <div className="row">
                            <h3>Aktiflik: {this.state.enabled}</h3>
                        </div>


                    </div>
                </div>
            </div>

        );
    }
}

export default ViewUserComponent;