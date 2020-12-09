import React, {Component} from 'react';
import UserService from "../services/UserService";
import HeaderComponent from "./HeaderComponent";
import Table from "react-bootstrap/Table";
import FooterComponent from "./FooterComponent";
import BackofficeContext from "../BackofficeContext";

class AuthListComponent extends Component {
    static contextType = BackofficeContext;
    constructor(props) {
        super(props)
        this.state = {
            authlist: []
        }

    }
    componentDidMount(){
        const token=this.context;
        console.log(token.token)

        UserService.getAuth().then((res)=>{
            this.setState({ authlist:res.data});
        });

    }
    editAuth(id){
        this.props.history.push('/update-auth/'+id);
        console.log(id)

    }
    viewAuth(id){
        this.props.history.push('/view-auth/'+id);
        sessionStorage.setItem("view-auth",id)

    }
    render() {
        return (
            <div>
                <HeaderComponent/>
                <div className="container productlist">
                    <h2 className="text-center">Yetkiler</h2>
                    <div className="row">
                    </div>
                    <div className="row">
                        <Table striped bordered hover >
                            <thead>
                            <tr>

                                <th>Kullanıcı Adı</th>
                                <th >Rol</th>
                                <th>Actions</th>
                            </tr>
                            </thead>
                            <tbody>
                            {
                                this.state.authlist.map(
                                    user =>
                                        <tr >
                                            <td>{user.username}</td>
                                            <td>{user.authority}</td>

                                            <td>
                                                <button  onClick={()=>this.editAuth(user.username)} className=" btn btn-info  ">Güncelle</button>
                                                <button style={{marginLeft: "10px"}}  onClick={()=>this.viewAuth(user.username)} className="btn btn-success">Görüntüle</button>
                                            </td>
                                        </tr>
                                )
                            }
                            </tbody>
                        </Table>
                    </div>

                </div>

            </div>
        );
    }
}

export default AuthListComponent;