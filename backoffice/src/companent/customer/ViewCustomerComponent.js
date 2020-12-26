import React, {Component} from 'react';
import HeaderComponent from "../homepage/HeaderComponent";
import BackofficeContext from "../../BackofficeContext";
import FullPageLoading from "../loading/FullPageLoading";
import {redirectWithId} from '../../RouterRedirect';

class ViewCustomerComponent extends Component {
    static contextType = BackofficeContext;

    constructor(props) {
        super(props);
        this.state = {
            customer: this.props.history.location.state?.customer
        }
    }

    componentDidMount() {

        const userToken = this.context;
        if (localStorage.getItem("token") == null) {
            if (userToken.token.length > 0) {
                this.state.token = userToken.token;
            } else {
                redirectWithId('/');
            }
        } else {
            this.state.token = localStorage.getItem("token")
        }

    }

    viewCustomer = () => {
        if (!this.state.customer) {
            return <h3>Kulllanıcı Bulunamadı!</h3>
        }
        return (
            <div className="card-body">
                <div className="row">
                    <h3>Musteri Adı: {this.state.customer.name}</h3>
                </div>
                <div className="row">
                    <h3>Musteri Soyadı: {this.state.customer.surname}</h3>
                </div>
                <div className="row">
                    <h3>Musteri Numarası: {this.state.customer.phoneNumber}</h3>
                </div>
                <div className="row">
                    <h3>Musteri Adresi: {this.state.customer.address}</h3>
                </div>
            </div>
        )
    }

    render() {
        return (
            <div>
                <HeaderComponent/>
                <br></br>
                <div className="card col-md-6 offset-md-3">
                    <h2 className="text-center">Musteri Detayları</h2>
                    {this.viewCustomer()}
                    {this.state.loading ? <FullPageLoading/> : null}
                </div>
            </div>
        );
    }
}

export default ViewCustomerComponent;