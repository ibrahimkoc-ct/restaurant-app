import React, {Component} from 'react';
import ProductService from "../services/ProductService";
import ListComponent from "./ListComponent";
import HeaderComponent from "./HeaderComponent";
import FooterComponent from "./FooterComponent";
import {BrowserRouter as Router} from "react-router-dom";
import CategoryService from "../services/CategoryService";
import createBrowserHistory from 'history/createBrowserHistory';
import BackofficeContext from "../BackofficeContext";
const history = createBrowserHistory({forceRefresh:true});

class CreateProductComponent extends Component {
    static contextType = BackofficeContext;
    constructor(props) {
        super(props);
        this.state ={
            id:'',
            title:'',
            description:'',
            category:'',
            price:'',
            urlToImage:'',
            categoryid:'',
            categorylist:[],
            categoryName:"",
            token:'',
            selectedCategory:[]
        }

        this.chargeTitleHandler=this.chargeTitleHandler.bind(this);
        this.chargeDescriptionHandler=this.chargeDescriptionHandler.bind(this);
        this.chargeCategoryHandler=this.chargeCategoryHandler.bind(this);
        this.chargePriceHandler=this.chargePriceHandler.bind(this);
        this.saveProduct=this.saveProduct.bind(this);
        this.chargeUrlToImageHandler=this.chargeUrlToImageHandler.bind(this);
    }
    componentDidMount() {
        const userToken = this.context;
        if(localStorage.getItem("token")==null){
            if(userToken.token.length>0){
                this.state.token=userToken.token;

                console.log(this.state.token)
            }
            else{
                history.push('/');
            }
        }
        else {
            this.state.token=localStorage.getItem("token")
        }

        CategoryService.getCategory(this.state.token).then((res) => {
            this.setState({categorylist: res.data});
        });

    }



    saveProduct = (e) =>{
        console.log(this.state.categorylist)

        let product={id:this.state.id,title: this.state.title,description: this.state.description,category: this.state.categoryName,price: this.state.price,urlToImage: this.state.urlToImage,categories:this.state.selectedCategory};
        console.log('product=>'+JSON.stringify(product));
        ProductService.addProductId(product,1,this.state.token).then(res =>{
            this.props.history.push('/products');
        })
        e.preventDefault()
    }
    chargeTitleHandler =(event) =>{
        this.setState({title:event.target.value});
    }
    chargeDescriptionHandler =(event) =>{
        this.setState({description:event.target.value});
    }
    chargeCategoryHandler =(event) =>{
        this.setState({category:event.target.value});
    }
    chargePriceHandler =(event) =>{
        this.setState({price:event.target.value});
    }
    chargeUrlToImageHandler =(event)=>{
        this.setState({urlToImage:event.target.value})
    }
    cancel(){
        this.props.history.push('/products');
    }


    changeMultiSelect=(category)=>{
        this.state.selectedCategory.push(category);
        console.log(this.state.selectedCategory)
        this.state.categoryName+=(category.name+' ,')
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
                                        <label>Urun Resmi</label>
                                        <input placeholder="Urun resmi" name="description" className="form-control"
                                               value={this.state.urlToImage} onChange={this.chargeUrlToImageHandler}/>

                                    </div>

                                    <div className="form-group">
                                        <label>Urun Fiyatı</label>
                                        <input placeholder="Urun fiyatı" name="price" className="form-control"
                                               value={this.state.price} onChange={this.chargePriceHandler}/>
                                    </div>
                                    <div className="checkbox" style={{height:"4rem",overflow:"auto"}}>
                                        {
                                            this.state.categorylist.map(
                                                category=>
                                                    <div className="row col-md -12">
                                                        <label><input type="checkbox" value="" onClick={()=>this.changeMultiSelect(category)}/>{category.name}</label>
                                                    </div>
                                            )
                                        }
                                    </div>

                                    <hr/>
                                    <button className="btn btn-success" onClick={this.saveProduct}>Kaydet</button>
                                    <button className="btn btn-danger" onClick={this.cancel.bind(this)} style={{marginLeft:"10px"}}>Iptal</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
                <FooterComponent/>
            </div>
        );
    }
}

export default CreateProductComponent;