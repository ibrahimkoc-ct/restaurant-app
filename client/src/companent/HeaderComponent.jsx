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
            waiter1:'',
            token:'',
        }
    }
    async componentDidMount() {
        const userToken = this.context;
        if (localStorage.getItem("token") == null) {

            if (userToken.token.length > 0) {
                this.state.token = userToken.token;
                const {waiter, setWaiter} = this.context
                this.state.waiter1 = waiter;
                console.log(this.state.token)
            } else {
                history.push('/');
            }
        } else {
            this.state.token = localStorage.getItem("token")

        }

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
                        <div><a href="/homepage" className="navbar-brand">Urun Siparis Uygulaması</a> </div>
                        <button onClick={this.waiterbutton} className="usernamepage btn btn-outline-danger">Garson: {localStorage.getItem("waiter")}</button>

                    </nav>

                </header>
            </div>
        );
    }
}

export default HeaderComponent;