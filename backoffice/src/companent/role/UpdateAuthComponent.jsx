import React, {Component} from 'react';
import UserService from "../../services/UserService";
import HeaderComponent from "../homepage/HeaderComponent";
import FooterComponent from "../homepage/FooterComponent";
import createBrowserHistory from 'history/createBrowserHistory';
import BackofficeContext from "../../BackofficeContext";
import FullPageLoading from "../loading/FullPageLoading";
const history = createBrowserHistory({forceRefresh:true});


class UpdateAuthComponent extends Component {
    static contextType = BackofficeContext;
    constructor(props) {
        super(props);
        this.state = {
            id: this.props.match.params.id,
            name:'',
            token:'',
            loading:false,
        }
        this.chargeUsernameHandler=this.chargeUsernameHandler.bind(this);
        this.updateAuth=this.updateAuth.bind(this);
    }
    componentDidMount() {
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

    }
    updateAuth = (e) =>{
        this.setState({loading: true})
        let auth={id:this.state.id,name: this.state.name};
        UserService.updateAuth(auth,this.state.token).then(res =>{

            console.log('user=>'+JSON.stringify(auth));
            this.props.history.push('/auth-table');
            this.setState({loading: false})
        })
        e.preventDefault()

    }
    cancel(){
        this.props.history.push('/auth-table');
    }
    chargeUsernameHandler =(event) =>{
        this.setState({name:event.target.value});
    }


    render() {
        return (
            <div>
                <HeaderComponent/>
                <div className="container">
                    <div className="row">
                        <div className="card col-md-6 offset-md-3 offset-md-3">
                            <h3 className="text-center">Yetkinlik Düzenle</h3>
                            <div className="card-body">
                                <form>
                                    <div className="form-group">
                                        <label>Kullanıcı Adı</label>
                                        <input placeholder="Degiştirilmez." name="username" className="form-control"
                                               value={this.state.username} onChange={this.chargeUsernameHandler}
                                        />

                                    </div>
                                    <hr/>
                                    <button className="btn btn-success" onClick={this.updateAuth}>Guncelle</button>
                                    <button className="btn btn-danger" onClick={this.cancel.bind(this)} style={{marginLeft:"10px"}}>Iptal</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
                <FooterComponent/>
                { this.state.loading ? <FullPageLoading/> : null}
            </div>
        );
    }
}

export default UpdateAuthComponent;
