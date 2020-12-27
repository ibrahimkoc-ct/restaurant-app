import React, {Component} from 'react';
import HeaderComponent from "../homepage/HeaderComponent";
import WaiterService from "../../services/WaiterService";
import createBrowserHistory from 'history/createBrowserHistory';
import BackofficeContext from "../../BackofficeContext";
import axios from "axios";
import FullPageLoading from "../loading/FullPageLoading";

const history = createBrowserHistory({forceRefresh: true});

class CreateWaiterComponent extends Component {
    static contextType = BackofficeContext;

    constructor(props) {
        super(props);
        this.state = {
            name: '',
            phoneNumber: '',
            mail: '',
            address: '',
            salary: '',
            token: '',
            media: [],
            mediaSelect: {},
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
                history.push('/');
            }
        } else {
            this.state.token = localStorage.getItem("token")
        }
        axios.get("http://localhost:8080/file").then((res) => {
            this.setState({media: res.data, loading: false});
        }).catch(this.setState({loading:false}));
    }
    debugBase64(base64URL) {
        var win = window.open();
        win.document.write('<iframe src="' + base64URL + '" frameborder="0" style="border:0; top:0px; left:0px; bottom:0px; right:0px; width:100%; height:100%;" allowfullscreen></iframe>');
    }

    cancel() {
        this.props.history.push('/waiter-table');
    }

    changeSelect = (media) => {
        this.state.mediaSelect = media;
    }

    saveWaiter = (e) => {
        this.setState({loading: true})
        let waiter = {
            name: this.state.name, phoneNumber: this.state.phoneNumber
            , mail: this.state.mail, address: this.state.address,
             salary: this.state.salary,
            mediaDTO: this.state.mediaSelect
        }
        WaiterService.addWaiter(waiter, this.state.token).then(res => {
            this.props.history.push('/waiter-table');
            this.setState({loading: false})
        })

        e.preventDefault();
    }
    changeInput = (e) => {
        this.setState({
            [e.target.name]: e.target.value
        })
    }
    addWaiterForm =()=>{
        if(!this.state.media){
            return <h2>Bir hata oluştu. Lütfen daha sonra tekrar deneyiniz</h2>
        }
        return (
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
                        <input placeholder="Adres" name="adres" className="form-control"
                               value={this.state.address} onChange={this.changeInput}/>
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
                        <label>Maas</label>
                        <input placeholder="Mass" name="salary" className="form-control" type="Number"
                               value={this.state.salary} onChange={this.changeInput}/>
                    </div>
                    <button className="btn btn-success" onClick={this.saveWaiter.bind(this)}>Kaydet
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
                            <h3 className="text-center">Garson Ekle</h3>
                            {this.addWaiterForm()}
                        </div>
                    </div>
                </div>
                {this.state.loading ? <FullPageLoading/> : null}

            </div>
        );
    }
}

export default CreateWaiterComponent;