import React, {Component} from 'react';
import HeaderComponent from "./HeaderComponent";
import WaiterService from "../services/WaiterService";

class CreateWaiterComponent extends Component {
    constructor(props) {
        super(props);
        this.state = {
            name: '',
            phoneNumber: '',
            mail: '',
            address: '',
            urlToImage: '',
            salary: '',

        }
        this.chargeNameHandler=this.chargeNameHandler.bind();
        this.chargePhoneHandler=this.chargePhoneHandler.bind();
        this.chargeMailHandler=this.chargeMailHandler.bind();
        this.chargeAddressHandler=this.chargeAddressHandler.bind();
        this.urlToImageHandler=this.urlToImageHandler.bind();
        this.salaryHandler=this.salaryHandler.bind();
    }
    chargeNameHandler=(event)=>{
        this.setState({name:event.target.value});
    }
    chargePhoneHandler=(event)=>{
        this.setState({phoneNumber:event.target.value});
    }
    chargeMailHandler=(event)=>{
        this.setState({mail:event.target.value});
    }
    chargeAddressHandler=(event)=>{
        this.setState({address:event.target.value});
    }
    urlToImageHandler=(event)=>{
        this.setState({urlToImage:event.target.value});
    }
    salaryHandler=(event)=>{
        this.setState({salary:event.target.value});
    }
    cancel(){
        this.props.history.push('/waiter-table');
    }
    saveWaiter =(e)=>{
        let waiter={name:this.state.name,phoneNumber:this.state.phoneNumber
        ,mail: this.state.mail,address: this.state.address,
        urlToImage: this.state.urlToImage,salary: this.state.salary}
        WaiterService.addWaiter(waiter).then(res=>{
            this.props.history.push('/waiter-table');
        })

        e.preventDefault();
    }

    render() {
        return (
            <div>
                <HeaderComponent/>
                <div className="container">
                    <div className="row">
                        <div className="card col-md-6 offset-md-3 offset-md-3">
                            <h3 className="text-center">Kategori Ekle</h3>
                            <div className="card-body">
                                <form>
                                    <div className="form-group">
                                        <label>Garson Adı</label>
                                        <input placeholder="Garson Adı" name="name" className="form-control"
                                               value={this.state.name} onChange={this.chargeNameHandler}/>
                                    </div>
                                    <div className="form-group">
                                        <label>Telefon Numarası</label>
                                        <input placeholder="Telefon Numarası" name="phoneNumber" type="Number" className="form-control"
                                               value={this.state.phoneNumber} onChange={this.chargePhoneHandler}/>
                                    </div>
                                    <div className="form-group">
                                        <label>Mail Adresi</label>
                                        <input placeholder="Mail Adresi" name="mail" className="form-control"
                                               value={this.state.mail} onChange={this.chargeMailHandler}/>
                                    </div>
                                    <div className="form-group">
                                        <label>Adres</label>
                                        <input placeholder="Adres" name="adres"  className="form-control"
                                               value={this.state.address} onChange={this.chargeAddressHandler}/>
                                    </div>
                                    <div className="form-group">
                                        <label>Resim Url</label>
                                        <input placeholder="Resim" name="resim" className="form-control"
                                               value={this.state.urlToImage} onChange={this.urlToImageHandler}/>
                                    </div>
                                    <div className="form-group">
                                        <label>Maas</label>
                                        <input placeholder="Mass" name="salary" className="form-control" type="Number"
                                               value={this.state.salary} onChange={this.salaryHandler}/>
                                    </div>
                                    <button className="btn btn-success" onClick={this.saveWaiter.bind(this)}>Kaydet</button>
                                    <button className="btn btn-danger" onClick={this.cancel.bind(this)} style={{marginLeft:"10px"}}>Iptal</button>
                                </form>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default CreateWaiterComponent;