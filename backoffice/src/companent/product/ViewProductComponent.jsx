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
            product: this.props.history.location.state?.product,
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

    render() {
        return (
            <div>
                <HeaderComponent/>
                <br></br>
                <div className="card col-md-6 offset-md-3">
                    <h2 className="text-center">Kullanıcı Detayları</h2>
                    <div className="card-body">
                        <div className="row">
                            <h3>Ürün Adı: {this.state.product.title}</h3>
                        </div>
                        <hr></hr>
                        <div className="row">
                            <h3>Ürün İçeriği: {this.state.product.description}</h3>
                        </div>
                        <hr></hr>
                        <div className="row">
                            <h3>Ürün Kategorisi: {this.state.product.category}</h3>
                        </div>

                        <hr></hr>
                        <div className="row">
                            <h3>Ürün Fiyatı: {this.state.product.price}</h3>
                        </div>
                        <hr></hr>
                        <div className="row">
                            <h3>Ürün Resmi:
                                <img
                                    src={'data:image/png;base64,' + this.state.product.mediaDTO.fileContent}
                                    width="100"/></h3>
                        </div>


                    </div>
                </div>
                {this.state.loading ? <FullPageLoading/> : null}
            </div>

        );
    }
}

export default ViewUserComponent;