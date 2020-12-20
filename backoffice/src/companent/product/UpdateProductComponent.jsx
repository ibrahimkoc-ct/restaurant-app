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
            category: '',
            urlToImage: '',
            price: '',
            token: '',
            loading: false,
            categorylist: [],
            selectedCategory: [],
            media: [],
            mediaSelect: {},
        }

        this.chargeTitleHandler = this.chargeTitleHandler.bind(this);
        this.chargeDescriptionHandler = this.chargeDescriptionHandler.bind(this);
        this.chargeCategoryHandler = this.chargeCategoryHandler.bind(this);
        this.chargePriceHandler = this.chargePriceHandler.bind(this);
        this.updateProduct = this.updateProduct.bind(this);
        this.chargeUrlToImageHandler = this.chargeUrlToImageHandler.bind(this);
    }

    async componentDidMount() {

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
        await CategoryService.getCategory(this.state.token).then((res) => {
            this.setState({categorylist: res.data});
        });
        axios.get("http://localhost:8080/file/list").then((res) => {
            this.setState({media: res.data});
        });
    }

    changeSelect = (media) => {
        this.state.mediaSelect = media;
    }


    changeMultiSelect = (category) => {
        this.state.selectedCategory.push(category);
        this.state.category += (category.name + ' ,')
    }

    updateProduct = (e) => {

        this.setState({loading: true})
        let product = {
            id: this.state.id,
            title: this.state.title,
            description: this.state.description,
            category: this.state.categoryName,
            price: this.state.price,
            urlToImage: this.state.urlToImage,
            categories: this.state.selectedCategory,
            mediaDTO: this.state.mediaSelect
        };
        ProductService.updateProduct(product, this.state.id, this.state.token).then(res => {
            this.props.history.push('/products');
            this.setState({loading: false})
        })
        e.preventDefault()
    }
    chargeTitleHandler = (event) => {
        this.setState({title: event.target.value});
    }
    chargeDescriptionHandler = (event) => {
        this.setState({description: event.target.value});
    }
    chargeCategoryHandler = (event) => {
        this.setState({category: event.target.value});
    }
    chargePriceHandler = (event) => {
        this.setState({price: event.target.value});
    }
    chargeUrlToImageHandler = (event) => {
        this.setState({urlToImage: event.target.value})
    }

    cancel() {
        this.props.history.push('/products');
    }

    render() {
        return (
            <div>
                <HeaderComponent/>
                <div className="container">
                    <div className="row">
                        <div className="card col-md-6 offset-md-3 offset-md-3">
                            <h3 className="text-center">Urun Guncelle</h3>
                            <div className="card-body">
                                <form>
                                    <div className="form-group">
                                        <label>Urun Adı</label>
                                        <input placeholder="Urun Adı" name="title" className="form-control"
                                               value={this.state.title} onChange={this.chargeTitleHandler}/>

                                    </div>
                                    <div className="form-group">
                                        <label>Urun Acıklaması</label>
                                        <input placeholder="Urun acıklaması" name="description" className="form-control"
                                               value={this.state.description} onChange={this.chargeDescriptionHandler}/>

                                    </div>
                                    <div className="form-group">
                                        <div className="form-check" style={{height: "4rem", overflow: "auto"}}>
                                            {
                                                this.state.media.map(
                                                    media =>
                                                        <div key={media.name} className="row col-md -12 custom-control custom-radio">
                                                            <input  className="form-check-input"
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
                                               value={this.state.price} onChange={this.chargePriceHandler}/>
                                    </div>
                                    <div className="checkbox" style={{height: "4rem", overflow: "auto"}}>
                                        {
                                            this.state.categorylist.map(
                                                category =>
                                                    <div key={category.name} className="row col-md -12">
                                                        <label><input  type="checkbox" value=""
                                                                                          onClick={() => this.changeMultiSelect(category)}/>{category.name}
                                                        </label>
                                                    </div>
                                            )
                                        }
                                    </div>
                                    <button className="btn btn-success" onClick={this.updateProduct}>Kaydet</button>
                                    <button className="btn btn-danger" onClick={this.cancel.bind(this)}
                                            style={{marginLeft: "10px"}}>Iptal
                                    </button>
                                </form>
                            </div>
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