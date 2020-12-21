import React, {Component} from 'react';
import HeaderComponent from "./HeaderComponent";
import {Link} from "react-router-dom";
import {Modal} from "react-bootstrap"
import "./App2.css";
import WaiterService from "../services/WaiterService";
import ClientContext from "../ClientContext";
import createBrowserHistory from 'history/createBrowserHistory';
import FullPageLoading from './FullPageLoading'

const history = createBrowserHistory({forceRefresh: true});

class HomePageCompanent extends Component {
    static contextType = ClientContext;

    constructor(props) {
        super(props);
        this.state = {
            token: '',
            waiterList: [],
            show: false,
            loading: false


        }
    }

    componentDidMount() {
        this.setState({loading: true})

        const userToken = this.context;
        if (localStorage.getItem("token") == null) {

            if (userToken.token.length > 0) {
                this.state.token = userToken.token;
            } else {
                this.props.history.push('/');
            }
        } else {
            this.state.token = localStorage.getItem("token")

        }
        WaiterService.getWaiter(this.state.token).then((res) => {
            this.setState({waiterList: res.data, loading: false})
        })
    }

    signOut = (e) => {


        localStorage.removeItem("token")
        this.props.history.push('/');
        e.preventDefault()
    }

    onClickWaiter = (waiter1) => {
        this.setState({loading: true})
        this.setState({show: false, loading: false})
        localStorage.setItem("waiter", waiter1.name)
        this.props.history.push('/products')
    }

    onClickProduct() {
        this.setState({show: true})
    }

    onClickWaiterCancel() {
        this.setState({show: false})
    }

    render() {
        return (

            <div className="HomePage">
                <HeaderComponent/>
                <Link to="/homepage">
                    <button className="btn btn-info backbutton fas fa-edit"></button>
                </Link>
                    <button className="btn btn-secondary homebutton">SEPET</button>
                <Link to="/table">
                    <button className="btn btn-secondary homebutton">MASALAR</button>
                </Link>
                    <button className="btn btn-secondary homebutton">RAPORLAR</button>
                <button onClick={() =>
                    this.onClickProduct()} className="btn btn-secondary homebutton">ÜRÜNLER
                </button>
                <Link to="/category">
                    <button className="btn btn-secondary homebutton">KULLANICILAR</button>
                </Link>
                    <button className="btn btn-secondary homebutton"></button>
                    <button className="btn btn-secondary homebutton"></button>

                    <button className="btn btn-secondary homebutton" onClick={this.signOut}>ÇIKIŞ</button>
                <Modal show={this.state.show}>
                    <Modal.Header>Garson Seçimi</Modal.Header>
                    <Modal.Body className="ModalBody">
                        {
                            this.state.waiterList.map(
                                waiter => {
                                    return (
                                        <button key={waiter.id} className="btn btn-outline-success btn-block " onClick={() =>
                                            this.onClickWaiter(waiter)}>
                                            <div  className="row">
                                                <div className="col-xl-8 my-auto" align="left">{waiter.name}</div>
                                                <div className="col-xl-4"><img
                                                    src={'data:image/png;base64,' + waiter.mediaDTO.fileContent}
                                                    height="40" width="40" style={{margin: 10}}/></div>

                                            </div>
                                        </button>


                                    )
                                })}
                    </Modal.Body>
                    <Modal.Footer>
                        <button className="btn btn-danger" onClick={() =>
                            this.onClickWaiterCancel()}>iptal
                        </button>
                    </Modal.Footer>


                </Modal>

                {this.state.loading ? <FullPageLoading/> : null}
            </div>
        );
    }
}

export default HomePageCompanent;