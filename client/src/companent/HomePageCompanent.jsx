import React, {Component} from 'react';
import HeaderComponent from "./HeaderComponent";
import FooterComponent from "./FooterCompanent";
import {Link} from "react-router-dom";
import {Button, Modal} from "react-bootstrap"
import "./App2.css";
import WaiterService from "../services/WaiterService";
import ClientContext from "../ClientContext";
import createBrowserHistory from 'history/createBrowserHistory';
const history = createBrowserHistory({forceRefresh:true});
class HomePageCompanent extends Component {
    static contextType=ClientContext;
    constructor() {
        super();
        this.state={
            waiterList:[],
            show:false,
            token:'',

        }
    }
    componentDidMount() {
        const userToken = this.context;
        if(localStorage.getItem("token")==null){
            if(userToken.token.length>0){
                this.state.token=userToken.token;

                console.log(this.state.token)
            }
            else{
                history.push('/');
            }
        }
        else {
            this.setState({token:localStorage.getItem("token")})

        }

        WaiterService.getWaiter(this.state.token).then((res)=>{
            this.setState({waiterList:res.data})
        })
    }

    signOut = (e) => {


        localStorage.removeItem("token")
        this.props.history.push('/');
        e.preventDefault()
    }

    onClickWaiter=(waiter1)=>{
        this.setState({show:false})
        this.props.history.push('/products')
        const{waiter,setWaiter}=this.context
       setWaiter(waiter1.name)
        // localStorage.setItem("waiter",waiter.name)
    }
    onClickProduct(){
        this.setState({show:true})
    }
    onClickWaiterCancel(){
        this.setState({show:false})
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
                    <button onClick={() =>
                        this.onClickProduct()} className="btn btn-secondary homebutton">ÜRÜNLER</button>
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
                <Modal show={this.state.show}>
                    <Modal.Header >Garson Seçimi</Modal.Header>
                    <Modal.Body className="ModalBody">
                        {
                            this.state.waiterList.map(
                                waiter => {
                                    return (
                                        <button className="btn btn-outline-success btn-block "onClick={() =>
                                            this.onClickWaiter(waiter)}>{waiter.name}</button>


                                    )
                                })}
                    </Modal.Body>
                    <Modal.Footer>
                        <button className="btn btn-danger" onClick={() =>
                            this.onClickWaiterCancel()}>iptal</button>
                    </Modal.Footer>


                </Modal>


            </div>
        );
    }
}

export default HomePageCompanent;