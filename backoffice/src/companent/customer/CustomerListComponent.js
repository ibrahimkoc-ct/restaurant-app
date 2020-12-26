import React, {Component} from 'react';
import {Link} from 'react-router-dom';
import CustomerService from "../../services/CustomerService";
import HeaderComponent from "../homepage/HeaderComponent";
import {Table} from "react-bootstrap";
import BackofficeContext from "../../BackofficeContext";
import FullPageLoading from "../loading/FullPageLoading";
import {redirectWithId} from '../../RouterRedirect';

class CustomerListComponent extends Component {
    static contextType = BackofficeContext;

    constructor(props) {
        super(props);
        this.state = {
            customerList: [],
            lastSelectedPage: 0,
            selectedPage: 0,
            loading: false,
            token: ''
        }
    }


    componentDidMount() {
        this.setState({loading:true})
        this.getTokenFromDb();
        this.getCustomerList(0, 0)

    }

    getCustomerList = (page, selectedPage) => {
        CustomerService.getPageCustomer(this.context.token, page).then((res) => {
            if (!res) {
                return;
            }
            this.setState({
                customerList: res.data.content,
                selectedPage: res.data.totalPages,
                loading: false,
                lastSelectedPage: page
            })
            let selectedButton = document.getElementById(page)
            selectedButton.className = "btn btn-secondary";
        }).catch(
            this.setState({customerList: null, loading: false})
        )
    }
    getTokenFromDb = () => {
        const userToken = this.context;
        this.setState({loading: true})
        if (userToken.token.length < 1) {
            if (localStorage.getItem("token") == null) {
                redirectWithId('/')
            } else {
                const {setToken} = this.context
                setToken(localStorage.getItem("token"))
            }
        }
    }
    viewPage = (page) => {
        this.setState({loading: true})
        let lastSelectedButton = document.getElementById(this.state.lastSelectedPage)
        lastSelectedButton.className = "btn btn-outline-secondary";
        this.getCustomerList(page, this.state.lastSelectedPage)

    }
    viewCustomer =(customer)=>{
        this.props.history.push({
            pathname:`view-customer/{customer.id}`,
            state:{
                customer:customer
            }
        });
    }
    deleteCustomer = (id) => {
        if (id == null) {
            return;
        }
        this.setState({loading: true})
        CustomerService.deleteCustomer(id, this.context.token).then(res => {
            if (!res) {
                this.setState({loading: false})
                return
            }
            this.setState({
                customerList: this.state.customerList.filter(customer => customer.id !== id),
                loading: false
            })

        }).catch(
            alert("silme islemi tamamlanamadı!"),
            this.setState({loading: false})
        )
    }


    customerListTable = () => {
        const {customerList} = this.state;
        if (!customerList) {
            return <h4>Kayıtlı kullanıcı bulunamadı!</h4>
        }

        return (
            <Table striped bordered hover>
                <thead>
                <tr>
                    <th>Musteri Id</th>
                    <th>Musteri Adı</th>
                    <th>Musteri Soyadı</th>
                    <th>Telefon Numarası</th>
                    <th>Musteri Adresi</th>
                    <th>Butonlar</th>
                </tr>
                </thead>
                <tbody>
                {
                customerList.map((customer) =>{
                    return(
                <tr key={customer.id}>
                    <td>{customer.id}</td>
                    <td>{customer.name}</td>
                    <td>{customer.surname}</td>
                    <td>{customer.phoneNumber}</td>
                    <td>{customer.address}</td>
                    <td>
                        <button onClick={() => redirectWithId('/update-customer/' + customer.id)}
                                className="btn btn-info">Guncelle
                        </button>
                        <button onClick={() => this.deleteCustomer(customer.id)} style={{marginLeft: "10px"}}
                                className="btn btn-danger">Sil
                        </button>
                        <button onClick={() => this.viewCustomer(customer)}
                                style={{marginLeft: "10px"}} className="btn btn-success">Detay
                        </button>
                    </td>
                </tr>)}
                )}
                </tbody>
            </Table>
        )
    }
    getPageButton = () => {
        let pageButtonArray = [];
        for (let i = 0; i < this.state.selectedPage; i++) {
            pageButtonArray.push(
                <button onClick={() => this.viewPage(i)} className="btn btn-outline-secondary" id={i}
                        style={{marginLeft: "5px"}}>{i + 1}</button>
            )
        }
        return pageButtonArray;
    }


    render() {

        return (
            <div>
                {this.state.loading ? <FullPageLoading/> : null}
                <HeaderComponent/>
                <Link to="/customers-add">
                    <button className="btn btn-info addbutton">Musteri Ekle</button>
                </Link>
                <div className="container productlist">
                    <h2 className="text-center">Musteriler</h2>
                    <div className="row">
                    </div>
                    <div className="row" align="center">
                        {this.customerListTable()}
                    </div>
                    <div align="center">
                        {this.getPageButton()}
                    </div>

                </div>
            </div>
        )
    }
}


export default CustomerListComponent;