import React, {Component} from 'react';
import ReactDOM from 'react-dom';
import { BrowserRouter as Router, Switch, Route } from 'react-router-dom';
import createBrowserHistory from 'history/createBrowserHistory';
import ClientContext from "../ClientContext";
const history = createBrowserHistory({forceRefresh:true});




class HeaderComponent extends Component {
    static contextType=ClientContext;
    constructor(props) {
        super(props);
        this.state = {
            waiter:'',
        }
    }
    componentDidMount() {
        const{waiter,setWaiter}=this.context
        this.state.waiter=waiter;

    }


    waiterbutton=(e)=>{
        e.preventDefault()

      history.push('/homepage');


    }
    render() {
        return (
            <div>
                <header>
                    <nav className="navbar navbar-expand-md navbar-dark bg-dark">
                        <div><a href="https://localhost:4040" className="navbar-brand">Urun Siparis Uygulaması</a> </div>
                        <button onClick={this.waiterbutton} className="usernamepage btn btn-outline-danger">Garson: {this.state.waiter}</button>

                    </nav>

                </header>
            </div>
        );
    }
}

export default HeaderComponent;