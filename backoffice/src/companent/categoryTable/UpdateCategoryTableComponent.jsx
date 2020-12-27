import React, {Component} from 'react';
import HeaderComponent from "../homepage/HeaderComponent";
import FooterComponent from "../homepage/FooterComponent";
import CategoryTable from "../../services/CategoryTable";
import createBrowserHistory from 'history/createBrowserHistory';
import BackofficeContext from "../../BackofficeContext";
import FullPageLoading from "../loading/FullPageLoading";

const history = createBrowserHistory({forceRefresh: true});

class UpdateCategoryTableComponent extends Component {
    static contextType = BackofficeContext;

    constructor(props) {
        super(props);
        this.state = {
            categoryList: this.props.history.location.state?.categoryList,
            name: '',
            description: '',
            tableAmount: '',
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

    changeInput = (e) => {
        this.setState({
            [e.target.name]: e.target.value
        })
    }

    cancel() {
        this.props.history.push('/categorytable-table');
    }

    updateCategory = (e) => {
        this.setState({loading: true})
        let category = {
            id: this.state.categoryList.id,
            name: this.state.name,
            description: this.state.description,
            tableAmount: this.state.tableAmount,
            mediaDTO: this.state.categoryList.mediaDTO,
        };
        CategoryTable.updateCategory(category, this.state.token).then(res => {
            this.props.history.push('/categorytable-table');
            this.setState({loading: false})
        })
        e.preventDefault()
    }

    updateCategoryTableForm = () => {
        return (
            <div className="card-body">
                <form>

                    <div className="form-group">
                        <label>Kategori Adı</label>
                        <input placeholder="Kategori Adı" name="name" className="form-control"
                               value={this.state.name} onChange={this.changeInput}/>
                    </div>
                    <div className="form-group">
                        <label>Kategori Acıklaması</label>
                        <input placeholder="Kategori acıklaması" name="description"
                               className="form-control"
                               value={this.state.description} onChange={this.changeInput}/>

                    </div>
                    <div className="form-group">
                        <label>Masa Sayisi</label>
                        <input placeholder="Masa Sayisi" name="tableAmount" className="form-control"
                               value={this.state.tableAmount} onChange={this.changeInput}/>
                    </div>
                </form>
                <button className="btn btn-success" onClick={this.updateCategory.bind(this)}>Kaydet</button>
                <button className="btn btn-danger" onClick={this.cancel.bind(this)}
                        style={{marginLeft: "10px"}}>Iptal
                </button>
            </div>
        )
    }

    render() {
        return (
            <div>
                <HeaderComponent/>
                <div className="container">
                    <div className="row">
                        <div className="card col-md-6 offset-md-3 offset-md-3">
                            <h3 className="text-center">Kategori Güncelle</h3>
                            {this.updateCategoryTableForm()}
                        </div>
                    </div>
                </div>
                <FooterComponent/>
                {this.state.loading ? <FullPageLoading/> : null}
            </div>
        );
    }
}

export default UpdateCategoryTableComponent;