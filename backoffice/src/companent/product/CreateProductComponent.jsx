import React, {Component} from 'react';
import ProductService from "../../services/ProductService";
import HeaderComponent from "../homepage/HeaderComponent";
import FooterComponent from "../homepage/FooterComponent";
import CategoryService from "../../services/CategoryService";
import createBrowserHistory from 'history/createBrowserHistory';
import BackofficeContext from "../../BackofficeContext";
import axios from "axios";
import FullPageLoading from "../loading/FullPageLoading";

const history = createBrowserHistory({forceRefresh: true});

class CreateProductComponent extends Component {
    static contextType = BackofficeContext;

    constructor(props) {
        super(props);
        this.state = {
            id: '',
            title: '',
            description: '',
            category: '',
            price: '',
            urlToImage: '',
            categoryid: '',
            categorylist: [],
            categoryName: "",
            token: '',
            selectedCategory: [],
            media: [],
            mediaSelect: {},
            loading: false

        }

        this.chargeTitleHandler = this.chargeTitleHandler.bind(this);
        this.chargeDescriptionHandler = this.chargeDescriptionHandler.bind(this);
        this.chargeCategoryHandler = this.chargeCategoryHandler.bind(this);
        this.chargePriceHandler = this.chargePriceHandler.bind(this);
        this.saveProduct = this.saveProduct.bind(this);
        this.chargeUrlToImageHandler = this.chargeUrlToImageHandler.bind(this);
    }

    async componentDidMount() {
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

        await CategoryService.getCategory(this.state.token).then((res) => {
            this.setState({categorylist: res.data});
        });
        axios.get("http://localhost:8080/file/list").then((res) => {
            this.setState({media: res.data, loading: false});
        });

    }

    changeSelect = (media) => {
        this.state.mediaSelect = media;
    }


    saveProduct = (e) => {


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
        ProductService.addProductId(product, 1, this.state.token).then(res => {
            this.props.history.push('/products');
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


    changeMultiSelect = (category) => {
        if (this.state.selectedCategory.filter(select => select.id === category.id).length > 0) {
            this.state.selectedCategory.splice(category)
        } else {
            this.state.selectedCategory.push(category);
        }
        this.state.category += (category.name + ' ,')
    }
    debugBase64 = (base64URL) => {
        var win = window.open();
        win.document.write('<iframe src="' + base64URL + '" frameborder="0" style="border:0; top:0px; left:0px; bottom:0px; right:0px; width:100%; height:100%;" allowfullscreen></iframe>');
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
                                        <label>Kategori Resmi</label>
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
                                                    <div className="row col-md -12" key={category.id}>
                                                        <label ><input type="checkbox" value=""
                                                                                        onClick={() => this.changeMultiSelect(category)}/>{category.name}
                                                        </label>
                                                    </div>
                                            )
                                        }
                                    </div>

                                    <hr/>
                                    <button className="btn btn-success" onClick={this.saveProduct}>Kaydet</button>
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

export default CreateProductComponent;