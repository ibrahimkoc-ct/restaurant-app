import React, {Component} from 'react';
import WaiterService from "../../services/WaiterService";
import HeaderComponent from "../homepage/HeaderComponent";
import createBrowserHistory from 'history/createBrowserHistory';
import BackofficeContext from "../../BackofficeContext";
import FullPageLoading from "../loading/FullPageLoading";

const history = createBrowserHistory({forceRefresh: true});

class ViewWaiterComponent extends Component {
    static contextType = BackofficeContext;

    constructor(props) {
        super(props);
        this.state = {
            waiter: this.props.history.location.state?.waiter,
            token: '',
            loading: false
        }
    }

    componentDidMount() {
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
    }
    viewWaiter =()=>{
        return(
            <div className="card-body">
                <div className="row">
                    <h3>Garson Adı: {this.state.waiter.name}</h3>
                </div>
                <hr></hr>
                <div className="row">
                    <h3>Telefon numarası: {this.state.waiter.phoneNumber}</h3>
                </div>
                <hr></hr>
                <div className="row">
                    <h3>Mail Adresi: {this.state.waiter.mail}</h3>
                </div>
                <hr></hr>
                <div className="row">
                    <h3>Adres: {this.state.waiter.address}</h3>
                </div>
                <hr></hr>
                <div className="row">
                    <h3>Maas: {this.state.waiter.salary}</h3>
                </div>
                <div className="row">
                    <h3>Maas: {this.state.waiter.salary}</h3>
                </div>
                <hr></hr>
                <div className="row">
                    <h3>Resim: <img
                        src={'data:image/png;base64,' + this.state.waiter.mediaDTO.fileContent}
                        width="100"/></h3>
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
                    <h2 className="text-center">Garson Detayları</h2>
                    {this.viewWaiter()}
                </div>
                {this.state.loading ? <FullPageLoading/> : null}
            </div>
        );
    }
}

export default ViewWaiterComponent;