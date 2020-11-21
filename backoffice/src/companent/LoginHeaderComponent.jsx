import React, {Component} from 'react';
import ListComponent from "./ListComponent";
import {Route} from "react-router-dom";
import LoginComponent from "./LoginComponent";

class LoginHeaderComponent extends Component {
    constructor(props) {
        super(props);
        this.state = {}
    }
    render() {
        return (
            <div>
                    <header>
                        <nav className="navbar navbar-expand-md navbar-dark bg-dark">

                            <div><a href="/" className="navbar-brand">Urun Yonetim Uygulaması</a> </div>

                        </nav>
                    </header>
            </div>

        );
    }
}

export default LoginHeaderComponent;