import React, {Component} from 'react';
import createBrowserHistory from 'history/createBrowserHistory';
import BackofficeContext from "../BackofficeContext";
import UserService from "../services/UserService";
import HeaderComponent from "./HeaderComponent";
import FooterComponent from "./FooterComponent";
const history = createBrowserHistory({forceRefresh:true});

class CreateRoleComponent extends Component {
    static contextType = BackofficeContext;
    constructor(props) {
        super(props);
        this.state={
            role:'',
            token:'',
        }
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
    RoleHandler=(event)=>{
        this.setState({role:event.target.value});

    }
    cancel(){
        this.props.history.push('/auth-table');
    }
    saveRole =(e) =>{
        console.log(this.state.role)
        let us={ name:this.state.role };
        UserService.createAuth(us,this.state.token);
        this.props.history.push('/auth-table');

        e.preventDefault();
    }

    render() {
        return (
            <div>
                <HeaderComponent/>
                <div className="container">
                    <div className="row">
                        <div className="card col-md-6 offset-md-3 offset-md-3">
                            <h3 className="text-center">Urun Ekle</h3>
                            <div className="card-body">
                                <form>
                                    <div className="form-group">
                                        <label>Rol Adı</label>
                                        <input placeholder="Rol Adı" name="title" className="form-control"
                                               value={this.state.role} onChange={this.RoleHandler.bind(this)}/>

                                    </div>
                                    <hr/>
                                    <button className="btn btn-success" onClick={this.saveRole.bind(this)}>Kaydet</button>
                                    <button className="btn btn-danger" onClick={this.cancel.bind(this)} style={{marginLeft:"10px"}}>Iptal</button>
                                </form>
                            </div>
                        </div>

                </div>
                <FooterComponent/>
            </div>


            </div>
        );
    }
}

export default CreateRoleComponent;