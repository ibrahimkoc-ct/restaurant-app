import React, {Component} from 'react';
import UserService from "../services/UserService";
import HeaderComponent from "./HeaderComponent";

class ViewUserComponent extends Component {
    constructor(props) {
        super(props);
        this.state= {

            username: "",
            enabled: "",
            password: "",

            user: []


        }
    }
    componentDidMount() {
        const {enabled }=this.state
        UserService.getUsersById(sessionStorage.getItem("wiew")).then(res =>{



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