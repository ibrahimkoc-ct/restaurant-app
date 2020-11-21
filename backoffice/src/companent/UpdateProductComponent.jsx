import React, {Component} from 'react';
import ProductService from "../services/ProductService";
import HeaderComponent from "./HeaderComponent";
import FooterComponent from "./FooterComponent";
import {BrowserRouter as Router} from "react-router-dom";

class UpdateProductComponent extends Component {
    constructor(props) {
        super(props);
        this.state ={
            id:this.props.match.params.id,
            title:'',
            description:'',
            category:'',
            price:''
        }

        this.chargeTitleHandler=this.chargeTitleHandler.bind(this);
        this.chargeDescriptionHandler=this.chargeDescriptionHandler.bind(this);
        this.chargeCategoryHandler=this.chargeCategoryHandler.bind(this);
        this.chargePriceHandler=this.chargePriceHandler.bind(this);
        this.updateProduct=this.updateProduct.bind(this);
    }

    updateProduct = (e) =>{
        e.preventDefault()
        let product={title: this.state.title,description: this.state.description,category: this.state.category,price: this.state.price};
        console.log('product=>'+JSON.stringify(product));
        ProductService.updateProduct(product,this.state.id).then(res =>{
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
                                        <label>Urun Kategorisi</label>
                                        <input placeholder="Urun kategorisi" name="category" className="form-control"
                                               value={this.state.category} onChange={this.chargeCategoryHandler}/>
                                    </div>
                                    <div className="form-group">
                                        <label>Urun Fiyatı</label>
                                        <input placeholder="Urun fiyatı" name="price" className="form-control"
                                               value={this.state.price} onChange={this.chargePriceHandler}/>
                                    </div>
                                    <button className="btn btn-success" onClick={this.updateProduct}>Kaydet</button>
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

export default UpdateProductComponent;