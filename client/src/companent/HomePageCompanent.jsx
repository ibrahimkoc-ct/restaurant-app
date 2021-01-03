import React, {Component} from 'react';
import HeaderComponent from "./HeaderComponent";
import {Link} from "react-router-dom";
import {Modal} from "react-bootstrap"
import "./App2.css";
import WaiterService from "../services/WaiterService";
import ClientContext from "../ClientContext";
import FullPageLoading from './FullPageLoading'
import CustomerService from "../services/CustomerService";
import axios from 'axios';

class HomePageCompanent extends Component {
    static contextType = ClientContext;

    constructor(props) {
        super(props);
        this.state = {
            id: '',
            name: '',
            surname: '',
            address: '',
            phoneNumber: '',
            token: '',
            waiterList: [],
            show: false,
            addCustomer: false,
            viewCustomer: false,
            loading: false,
            hasNext: true,
            scrollTop: 0,
            pageNum: 0,
            customers: [],
        }
        this.myRef = React.createRef()
    }

    componentDidMount() {
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

        localStorage.setItem("product", "Secili Masa Yok")
    }


    onScroll = () => {
        const scrollTop = this.myRef.current.scrollTop
        this.setState({scrollTop: scrollTop})
        if (scrollTop > Math.pow(this.state.pageNum + 1, 2) * 100) {
            if (this.state.hasNext == false) {
                this.state.pageNum += 1;
                CustomerService.getPageCustomer(this.state.token, this.state.pageNum).then((res) => {
                    this.setState({hasNext: res.data.last, customers: [...this.state.customers, ...res.data.content]})
                })
            }

        }
    }

    saveCustomer = (e) => {
        let customer = {
            id: this.state.id,
            name: this.state.name,
            surname: this.state.surname,
            address: this.state.address,
            phoneNumber: this.state.phoneNumber
        };
        if (!customer) {
            return
        }
        CustomerService.addCustomer(customer, this.state.token).then((res)=>{
            sessionStorage.setItem("customerId",res.data.id)
        })
        localStorage.setItem("product", "Secili Masa Yok")

        this.props.history.push("/products")
        e.preventDefault();
    }

    signOut = (e) => {
        localStorage.removeItem("token")
        this.props.history.push('/');
        e.preventDefault()
    }

    viewCustomer() {
        this.setState({show: false, viewCustomer: true, loading: true})
        CustomerService.getPageCustomer(this.state.token, 0).then((res) => {
            this.setState({customers: res.data.content, hasNext: res.data.last, loading: false, pageNum: 0})
        }).catch(
            this.setState({customers: null, loading: false, pageNum: 0})
        )
    }

    selectedCustomer(customer) {
        localStorage.setItem("product", "Secili Masa Yok")
        sessionStorage.setItem("customerId",customer.id)
        this.props.history.push('/products');
    }

    goProduct() {
        localStorage.setItem("product", "Secili Masa Yok")
        this.props.history.push('/products');
    }

    takeaway = () => {
        return (
            <Modal show={this.state.show}>
                <Modal.Header>Paket Servis</Modal.Header>
                <Modal.Body align="center">
                    <div>
                        <button onClick={() => this.setState({show: false, addCustomer: true})}
                                className="btn btn-info customerButton ">Müsteri Ekle
                        </button>
                        <button onClick={() => this.viewCustomer()} style={{marginLeft: "10px"}}
                                className="btn btn-success customerButton">Kayıtlı Müsteriler
                        </button>
                    </div>
                </Modal.Body>
                <Modal.Footer>
                    <button onClick={() => this.goProduct()} style={{marginLeft: "10px"}}
                            className="btn btn-secondary btn-block">Müsteri Seçmeden Devam Et
                    </button>
                    <button className="btn btn-danger" onClick={() => this.setState({show: false})}>Iptal</button>
                </Modal.Footer>
            </Modal>
        )
    }

    addCustomerModal = () => {
        return (
            <Modal show={this.state.addCustomer}>
                <Modal.Header>Müsteri Ekle</Modal.Header>
                <Modal.Body align="center" style={{backgroundColor: '#446084'}}>
                    <form>
                        <div className="form-group">
                            <label style={{color: 'white'}}>Musteri Adı</label>
                            <input placeholder="Musteri Adı" name="name" className="form-control"
                                   value={this.state.name} onChange={this.changeInput}/>
                        </div>
                        <div className="form-group">
                            <label style={{color: 'white'}}>Musteri Soyadı</label>
                            <input placeholder="Musteri soyadı" name="surname" className="form-control"
                                   value={this.state.surname} onChange={this.changeInput}/>
                        </div>
                        <div className="form-group">
                            <label style={{color: 'white'}}>Musteri Numarası</label>
                            <input placeholder="Musteri Numarası" name="phoneNumber" className="form-control"
                                   value={this.state.phoneNumber} onChange={this.changeInput}/>
                        </div>
                        <div className="form-group">
                            <label style={{color: 'white'}}>Musteri Adresi</label>
                            <input placeholder="Musteri Adresi" name="address" className="form-control"
                                   value={this.state.address} onChange={this.changeInput}/>
                        </div>
                </form>
                </Modal.Body>
                <Modal.Footer>
                    <button className="btn btn-success" onClick={this.saveCustomer.bind(this)}>Kaydet</button>
                    <button className="btn btn-danger" onClick={() => this.setState({addCustomer: false})}>Iptal
                    </button>
                </Modal.Footer>
            </Modal>
        )
    }

    viewCustomerModal = () => {
        if (!this.state.customers) {
            return <h3>Kayıtlı Müsteri Yok</h3>
        }
        return (
            <Modal show={this.state.viewCustomer}>
                <Modal.Header>Müsteri Seçimi</Modal.Header>
                <Modal.Body>
                    <div className="viewCustomer" ref={this.myRef} onScroll={this.onScroll}>
                        {
                            this.state.customers.map(
                                v => {
                                    return (
                                        <div>
                                            <button onClick={() => this.selectedCustomer(v)}
                                                    className="btn btn-outline-success selectCustomerButton">
                                                <div className="row">
                                                    <div className="col-xl-8" align="left">{v.name} {v.surname}</div>
                                                    <div className="col-xl-4"></div>
                                                </div>
                                            </button>
                                        </div>

                                    )
                                })
                        }
                    </div>
                </Modal.Body>
                <Modal.Footer>
                    <button className="btn btn-danger" onClick={() => this.setState({viewCustomer: false})}>Iptal
                    </button>
                </Modal.Footer>
            </Modal>
        )
    }

    changeInput = (e) => {
        this.setState({
            [e.target.name]: e.target.value
        })
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
                <button onClick={() => this.setState({show: true})} className="btn btn-secondary homebutton">ÜRÜNLER
                </button>
                <Link to="/category">
                    <button className="btn btn-secondary homebutton">KULLANICILAR</button>
                </Link>
                <button className="btn btn-secondary homebutton"></button>
                <button className="btn btn-secondary homebutton"></button>
                <button className="btn btn-secondary homebutton"></button>
                <button className="btn btn-secondary homebutton" onClick={this.signOut}>ÇIKIŞ</button>
                {this.takeaway()}
                {this.addCustomerModal()}
                {this.viewCustomerModal()}
                {this.state.loading ? <FullPageLoading/> : null}
            </div>
        );
    }
}

export default HomePageCompanent;