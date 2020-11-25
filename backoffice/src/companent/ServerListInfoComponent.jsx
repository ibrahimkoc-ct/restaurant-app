import React, {Component} from 'react';
import UserService from "../services/UserService";
import HeaderComponent from "./HeaderComponent";
import Table from "react-bootstrap/Table";
import FooterComponent from "./FooterComponent";

class ServerListInfoComponent extends Component {
    constructor(props) {
        super(props)
        this.state = {
            infolist: []
        }

    }
    componentDidMount(){
        UserService.getServerInfo().then((res)=>{
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