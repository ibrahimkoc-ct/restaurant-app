import React, {Component} from 'react';
import {Link} from "react-router-dom";

class HeaderComponent extends Component {
    constructor(props) {
        super(props);
        this.state = {}
    }
    Exitbutton(){
        sessionStorage.removeItem("token")
    }
    render() {
        <HeaderComponent/>
        return (
            <div>
                <header>
                    <nav className="navbar navbar-expand-md navbar-dark bg-dark">
                        <div><a href="https://localhost:4040" className="navbar-brand">Urun Siparis Uygulaması</a> </div>

                    </nav>
                    <Link to ='/' >
                        <button style={{marginLeft: "10px"}} className="btn btn-danger" onClick={()=>this.Exitbutton()}>Çıkıs</button>
                    </Link>
                </header>
            </div>
        );
    }
}

export default HeaderComponent;