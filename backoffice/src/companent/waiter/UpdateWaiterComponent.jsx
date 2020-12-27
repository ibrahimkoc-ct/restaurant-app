import React, {Component} from 'react';
import HeaderComponent from "../homepage/HeaderComponent";
import WaiterService from "../../services/WaiterService";
import createBrowserHistory from 'history/createBrowserHistory';
import BackofficeContext from "../../BackofficeContext";
import FullPageLoading from "../loading/FullPageLoading";

const history = createBrowserHistory({forceRefresh: true});

class UpdateWaiterComponent extends Component {
    static contextType = BackofficeContext;

    constructor(props) {
        super(props);
        this.state = {
            waiter: this.props.history.location.state?.waiter,
            id:'',
            name: '',
            phoneNumber: '',
            mail: '',
            address: '',
            salary: '',
            token: '',
            loading: false
        }

    }
    changeInput = (e) => {
        this.setState({
            [e.target.name]: e.target.value
        })
    }
    cancel() {
        this.props.history.push('/waiter-table');
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

    updateWaiter = (e) => {
        let waiter = {
            id: this.state.waiter.id, name: this.state.name, phoneNumber: this.state.phoneNumber
            , mail: this.state.mail, address: this.state.address,
             salary: this.state.salary,
            mediaDTO: this.state.waiter.mediaDTO
        }
        WaiterService.updateWiew(waiter, this.state.token).then(res => {
            this.props.history.push('/waiter-table');
        })
        e.preventDefault();
    }
    updateWaiterForm =()=>{
        return(
            <div className="card-body">
                <form>
                    <div className="form-group">
                        <label>Garson Adı</label>
                        <input placeholder="Garson Adı" name="name" className="form-control"
                               value={this.state.name} onChange={this.changeInput}/>
                    </div>
                    <div className="form-group">
                        <label>Telefon Numarası</label>
                        <input placeholder="Telefon Numarası" name="phoneNumber" type="Number"
                               className="form-control"
                               value={this.state.phoneNumber} onChange={this.changeInput}/>
                    </div>
                    <div className="form-group">
                        <label>Mail Adresi</label>
                        <input placeholder="Mail Adresi" name="mail" className="form-control"
                               value={this.state.mail} onChange={this.changeInput}/>
                    </div>
                    <div className="form-group">
                        <label>Adres</label>
                        <input placeholder="Adres" name="address" className="form-control"
                               value={this.state.address} onChange={this.changeInput}/>
                    </div>
                    <div className="form-group">
                        <label>Maas</label>
                        <input placeholder="Mass" name="salary" className="form-control" type="Number"
                               value={this.state.salary} onChange={this.changeInput}/>
                    </div>
                    <button className="btn btn-success" onClick={this.updateWaiter.bind(this)}>Kaydet
                    </button>
                    <button className="btn btn-danger" onClick={this.cancel.bind(this)}
                            style={{marginLeft: "10px"}}>Iptal
                    </button>
                </form>
            </div>
        )
    }

    render() {
        return (
            <div>
                <HeaderComponent/>
                <div className="container">
                    <div className="row">
                        <div className="card col-md-6 offset-md-3 offset-md-3">
                            <h3 className="text-center">Kategori Ekle</h3>
                            {this.updateWaiterForm()}
                        </div>
                    </div>
                </div>
                {this.state.loading ? <FullPageLoading/> : null}
            </div>
        );
    }
}

export default UpdateWaiterComponent;