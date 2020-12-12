import React, {Component} from 'react';
import WaiterService from "../../services/WaiterService";
import HeaderComponent from "../homepage/HeaderComponent";
import createBrowserHistory from 'history/createBrowserHistory';
import BackofficeContext from "../../BackofficeContext";
import FullPageLoading from "../loading/FullPageLoading";
const history = createBrowserHistory({forceRefresh:true});

class ViewWaiterComponent extends Component {
    static contextType = BackofficeContext;
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
            waiter: {},
            token:'',
            loading:false
        }
    }
    componentDidMount() {
        this.setState({loading: true})
        const userToken = this.context;
        if(localStorage.getItem("token")==null){
            if(userToken.token.length>0){
                this.state.token=userToken.token;

                console.log(this.state.token)
            }
            else{
                history.push('/');
            }
        }
        else {
            this.state.token=localStorage.getItem("token")
        }

        WaiterService.viewWaiter(this.state.id,this.state.token).then(res=>{
            this.setState({waiter:res.data,loading:false})

        })

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
                { this.state.loading ? <FullPageLoading/> : null}
            </div>

        );
    }
}

export default ViewWaiterComponent;