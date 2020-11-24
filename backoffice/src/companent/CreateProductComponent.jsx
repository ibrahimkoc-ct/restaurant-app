import React, {Component} from 'react';
import ProductService from "../services/ProductService";
import ListComponent from "./ListComponent";
import HeaderComponent from "./HeaderComponent";
import FooterComponent from "./FooterComponent";
import {BrowserRouter as Router} from "react-router-dom";
import CategoryService from "../services/CategoryService";
class CreateProductComponent extends Component {
    constructor(props) {
        super(props);
        this.state ={
            id:'',
            title:'',
            description:'',
            category:'',
            price:'',
            categoryid:'',
            categorylist:[],
            categoryName:"Kategori Seçiniz"
        }

        this.chargeTitleHandler=this.chargeTitleHandler.bind(this);
        this.chargeDescriptionHandler=this.chargeDescriptionHandler.bind(this);
        this.chargeCategoryHandler=this.chargeCategoryHandler.bind(this);
        this.chargePriceHandler=this.chargePriceHandler.bind(this);
        this.saveProduct=this.saveProduct.bind(this);
    }
    saveProduct = (e) =>{
        e.preventDefault()
        let product={id:this.state.id,title: this.state.title,description: this.state.description,category: this.state.category,price: this.state.price};
        console.log('product=>'+JSON.stringify(product));
        ProductService.addProductId(product,this.state.categoryid).then(res =>{
            this.props.history.push('/products');
        })
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
    cancel(){
        this.props.history.push('/products');
    }

    componentDidMount() { CategoryService.getCategory().then((res)=>{
        this.setState({categorylist:res.data});
    });
    }
    onClickItem(e){
        this.setState({categoryid:e.id,
            categoryName:e.name})

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
                                        <label>Urun Fiyatı</label>
                                        <input placeholder="Urun fiyatı" name="price" className="form-control"
                                               value={this.state.price} onChange={this.chargePriceHandler}/>
                                    </div>
                                    <div className="dropdown show">
                                        <a className="btn btn-secondary btn-block dropdown-toggle" href="#" role="button" id="dropdownMenuLink"
                                           data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                            {this.state.categoryName}
                                        </a>

                                        <div className="dropdown-menu btn-block" aria-labelledby="dropdownMenuLink">
                                            {
                                                this.state.categorylist.map(
                                                    category =>
                                                        <a className="dropdown-item" onClick={this.onClickItem.bind(this,category)}>{category.name}</a>
                                                )
                                            }

                                        </div>
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