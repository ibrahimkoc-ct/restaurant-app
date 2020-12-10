import React, {Component} from 'react';
import UserService from "../services/UserService";
import HeaderComponent from "./HeaderComponent";
import Table from "react-bootstrap/Table";
import FooterComponent from "./FooterComponent";
import createBrowserHistory from 'history/createBrowserHistory';
import BackofficeContext from "../BackofficeContext";
const history = createBrowserHistory({forceRefresh:true});



class ServerListInfoComponent extends Component {
    static contextType = BackofficeContext;
    constructor(props) {
        super(props)
        this.state = {
            infolist: [],
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

        UserService.getServerInfo(this.state.token).then((res)=>{
            this.setState({ infolist:res.data});
        });

    }
    render() {
        return (
            <div>
                <HeaderComponent/>
                <div className="container productlist">
                    <h2 className="text-center">Server Bilgileri</h2>
                    <div className="row">
                    </div>
                    <div className="row">
                        <Table striped bordered hover >
                            <thead>
                            <tr>

                                <th>Bilgi</th>
                                <th >Degeri</th>

                            </tr>
                            </thead>
                            <tbody>
                            {
                                this.state.infolist.map(
                                    user =>
                                        <tr >
                                            <td>{user.value}</td>
                                            <td>{user.key}</td>

                                        </tr>
                                )
                            }
                            </tbody>
                        </Table>
                    </div>

                </div>
                <FooterComponent/>

            </div>
        );
    }
}

export default ServerListInfoComponent;