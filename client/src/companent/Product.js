import React, {Component} from 'react';
import PropTypes from "prop-types";
import Navbar from "./Navbar";

class Product extends Component {
    state = {
        isVisible: false,
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


    render() {
        const {title, id, description, category,price,isVisible} = this.state;

        return (

            <div className="col-md-8 mb-4"style={{backgroundColor :"#1E3093"}}  >
                <hr/>
                <div className="card" style={{backgroundColor :"#1Z1093"}}>
                    <div className="card-header d-flex justify-content-between">
                        <h4 className="d-inline" onClick={this.onClickEvent}>{title}</h4>
                    </div>
                    {isVisible ?
                        <div className="card-body">
                            <p className="card-text" >Ürün Açıklaması :{description}</p>
                            <p className="card-text">Kategori :{category}</p>
                            <p className="card-text">Fiyat :{price}</p>
                            <hr/>
                        </div>:null}

            </div>
                </div>

        );

    }
}
export default Product;