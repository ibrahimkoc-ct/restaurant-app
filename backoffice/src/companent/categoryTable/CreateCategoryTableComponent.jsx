import React, {Component} from 'react';
import HeaderComponent from "../homepage/HeaderComponent";
import FooterComponent from "../homepage/FooterComponent";
import CategoryTable from "../../services/CategoryTable";
import BackofficeContext from "../../BackofficeContext";
import createBrowserHistory from 'history/createBrowserHistory';
import FullPageLoading from "../loading/FullPageLoading";
import axios from "axios";

const history = createBrowserHistory({forceRefresh: true});

class CreateCategoryTableComponent extends Component {
    static contextType = BackofficeContext;

    constructor(props) {
        super(props);
        this.state = {
            name: '',
            description: '',
            tableAmount: '',
            token: '',
            products: [],
            media: [],
            mediaSelect: {},
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
        axios.get("http://localhost:8080/file").then((res) => {
            this.setState({media: res.data, loading: false});
        });
    }

    changeSelect = (media) => {
        this.state.mediaSelect = media;
    }

    saveCategory = (e) => {
        this.setState({loading: true})
        let category = {
            name: this.state.name,
            description: this.state.description,
            products: this.state.products,
            tableAmount: this.state.tableAmount,
            mediaDTO: this.state.mediaSelect
        };
        CategoryTable.addCategory(category, this.state.token).then(res => {
            this.props.history.push('/categorytable-table');
            this.setState({loading: false})
        }).catch(this.setState({loading:false}))
        e.preventDefault()
    }
    debugBase64 = (base64URL) => {
        var win = window.open();
        win.document.write('<iframe src="' + base64URL + '" frameborder="0" style="border:0; top:0px; left:0px; bottom:0px; right:0px; width:100%; height:100%;" allowfullscreen></iframe>');
    }
    changeInput = (e) => {
        this.setState({
            [e.target.name]: e.target.value
        })
    }
    cancel() {
        this.props.history.push('/categorytable-table');
    }
    addCategoryTable = () => {
        if (!this.state.media) {
            return <h2>Media verisi yok! Lütfen bir media verisi yükleyin..</h2>
        }
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
                        <label>Kategori Resmi</label>
                        <div className="form-group">
                            <div className="form-check" style={{height: "4rem", overflow: "auto"}}>
                                {
                                    this.state.media.map(
                                        media =>
                                            <div key={media.name}
                                                 className="row col-md -12 custom-control custom-radio">
                                                <input className="form-check-input"
                                                       name="customRadio" type="radio"
                                                       onClick={() => this.changeSelect(media)}/>
                                                <label className="form-check-label">
                                                    <a onClick={() => this.debugBase64('data:image/png;base64,' + media.fileContent)}>{media.name}</a></label>
                                            </div>
                                    )
                                }
                            </div>
                        </div>
                    </div>
                    <div className="form-group">
                        <label>Masa Sayisi</label>
                        <input type="number" placeholder="Masa Sayisi" name="tableAmount"
                               className="form-control"
                               value={this.state.tableAmount} onChange={this.changeInput}/>
                    </div>
                    <button className="btn btn-success" onClick={this.saveCategory.bind(this)}>Kaydet</button>
                    <button className="btn btn-danger" onClick={this.cancel.bind(this)}
                            style={{marginLeft: "10px"}}>Iptal
                    </button>
                </form>
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
                            <h3 className="text-center">Kategori Ekle</h3>
                            {this.addCategoryTable()}
                        </div>
                    </div>
                </div>
                <FooterComponent/>
                {this.state.loading ? <FullPageLoading/> : null}
            </div>
        );
    }
}

export default CreateCategoryTableComponent;