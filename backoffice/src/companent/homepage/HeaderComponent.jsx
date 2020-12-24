import React, {Component} from 'react';
import {Link} from "react-router-dom";
import BackofficeContext from "../../BackofficeContext";
import createBrowserHistory from 'history/createBrowserHistory';

const history = createBrowserHistory({forceRefresh:true});

class HeaderComponent extends Component {
    static contextType = BackofficeContext;
    constructor(props) {
        super(props);
        this.state = {
            username:''
        }
        this.Exitbutton=this.Exitbutton.bind(this);
    }
    componentDidMount() {
        const userToken = this.context;
        if(localStorage.getItem("token")==null){
            if(userToken.token.length>0){
                this.state.username=userToken.username;

            }
            else{
                history.push('/');
            }
        }
        else {
            this.state.token=localStorage.getItem("token")
            this.state.username=localStorage.getItem("username")
        }


    }

    Exitbutton(){
        localStorage.removeItem("token");
        localStorage.removeItem("username")



    }
    render() {
        return (
            <div>
                <header>
                    <nav className="navbar navbar-expand-md navbar-dark bg-dark">
                        <Link to='/products'>
                            <div className="navbar-brand">Urun Yonetim Uygulaması </div>
                        </Link>
                        <Link to="/category-table">
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
                        <Link to ='categorytable-table' >
                            <button style={{marginLeft: "10px"}} className="btn btn-info" >Masa Kategorileri</button>
                        </Link>
                        <Link to ='/waiter-table' >
                            <button style={{marginLeft: "10px"}} className="btn btn-info" >Garsonlar</button>
                        </Link>
                        <Link to ='/add-media' >
                            <button style={{marginLeft: "10px"}} className="btn btn-info" >Medya</button>
                        </Link>
                        <Link to ='/customers' >
                            <button style={{marginLeft: "10px"}} className="btn btn-info" >Musteriler</button>
                        </Link>

                        <Link to ='/' >
                            <button className="btn btn-danger usernamepage" onClick={()=>this.Exitbutton()}>Çıkıs: {this.state.username}</button>
                        </Link>
                        <Link to ='/server-info' >
                            <button style={{marginLeft: "10px"}} className="btn btn-info serverinfo">Server Bilgileri </button>
                        </Link>

                    </nav>
                </header>
            </div>
        );
    }
}

export default HeaderComponent;