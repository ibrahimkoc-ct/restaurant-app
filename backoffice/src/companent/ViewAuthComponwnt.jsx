import React, {Component} from 'react';
import UserService from "../services/UserService";
import HeaderComponent from "./HeaderComponent";

class ViewUserComponent extends Component {
    constructor(props) {
        super(props);
        this.state={


            user:{

            }
        }

    }
    componentDidMount() {
        UserService.getAuthById(sessionStorage.getItem("view-auth")).then(res =>{
            this.setState({user:res.data})


        })
        console.log(this.state.user)
    }

    render() {
        return (
            <div>
                <HeaderComponent/>
                <br></br>
                <div className="card col-md-6 offset-md-3" >
                    <h2 className="text-center">Yetkinlik Detayları</h2>
                    <div className="card-body">
                        <div className="row">
                            <h3>Kullanıcı Adı: {this.state.user.username}</h3>
                        </div>
                        <hr></hr>
                        <div className="row">

                            <h3>Rol:  {this.state.user.authority}</h3>
                        </div>

                    </div>
                </div>
            </div>

        );
    }
}

export default ViewUserComponent;