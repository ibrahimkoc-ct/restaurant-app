
import React, {Component} from 'react';
import ProductService from "../services/ProductService";
import Table from "react-bootstrap/Table";
import HeaderComponent from "./HeaderComponent";
import '../App.css'
import createBrowserHistory from 'history/createBrowserHistory';
import BackofficeContext from "../BackofficeContext";
const history = createBrowserHistory({forceRefresh:true});



class ProductSalesTable extends Component {
    static contextType = BackofficeContext;
    constructor(props) {
        super(props);
        this.state = {
            ProductSales: [],
            token:''
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

        ProductService.getProductSales(this.state.token).then((res)=>{
            this.setState({ ProductSales:res.data});
        });
    }


    render() {
        return (
            <div>
                <HeaderComponent/>
                <div className="container productlist tbody-heigth">
                <h2 className="text-center">Urun Satış Tablosu</h2>
                <div className="row">
                </div>
                <div className="row">
                    <Table striped bordered hover>
                        <thead>
                        <tr>
                            <th>Siparis Numarası</th>
                            <th>Siparis Tarihi</th>
                            <th>Garson Adı</th>
                            <th>Masa Numarası</th>
                            <th>Urun Adi</th>
                            <th>Urun ID</th>
                            <th>Urun Adeti</th>
                            <th>Urun Fiyatı</th>
                            <th>Toplam Fiyat</th>
                        </tr>
                        </thead>
                        <tbody >
                            {

                                this.state.ProductSales.map(
                                    product =>
                                        <tr key={product.orderId}>
                                            <td>{product.orderId}</td>
                                            <td>{product.createDate}</td>
                                            <td>{product.waiterName}</td>
                                            <td>{product.selectedtable}</td>
                                            <td>{product.title}</td>
                                            <td>{product.id}</td>
                                            <td>{product.piece}</td>
                                            <td>{product.price}</td>
                                            <td>{product.price*product.piece}</td>

                                        </tr>
                                )
                            }
                            </tbody>
                            </Table>
                            </div>

            </div>
            </div>

        );
    }
}

export default ProductSalesTable;