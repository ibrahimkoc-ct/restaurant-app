import React, {Component} from 'react';
import UserService from "../../services/UserService";
import HeaderComponent from "./HeaderComponent";
import Table from "react-bootstrap/Table";
import FooterComponent from "./FooterComponent";
import createBrowserHistory from 'history/createBrowserHistory';
import BackofficeContext from "../../BackofficeContext";
import FullPageLoading from "../loading/FullPageLoading";
import {Modal} from "react-bootstrap";
import ReactToExcel from "react-html-table-to-excel";

const history = createBrowserHistory({forceRefresh: true});

class ServerListInfoComponent extends Component {
    static contextType = BackofficeContext;

    constructor(props) {
        super(props)
        this.state = {
            infolist: [],
            token: '',
            loading: false,
            beans: [],
            show: false
        }
    }

    componentDidMount() {
        this.setState({loading: true})
        const userToken = this.context;
        if (localStorage.getItem("token") == null) {
            if (userToken.token.length > 0) {
                this.state.token = userToken.token;

            } else {
                history.push('/');
            }
        } else {
            this.state.token = localStorage.getItem("token")
        }
        UserService.getServerInfo(this.state.token).then((res) => {
            this.setState({infolist: res.data, loading: false});
        });
        UserService.getBeans().then((res) => {
            this.setState({beans: res.data})
        })
    }
    beansModal =()=>{
        return(
            <div>
                <Modal show={this.state.show} size="xl">
                    <Modal.Header>
                        <div className="container">
                            <div className="row">
                                <h2 style={{textAlign:"center"}}>Bean Listesi</h2>
                            </div>
                            <div className="row">
                                <ReactToExcel
                                    className="btn btn-success exportButton "
                                    table="beansTable"
                                    filename="Beans"
                                    sheet="sheet 1"
                                    buttonText="Tabloyu İndir"/>
                            </div>
                        </div>
                    </Modal.Header>
                    <Modal.Body className="ModalBody">
                        {this.beanModalTable()}
                    </Modal.Body>
                    <Modal.Footer>
                        <button className="btn btn-danger" onClick={() =>
                            this.setState({show:false})}>İptal
                        </button>
                    </Modal.Footer>
                </Modal>
            </div>
        )
    }

    beanModalTable =()=>{
        if(!this.state.beans){
            return <h2>Bean Bilgilerine Erişilemedi!</h2>
        }
        return(
            <div align="center">
                <Table striped bordered hover id="beansTable">
                    <thead>
                    <tr>
                        <th>Sıra</th>
                        <th>Bean Adi</th>
                    </tr>
                    </thead>
                    <tbody>
                    {
                        this.state.beans.map(
                            (v,index) =>
                                <tr key={index}>
                                    <td>{index+1}</td>
                                    <td>{v}</td>
                                </tr>
                        )
                    }
                    </tbody>
                </Table>
            </div>
        )
    }

    serverInfoForm = () => {
        if (!this.state.infolist) {
            return <h2>Server bilgilerine erişilemedi</h2>
        }
        return (
            <div className="row">
                <button className="btn btn-success mb-2 beanButton" onClick={()=>this.setState({show:true})}>Bean Listesi</button>
                <Table striped bordered hover>
                    <thead>
                    <tr>
                        <th>Bilgi</th>
                        <th>Degeri</th>
                    </tr>
                    </thead>
                    <tbody>
                    {
                        this.state.infolist.map(
                            user =>
                                <tr key={user.value}>
                                    <td>{user.value}</td>
                                    <td>{user.key}</td>
                                </tr>
                        )
                    }
                    </tbody>
                </Table>
            </div>
        )
    }

    render() {
        return (
            <div>
                <HeaderComponent/>
                <div className="container productlist">
                    <h2 className="text-center">Server Bilgileri</h2>
                    <div className="row">
                    </div>
                    {this.serverInfoForm()}
                    {this.beansModal()}
                </div>
                <FooterComponent/>
                {this.state.loading ? <FullPageLoading/> : null}
            </div>
        );
    }
}

export default ServerListInfoComponent;