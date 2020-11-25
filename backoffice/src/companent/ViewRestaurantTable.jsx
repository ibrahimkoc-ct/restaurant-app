import React, {Component} from 'react';
import ProductService from "../services/ProductService";
import RestaurantTableService from "../services/RestaurantTableService";
import HeaderComponent from "./HeaderComponent";

class ViewRestaurantTable extends Component {
    constructor(props) {
        super(props);
        this.state={
            id:this.props.match.params.id,
            product:{}
        }

    }
    componentDidMount() {
        RestaurantTableService.getTableById(this.state.id).then(res =>{
            this.setState({product:res.data})

        })

    }
    render() {
        return (
            <div>
                <HeaderComponent/>
                <br></br>
                <div className="card col-md-6 offset-md-3" >
                    <h2 className="text-center">Masa Detayları</h2>
                    <div className="card-body">
                        <div className="row">
                            <h3>Masa Adı: {this.state.product.title}</h3>
                        </div>
                        <hr></hr>
                        <div className="row">
                            <h3>Masa Açıklaması:  {this.state.product.description}</h3>
                        </div>
                        <hr></hr>
                        <div className="row">
                            <h3>Ürün Kategorisi: {this.state.product.category}</h3>
                        </div>
                        <hr></hr>




                    </div>
                </div>

            </div>
        );
    }
}

export default ViewRestaurantTable;