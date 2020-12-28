import React, {Component} from 'react';
import ProductService from "../../services/ProductService";
import HeaderComponent from "../homepage/HeaderComponent";
import FooterComponent from "../homepage/FooterComponent";
import createBrowserHistory from 'history/createBrowserHistory';
import BackofficeContext from "../../BackofficeContext";
import FullPageLoading from "../loading/FullPageLoading";
import CategoryService from "../../services/CategoryService";
import axios from "axios";

const history = createBrowserHistory({forceRefresh: true});

class UpdateProductComponent extends Component {
    static contextType = BackofficeContext;

    constructor(props) {
        super(props);
        this.state = {
            id: this.props.match.params.id,
            title: '',
            description: '',
            price: '',
            token: '',
            loading: false,
            categorylist: [],
            selectedCategory: [],
            media: [],
            mediaSelect: {},
        }
    }

    async componentDidMount() {
        const userToken = this.context;
        this.setState({loading: true})
        if (localStorage.getItem("token") == null) {
            if (userToken.token.length > 0) {
                this.state.token = userToken.token;
            } else {
                history.push('/');
            }
        } else {
            this.state.token = localStorage.getItem("token")
        }
        await CategoryService.getCategory(this.state.token).then((res) => {
            this.setState({categorylist: res.data});
        });
        axios.get("http://localhost:8080/file").then((res) => {
            this.setState({media: res.data})
        }).catch(this.setState({loading: false}))
    }

    changeSelect = (media) => {
        this.state.mediaSelect = media;
    }

    changeMultiSelect = (category) => {
        if (this.state.selectedCategory.filter(select => select.id === category.id).length > 0) {
            this.state.selectedCategory.splice(category)
        } else {
            this.state.selectedCategory.push(category);
        }
    }

    updateProduct = (e) => {
        this.setState({loading: true})
        let product = {
            id: this.state.id,
            title: this.state.title,
            description: this.state.description,
            price: this.state.price,
            categories: this.state.selectedCategory,
            mediaDTO: this.state.mediaSelect
        };
        ProductService.updateProduct(product, this.state.id, this.state.token).then(res => {
            this.props.history.push('/products');
            this.setState({loading: false})
        })
        e.preventDefault()
    }

    changeInput = (e) => {
        this.setState({
            [e.target.name]: e.target.value
        })
    }

    cancel() {
        this.props.history.push('/products');
    }

    debugBase64 = (base64URL) => {
        var win = window.open();
        win.document.write('<iframe src="' + base64URL + '" frameborder="0" style="border:0; top:0px; left:0px; bottom:0px; right:0px; width:100%; height:100%;" allowfullscreen></iframe>');
    }
    updateProductForm = () => {
        if (!this.state.categorylist || !this.state.media) {
            return (<h2>Bir hata oluştu. Lütfen daha sonra tekrar deneyiniz.</h2>)
        }
        return (
            <div className="card-body">
                <form>
                    <div className="form-group">
                        <label>Urun Adı</label>
                        <input placeholder="Urun Adı" name="title" className="form-control"
                               value={this.state.title} onChange={this.changeInput}/>
                    </div>
                    <div className="form-group">
                        <label>Urun Acıklaması</label>
                        <input placeholder="Urun acıklaması" name="description" className="form-control"
                               value={this.state.description} onChange={this.changeInput}/>
                    </div>
                    <div className="form-group">
                        <div className="form-check" style={{height: "4rem", overflow: "auto"}}>
                            {
                                this.state.media.map(
                                    media =>
                                        <div key={media.name} className="row col-md -12 custom-control custom-radio">
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
                    <div className="form-group">
                        <label>Urun Fiyatı</label>
                        <input placeholder="Urun fiyatı" name="price" className="form-control"
                               value={this.state.price} onChange={this.changeInput}/>
                    </div>
                    <div className="checkbox" style={{height: "4rem", overflow: "auto"}}>
                        {
                            this.state.categorylist.map(
                                category =>
                                    <div key={category.name} className="row col-md -12">
                                        <label><input type="checkbox" value=""
                                                      onClick={() => this.changeMultiSelect(category)}/>{category.name}
                                        </label>
                                    </div>
                            )
                        }
                    </div>
                    <button className="btn btn-success" onClick={this.updateProduct.bind(this)}>Kaydet</button>
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
                            <h3 className="text-center">Urun Guncelle</h3>
                            {this.updateProductForm()}
                        </div>
                    </div>
                </div>
                <FooterComponent/>
                {this.state.loading ? <FullPageLoading/> : null}
            </div>
        );
    }
}

export default UpdateProductComponent;