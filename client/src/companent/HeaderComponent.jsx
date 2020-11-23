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
        return (
            <div>
                <header>
                    <nav className="navbar navbar-expand-md navbar-dark bg-dark">
                        <div><a href="https://localhost:4040" className="navbar-brand">Urun Siparis Uygulaması</a> </div>
                            <a className="usernamepage">Kullanıcı: {sessionStorage.getItem("key")}</a>

                    </nav>

                </header>
            </div>
        );
    }
}

export default HeaderComponent;