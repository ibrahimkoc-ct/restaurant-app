import React, {Component} from 'react';
import WaiterService from "../services/WaiterService";
import HeaderComponent from "./HeaderComponent";

class ViewWaiterComponent extends Component {
    constructor(props) {
        super(props);
        this.state = {
            id:this.props.match.params.id,
            name: '',
            phoneNumber: '',
            mail: '',
            address: '',
            urlToImage: '',
            salary: '',
            waiter: {}
        }
    }
    componentDidMount() {
        WaiterService.viewWaiter(this.state.id).then(res=>{
            this.setState({waiter:res.data})

        })
        console.log(this.state.waiter)
    }

    render() {
        return (
            <div>
                <HeaderComponent/>
                <br></br>
                <div className="card col-md-6 offset-md-3" >
                    <h2 className="text-center">Garson Detayları</h2>
                    <div className="card-body">
                        <div className="row">
                            <h3>Garson Adı: {this.state.waiter.name}</h3>
                        </div>
                        <hr></hr>
                        <div className="row">

                            <h3>Telefon numarası:  {this.state.waiter.phoneNumber}</h3>
                        </div>
                        <hr></hr>
                        <div className="row">

                            <h3>Mail Adresi:  {this.state.waiter.mail}</h3>
                        </div>
                        <hr></hr>
                        <div className="row">

                            <h3>Adres:  {this.state.waiter.address}</h3>
                        </div>
                        <hr></hr>
                        <div className="row">

                            <h3>Maas:  {this.state.waiter.salary}</h3>
                        </div>
                        <hr></hr>
                        <div className="row">
                            <h3>Resim: <img src={this.state.waiter.urlToImage}/></h3>
                        </div>



                    </div>
                </div>
            </div>

        );
    }
}

export default ViewWaiterComponent;