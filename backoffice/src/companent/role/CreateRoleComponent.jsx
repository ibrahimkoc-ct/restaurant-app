import React, {Component} from 'react';
import createBrowserHistory from 'history/createBrowserHistory';
import BackofficeContext from "../../BackofficeContext";
import UserService from "../../services/UserService";
import HeaderComponent from "../homepage/HeaderComponent";
import FooterComponent from "../homepage/FooterComponent";
import FullPageLoading from "../loading/FullPageLoading";

const history = createBrowserHistory({forceRefresh: true});

class CreateRoleComponent extends Component {
    static contextType = BackofficeContext;

    constructor(props) {
        super(props);
        this.state = {
            role: '',
            token: '',
            loading: false,
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

    RoleHandler = (event) => {
        this.setState({role: event.target.value});

    }

    cancel() {
        this.props.history.push('/auth-table');
    }

    saveRole = (e) => {
        this.setState({loading: true})
        let us = {name: this.state.role};
        UserService.createAuth(us, this.state.token).then((res) => {
            this.setState({loading: false})
        });
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
                            <h3 className="text-center">Role Ekle</h3>
                            <div className="card-body">
                                <form>
                                    <div className="form-group">
                                        <label>Rol Adı</label>
                                        <input placeholder="Rol Adı" name="title" className="form-control"
                                               value={this.state.role} onChange={this.RoleHandler.bind(this)}/>

                                    </div>
                                    <hr/>
                                    <button className="btn btn-success" onClick={this.saveRole.bind(this)}>Kaydet
                                    </button>
                                    <button className="btn btn-danger" onClick={this.cancel.bind(this)}
                                            style={{marginLeft: "10px"}}>Iptal
                                    </button>
                                </form>
                            </div>
                        </div>

                    </div>
                    <FooterComponent/>
                </div>

                {this.state.loading ? <FullPageLoading/> : null}
            </div>
        );
    }
}

export default CreateRoleComponent;