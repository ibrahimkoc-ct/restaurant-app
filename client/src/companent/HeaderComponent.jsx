import React, {Component} from 'react';
import ReactDOM from 'react-dom';
import { BrowserRouter as Router, Switch, Route } from 'react-router-dom';
import { createBrowserHistory } from 'history';



class HeaderComponent extends Component {
    constructor(props) {
        super(props);
        this.state = {}
    }
    Exitbutton(){
        sessionStorage.removeItem("token")
    }
    waiterbutton(){
        const history = createBrowserHistory();
        history.push('/homepage');
        localStorage.setItem("waiter","Seçili Garson Yok");
        window.location.reload(false);





    }
    render() {
        return (
            <div>
                <header>
                    <nav className="navbar navbar-expand-md navbar-dark bg-dark">
                        <div><a href="https://localhost:4040" className="navbar-brand">Urun Siparis Uygulaması</a> </div>
                        <button onClick={this.waiterbutton} className="usernamepage btn btn-outline-danger">Garson: {localStorage.getItem("waiter")}</button>

                    </nav>

                </header>
            </div>
        );
    }
}

export default HeaderComponent;