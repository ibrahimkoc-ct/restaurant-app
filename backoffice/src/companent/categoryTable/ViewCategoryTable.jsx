import React, {Component} from 'react';
import HeaderComponent from "../homepage/HeaderComponent";
import createBrowserHistory from 'history/createBrowserHistory';
import BackofficeContext from "../../BackofficeContext";
import FullPageLoading from "../loading/FullPageLoading";

const history = createBrowserHistory({forceRefresh: true});

class ViewCategoryTable extends Component {
    static contextType = BackofficeContext;

    constructor(props) {
        super(props);
        this.state = {
            categoryList: this.props.history.location.state?.categoryList,
            token: '',
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

    viewCategory = () => {
        return (
            <div className="card-body">
                <div className="row">
                    <h3>Kategori Adı: {this.state.categoryList.name}</h3>
                </div>
                <hr></hr>
                <div className="row">
                    <h3>Kategori Bilgileri: {this.state.categoryList.description}</h3>
                </div>
                <hr></hr>
                <div className="row">
                    <h3>Masa Sayisi: {this.state.categoryList.tableAmount}</h3>
                </div>
                <hr/>
                <div className="row">
                    <h3>Resim: <img
                        src={'data:image/png;base64,' + this.state.categoryList.mediaDTO.fileContent}
                        width="100"/></h3>
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
                    <h2 className="text-center">Masa Kategori Detayları</h2>
                    {this.viewCategory()}
                </div>
                {this.state.loading ? <FullPageLoading/> : null}
            </div>
        );
    }
}

export default ViewCategoryTable;