import React, {Component} from 'react';
import BackofficeContext from "../../BackofficeContext";
import CustomerService from "../../services/CustomerService";
import HeaderComponent from "../homepage/HeaderComponent";
import FooterComponent from "../homepage/FooterComponent";
import FullPageLoading from "../loading/FullPageLoading";
import {redirectWithId} from '../../RouterRedirect';
class CreateCustomerComponent extends Component {
    static contextType = BackofficeContext;

    constructor(props) {
        super(props);
        this.state = {
            id: '',
            name: '',
            surname: '',
            address: '',
            phoneNumber: '',
            loading: false,
            token: '',
        }
    }

    componentDidMount() {
        const userToken = this.context;
        if (localStorage.getItem("token") == null) {
            if (userToken.token.length > 0) {
                this.state.token = userToken.token;

            } else
                redirectWithId('/customers');
        } else {
            this.state.token = localStorage.getItem("token")
        }
    }

    changeSelect = (media) => {
        this.state.mediaSelect = media;
    }

    saveCustomer = () => {
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
        CustomerService.addCustomer(customer, this.state.token)
        this.props.history.push('/customers')
    }
    changeInput = (e) => {
        this.setState({
            [e.target.name]: e.target.value
        })
    }


    addCustomerForm = () => {
        return (

            <form>
                <div className="form-group">
                    <label>Musteri Adı</label>
                    <input placeholder="Musteri Adı" name="name" className="form-control"
                           value={this.state.name} onChange={this.changeInput}/>
                </div>
                <div className="form-group">
                    <label>Musteri Soyadı</label>
                    <input placeholder="Musteri soyadı" name="surname" className="form-control"
                           value={this.state.surname} onChange={this.changeInput}/>
                </div>
                <div className="form-group">
                    <label>Musteri Numarası</label>
                    <input placeholder="Musteri Numarası" name="phoneNumber" className="form-control"
                           value={this.state.phoneNumber} onChange={this.changeInput}/>
                </div>
                <div className="form-group">
                    <label>Musteri Adresi</label>
                    <input placeholder="Musteri Adresi" name="address" className="form-control"
                           value={this.state.address} onChange={this.changeInput}/>
                </div>
                <hr/>
            </form>
        )
    }

    render() {
        return (
            <div>
                <HeaderComponent/>
                <div className="container">
                    <div className="row">
                        <div className="card col-md-6 offset-md-3 offset-md-3">
                            <h3 className="text-center">Müsteri Ekle</h3>
                            <div className="card-body">
                                {this.addCustomerForm()}
                                <button className="btn btn-success" onClick={() => this.saveCustomer()}>Kaydet</button>
                                <button className="btn btn-danger" onClick={() => redirectWithId('/customers')}
                                        style={{marginLeft: "10px"}}>Iptal
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
                <FooterComponent/>
                {this.state.loading ? <FullPageLoading/> : null}
            </div>

        );
    }
}

export default CreateCustomerComponent;