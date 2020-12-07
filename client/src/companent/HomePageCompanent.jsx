import React, {Component} from 'react';
import HeaderComponent from "./HeaderComponent";
import FooterComponent from "./FooterCompanent";
import {Link} from "react-router-dom";
import {Button, Modal} from "react-bootstrap"
import "./App2.css";
import WaiterService from "../services/WaiterService";

class HomePageCompanent extends Component {
    constructor() {
        super();
        this.state={
            waiterList:[],
            show:false,

        }
    }
    componentDidMount() {
        WaiterService.getWaiter().then((res)=>{
            this.setState({waiterList:res.data})
        })
    }

    signOut = (e) => {
        e.preventDefault()

        sessionStorage.removeItem("token")
        this.props.history.push('/');
    }

    onClickWaiter=(waiter)=>{
        this.setState({show:false})
        this.props.history.push('/products')
        localStorage.setItem("waiter",waiter.name)
    }
    onClickProduct(){
        this.setState({show:true})
    }
    onClickWaiterCancel(){
        this.setState({show:false})
    }

    render() {
        return (

            <div className="HomePage">
                <HeaderComponent/>
                <Link to="/homepage">
                    <button className="btn btn-info backbutton fas fa-edit" ></button>
                </Link>
                <Link>
                    <button className="btn btn-secondary homebutton">SEPET</button>
                </Link>
                <Link to ="/table">
                    <button className="btn btn-secondary homebutton">MASALAR</button>
                </Link>
                <Link>
                    <button className="btn btn-secondary homebutton">RAPORLAR</button>
                </Link>
                    <button onClick={() =>
                        this.onClickProduct()} className="btn btn-secondary homebutton">ÜRÜNLER</button>
                <Link>
                    <button className="btn btn-secondary homebutton">KULLANICILAR</button>
                </Link>
                <Link>
                    <button className="btn btn-secondary homebutton"></button>
                </Link>

                <Link>
                    <button className="btn btn-secondary homebutton"></button>
                </Link>
                <Link>
                <button className="btn btn-secondary homebutton"></button>
            </Link>
                <Link>
                    <button className="btn btn-secondary homebutton" onClick={this.signOut}>ÇIKIŞ</button>
                </Link>
                <Modal show={this.state.show}>
                    <Modal.Header >Garson Seçimi</Modal.Header>
                    <Modal.Body className="ModalBody">
                        {
                            this.state.waiterList.map(
                                waiter => {
                                    return (
                                        <button className="btn btn-outline-success btn-block "onClick={() =>
                                            this.onClickWaiter(waiter)}>{waiter.name}</button>


                                    )
                                })}
                    </Modal.Body>
                    <Modal.Footer>
                        <button className="btn btn-danger" onClick={() =>
                            this.onClickWaiterCancel()}>iptal</button>
                    </Modal.Footer>


                </Modal>


            </div>
        );
    }
}

export default HomePageCompanent;