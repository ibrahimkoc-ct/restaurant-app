import React, {Component} from 'react';
import HeaderComponent from "../homepage/HeaderComponent";
import createBrowserHistory from 'history/createBrowserHistory';
import BackofficeContext from "../../BackofficeContext";
import FullPageLoading from "../loading/FullPageLoading";

const history = createBrowserHistory({forceRefresh: true});

class ViewUserComponent extends Component {
    static contextType = BackofficeContext;

    constructor(props) {
        super(props);
        this.state = {
            role: this.props.history.location.state?.role,
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
    viewAuthForm=()=>{
        return(
            <div className="card-body">
                <div className="row">
                    <h3>Rol id: {this.state.role.id}</h3>
                </div>
                <hr></hr>
                <div className="row">
                    <h3>Rol Adı: {this.state.role.name}</h3>
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
                    <h2 className="text-center">Yetkinlik Detayları</h2>
                    {this.viewAuthForm()}
                </div>
                {this.state.loading ? <FullPageLoading/> : null}
            </div>

        );
    }
}

export default ViewUserComponent;