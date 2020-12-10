import React, {Component} from 'react';
import UserService from "../services/UserService";
import HeaderComponent from "./HeaderComponent";
import Table from "react-bootstrap/Table";
import FooterComponent from "./FooterComponent";
import BackofficeContext from "../BackofficeContext";
import createBrowserHistory from 'history/createBrowserHistory';
const history = createBrowserHistory({forceRefresh:true});
class AuthListComponent extends Component {
    static contextType = BackofficeContext;
    constructor(props) {
        super(props)
        this.state = {
            authlist: [],
            token:''
        }

    }
    componentDidMount(){
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


        UserService.getAuth(this.state.token).then((res)=>{
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