import React, {Component} from 'react';
import UserService from "../services/UserService";
import CategoryService from "../services/CategoryService";
import HeaderComponent from "./HeaderComponent";
import {Link} from "react-router-dom";
import Table from "react-bootstrap/Table";
import FooterComponent from "./FooterComponent";
import BackofficeContext from "../BackofficeContext";
import createBrowserHistory from 'history/createBrowserHistory';
const history = createBrowserHistory({forceRefresh:true});

class CategoryProductList extends Component {
    static contextType = BackofficeContext;
    constructor(props) {
        super(props)
        this.state = {
            id:this.props.match.params.id,
            productlist: [],
            categorylist:[],
            token:'',

        }


    }
    componentDidMount(){
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
            this.state.token=localStorage.getItem("token")
        }

        CategoryService.getCategoryProductId(this.state.id,this.state.token).then((res)=>{
            this.setState({ productlist:res.data});
        });
        CategoryService.viewCategory(this.state.id,this.state.token).then((res)=>{
            this.setState({categorylist:res.data});
        });

    }
    render() {
        return (
            <div>
                <HeaderComponent/>
                <div className="container productlist">

                    <h2 className="text-center ">{this.state.categorylist.name} Kategorisi</h2>

                    <div className="row">

                    </div>
                    <div className="row">
                        <Table striped bordered hover >
                            <thead>
                            <tr>
                                <th>Urun Adi</th>
                                <th>Urun Icerigi</th>
                                <th>Urun Fiyati</th>

                            </tr>
                            </thead>
                            <tbody>
                            {
                                this.state.productlist.map(
                                    product =>
                                        <tr key={product.id}>
                                            <td>{product.title}</td>
                                            <td>{product.description}</td>
                                            <td>{product.price}</td>
                                            <td>


                                            </td>
                                        </tr>
                                )
                            }
                            </tbody>
                        </Table>
                    </div>

                </div>
                <FooterComponent/>
            </div>
        );
    }
}

export default CategoryProductList;