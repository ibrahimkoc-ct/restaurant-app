import React, {Component} from 'react';
import HeaderComponent from "./HeaderComponent";
import FooterComponent from "./FooterCompanent";
import {Link} from "react-router-dom";
import "./App2.css";

class HomePageCompanent extends Component {
    signOut = (e) => {
        e.preventDefault()


        sessionStorage.removeItem("token")
        this.props.history.push('/');
    }
    render() {
        return (

            <div className="HomePage">
                <HeaderComponent/>
                <Link to="/homepage">
                    <button className="btn btn-info backbutton fas fa-edit" ></button>
                </Link>
                <Link>
                    <button className="btn btn-secondary homebutton">SEPET</button>
                </Link>
                <Link to ="/table">
                    <button className="btn btn-secondary homebutton">MASALAR</button>
                </Link>
                <Link>
                    <button className="btn btn-secondary homebutton">RAPORLAR</button>
                </Link>
                <Link to="/products">
                    <button className="btn btn-secondary homebutton">ÜRÜNLER</button>
                </Link>
                <Link>
                    <button className="btn btn-secondary homebutton">KULLANICILAR</button>
                </Link>
                <Link>
                    <button className="btn btn-secondary homebutton"></button>
                </Link>

                <Link>
                    <button className="btn btn-secondary homebutton"></button>
                </Link>
                <Link>
                <button className="btn btn-secondary homebutton"></button>
            </Link>
                <Link>
                    <button className="btn btn-secondary homebutton" onClick={this.signOut}>ÇIKIŞ</button>
                </Link>


            </div>
        );
    }
}

export default HomePageCompanent;