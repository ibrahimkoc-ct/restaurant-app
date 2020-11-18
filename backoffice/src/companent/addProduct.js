import React, {Component} from 'react';
import axios from 'axios';
class AddProduct extends Component {
    state = {
        isVisible: false,
        title: "",
        description: "",
        price: "",
        category: "",
        id: ""
    }
    onClickEvent = (e) => {
        this.setState({
            isVisible: !this.state.isVisible
        })
    }
    changeInput =(e) =>{
        this.setState({
            [e.target.name] :e.target.value
        })
    }
    addProduct =(e) => {
        e.preventDefault();
        window.location.reload();
        console.log("test")
        const {id, title, description, category, price} = this.state;
        const newProduct = {
            id:id,
            title: title,
            description: description,
            category: category,
            price: price,
        }
        const response = axios.post("http://localhost:8080/product/add", newProduct)
        console.log(newProduct);
        window.location.reload();
    }
    componentDidMount = async () =>{
        const response= await axios.get("http://localhost:8080/product/list")
        console.log(response)
    }
    render() {
        const {isVisible,id,title,description,price,category} = this.state;
        return (
            <div className="col-md-8 mb-4">
                <div className="card">
                    <div className="card-header"onClick={this.onClickEvent}>

                        <h4>Yeni Ürün Ekle<i className="fas fa-plus" ></i></h4>

                    </div>
                    {isVisible ?
                        <div className="card-body">
                            <form onSubmit={this.addProduct}>
                                <div className="form-group">


                                </div>
                                <div className="form-group">
                                    <label htmlFor="title">Ürün Adı</label>
                                    <input
                                        type="text"
                                        name="title"
                                        placeholder="Ürün adı giriniz"
                                        className="form-control"
                                        value={title}
                                        onChange={this.changeInput}

                                    />

                                </div>
                                <div className="form-group">
                                    <label htmlFor="description">Ürün Detayı</label>
                                    <input
                                        type="text"
                                        name="description"
                                        placeholder="Haber Detayı giriniz"
                                        className="form-control"
                                        value={description}
                                        onChange={this.changeInput}

                                    />

                                </div>
                                <div className="form-group">
                                    <label htmlFor="category">Kategori</label>
                                    <input
                                        type="text"
                                        name="category"
                                        placeholder="Kategori giriniz"
                                        className="form-control"
                                        value={category}
                                        onChange={this.changeInput}
                                    />

                                </div>
                                <div className="form-group">
                                    <label htmlFor="price">Fiyat</label>
                                    <input
                                        type="text"
                                        name="price"
                                        placeholder="Ürün fiyati giriniz"
                                        className="form-control"
                                        value={price}
                                        onChange={this.changeInput}

                                    />

                                </div>
                                <button className="btn btn-danger btn-block" type ="submit">Ürünü Ekle</button>

                            </form>

                        </div> :null}
                </div>
            </div>
        );
    }
}

export default AddProduct;