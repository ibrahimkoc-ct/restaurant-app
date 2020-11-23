import React, {Component} from 'react';
import {Link} from "react-router-dom";

class HeaderComponent extends Component {
    constructor(props) {
        super(props);
        this.state = {}
        this.Exitbutton=this.Exitbutton.bind(this);
    }
    Exitbutton(){
        sessionStorage.removeItem("token");
        sessionStorage.removeItem("key");



    }
    render() {
        return (
            <div>
                <header>
                    <nav className="navbar navbar-expand-md navbar-dark bg-dark">
                        <Link to='/products'>
                            <div className="navbar-brand">Urun Yonetim Uygulaması </div>
                        </Link>
                        <Link>
                            <button style={{marginLeft: "10px"}} className="btn btn-info" >Kategoriler</button>
                        </Link>
                        <Link to='/products'>
                        <button style={{marginLeft: "10px"}} className="btn btn-info" >Urunler</button>
                    </Link>


                        <Link to ='/salestable' >
                            <button style={{marginLeft: "10px"}} className="btn btn-info" >Raporlar</button>
                        </Link>
                        <Link to='/auth-table'>
                            <button style={{marginLeft: "10px"}} className="btn btn-info" >Yetkiler</button>
                        </Link>
                        <Link to ='/user-table' >
                            <button style={{marginLeft: "10px"}} className="btn btn-info" >Kullanıcılar</button>
                        </Link>
                        <Link to ='/' >
                            <button className="btn btn-danger usernamepage" onClick={()=>this.Exitbutton()}>Çıkıs: {sessionStorage.getItem("key")}</button>
                        </Link>

                    </nav>
                </header>
            </div>
        );
    }
}

export default HeaderComponent;