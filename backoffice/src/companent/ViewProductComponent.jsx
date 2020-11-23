import React, {Component} from 'react';
import UserService from "../services/UserService";
import HeaderComponent from "./HeaderComponent";
import ProductService from "../services/ProductService";

class ViewUserComponent extends Component {
    constructor(props) {
        super(props);
        this.state={
            id:this.props.match.params.id,
            product:{}
        }

    }
    componentDidMount() {
        ProductService.getProductById(this.state.id).then(res =>{
            this.setState({product:res.data})

        })

    }

    render() {
        return (
            <div>
                <HeaderComponent/>
                <br></br>
                <div className="card col-md-6 offset-md-3" >
                    <h2 className="text-center">Kullanıcı Detayları</h2>
                    <div className="card-body">
                        <div className="row">
                            <h3>Ürün Adı: {this.state.product.title}</h3>
                        </div>
                        <hr></hr>
                        <div className="row">
                            <h3>Ürün İçeriği:  {this.state.product.description}</h3>
                        </div>
                        <hr></hr>
                        <div className="row">
                            <h3>Ürün Kategorisi: {this.state.product.category}</h3>
                        </div>
                        <hr></hr>
                        <div className="row">
                            <h3>Ürün Fiyatı: {this.state.product.price}</h3>
                        </div>



                    </div>
                </div>
            </div>

        );
    }
}

export default ViewUserComponent;