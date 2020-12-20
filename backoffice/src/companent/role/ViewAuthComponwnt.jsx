import React, {Component} from 'react';
import UserService from "../../services/UserService";
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
            id: this.props.match.params.id,

            user: {},
            token: '',
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


        UserService.getAuthById(this.state.id, this.state.token).then(res => {
            this.setState({user: res.data, loading: false})

        })
    }

    render() {
        return (
            <div>
                <HeaderComponent/>
                <br></br>
                <div className="card col-md-6 offset-md-3">
                    <h2 className="text-center">Yetkinlik Detayları</h2>
                    <div className="card-body">
                        <div className="row">
                            <h3>Rol id: {this.state.user.id}</h3>
                        </div>
                        <hr></hr>
                        <div className="row">

                            <h3>Rol Adı: {this.state.user.name}</h3>
                        </div>

                    </div>
                </div>
                {this.state.loading ? <FullPageLoading/> : null}
            </div>

        );
    }
}

export default ViewUserComponent;