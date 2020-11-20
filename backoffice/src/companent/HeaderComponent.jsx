import React, {Component} from 'react';
import {Link} from "react-router-dom";

class HeaderComponent extends Component {
    constructor(props) {
        super(props);
        this.state = {}
    }
    render() {
        return (
            <div>
                <header>
                    <nav className="navbar navbar-expand-md navbar-dark bg-dark">

                        <div><a href="https://localhost:3000" className="navbar-brand">Urun Yonetim Uygulaması</a> </div>
                        <Link to='/products'>
                            <button className="btn btn-info" >Urunler</button>
                        </Link>
                        <Link to='/add-product'>
                            <button style={{marginLeft: "10px"}} className="btn btn-info" >Urun Ekle</button>
                        </Link>
                        <Link to ='/salestable' >
                            <button style={{marginLeft: "10px"}} className="btn btn-info" >Satış Tablosu</button>
                        </Link>
                        <Link to ='/add-users' >
                            <button style={{marginLeft: "10px"}} className="btn btn-info" >Kullanıcı Ekle</button>
                        </Link>
                        <Link to ='/user-table' >
                            <button style={{marginLeft: "10px"}} className="btn btn-info" >Kullanıcılar</button>
                        </Link>


                    </nav>
                </header>
            </div>
        );
    }
}

export default HeaderComponent;