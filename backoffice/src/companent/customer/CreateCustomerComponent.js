import React, {Component} from 'react';
import BackofficeContext from "../../BackofficeContext";
import CustomerService from "../../services/CustomerService";
import HeaderComponent from "../homepage/HeaderComponent";
import FooterComponent from "../homepage/FooterComponent";
import FullPageLoading from "../loading/FullPageLoading";
import {redirectWithId} from '../../RouterRedirect';
import axios from "axios";
import { Formik } from 'formik';
class CreateCustomerComponent extends Component {


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
            media: [],
            mediaSelect: {},
        }
    }

    componentDidMount() {
        const userToken = this.context;
        this.setState({loading: true})
        if (localStorage.getItem("token") == null) {
            if (userToken.token.length > 0) {
                this.state.token = userToken.token;

            } else
                redirectWithId('/customers');
        } else {
            this.state.token = localStorage.getItem("token")
        }
        axios.get("http://localhost:8080/file").then((res) => {
            this.setState({media: res.data, loading: false})
        }).catch(this.setState({loading:false}));
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
            phoneNumber: this.state.phoneNumber,
            mediaDTO: this.state.mediaSelect
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

    debugBase64(base64URL) {
        var win = window.open();
        win.document.write('<iframe src="' + base64URL + '" frameborder="0" style="border:0; top:0px; left:0px; bottom:0px; right:0px; width:100%; height:100%;" allowfullscreen></iframe>');
    }

    addCustomerForm = () => {
        if(!this.state.media){
            return <h2>Bir hata oluştu. Lütfen daha sonra tekrar deneyiniz.</h2>
        }
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
                    <label>Resim</label>
                    <div className="form-group">
                        <div className="form-check" style={{height: "4rem", overflow: "auto"}}>
                            {
                                this.state.media.map(
                                    media =>
                                        <div key={media.name} className="row col-md -12 custom-control custom-radio">
                                            <input className="form-check-input" name="customRadio"
                                                   type="radio"
                                                   onClick={() => this.changeSelect(media)}/>
                                            <label className="form-check-label">
                                                <a onClick={() => this.debugBase64('data:image/png;base64,' + media.fileContent)}>{media.name}</a></label>
                                        </div>
                                )
                            }
                        </div>
                    </div>
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