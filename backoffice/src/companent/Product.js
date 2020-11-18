import React, {Component} from 'react';
import PropTypes from "prop-types";
import axios from "axios";

class Product extends Component {

    state = {
        isVisible: false,
        isUpdate: false,
        title:this.props.title,
        description:this.props.description,
        category:this.props.category,
        price:this.props.price,
        id:this.props.id
    }
    onClickEvent = (e) => {
        this.setState({
            isVisible: !this.state.isVisible,

        })
    }
    changeInput =(e) => {
        this.setState({
            [e.target.name]: e.target.value
        })
    }
    addProduct =(e) => {

        e.preventDefault();
        console.log("test")
        const {id, title, description, category, price} = this.state;
        const newProduct = {
            id:id,
            title: title,
            description: description,
            category: category,
            price: price
        }
        const response = axios.put('http://localhost:8080/product/update/'+id, newProduct)
        console.log(newProduct);
        window.location.reload();

    }
    deleteClickEvent =(e) =>{
        window.location.reload();
        return fetch('http://localhost:8080/product/delete/'+e.id, {
            method: 'DELETE',

        }).then(response => response.json())
        window.location.reload();

    }
    updateOnClick =(e) => {
        this.setState({
            isUpdate: !this.state.isUpdate
        })
    }
    render() {
        const {title, id, description, category,price,isUpdate,isVisible} = this.state;

        return (
            <div className="col-md-8 mb-4">
                <div className="card">
                    <div className="card-header d-flex justify-content-between">
                        <i className="fas fa-edit" onClick={this.updateOnClick}></i>
                        <h4 className="d-inline" onClick={this.onClickEvent}>{title}</h4>
                        <i className="far fa-trash-alt" style={{cursor: "pointer"}} onClick={this.deleteClickEvent.bind(this,{id})}></i>
                    </div>
                    {isVisible ?
                        <div className="card-body">
                            <p className="card-text">Ürün Adı: :{title}</p>
                            <p className="card-text">Ürün Açıklaması :{description}</p>
                            <p className="card-text">Kategori :{category}</p>
                            <p className="card-text">Fiyat :{price}</p>
                        </div> : null}
                    {isUpdate?
                        <form onSubmit={this.addProduct}>
                            <div className="form-group">
                                <label htmlFor="title">Ürün Adı</label>
                                <input
                                    type="text"
                                    name="title"
                                    value={title}
                                    id="title"
                                    placeholder="Ürün adı giriniz"
                                    className="form-control"
                                    onChange={this.changeInput}
                                />
                            </div>
                            <div className="form-group">
                                <label htmlFor="description">Ürün Açıklaması</label>
                                <input
                                    type="text"
                                    name="description"
                                    value={description}
                                    placeholder="Ürün açıklaması giriniz"
                                    className="form-control"
                                    onChange={this.changeInput}
                                />

                            </div>
                            <div className="form-group">
                                <label htmlFor="category">Kategori</label>
                                <input
                                    type="text"
                                    name="category"
                                    placeholder={category}
                                    value={category}
                                    className="form-control"
                                    onChange={this.changeInput}
                                />
                            </div>
                            <div className="form-group">
                                <label htmlFor="price">Fiyat</label>
                                <input
                                    type="text"
                                    name="price"
                                    placeholder={price}
                                    value={price}
                                    className="form-control"
                                    onChange={this.changeInput}
                                />


                            </div>
                            <button className="btn btn-danger btn-block" type ="submit" >Ürünü Güncelle</button>
                        </form>
                        :null }
                </div>
            </div>
        );
    }
}
Product.propTypes ={
    title:PropTypes.string.isRequired,
    description:PropTypes.string.isRequired,
    category:PropTypes.string.isRequired,
    price:PropTypes.string.isRequired

}
Product.defaultProps ={
    title:"Bilgi yok",
    description:"Bilgi yok",
    author:"Bilgi yok",
    price:"Bilgi yok"

}

export default Product;