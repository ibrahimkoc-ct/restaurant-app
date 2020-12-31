import React, {Component} from 'react';
import ProductService from "../../services/ProductService";
import Table from "react-bootstrap/Table";
import HeaderComponent from "../homepage/HeaderComponent";
import '../../App.css'
import createBrowserHistory from 'history/createBrowserHistory';
import BackofficeContext from "../../BackofficeContext";
import FullPageLoading from "../loading/FullPageLoading";

const history = createBrowserHistory({forceRefresh: true});


class ProductSalesTable extends Component {
    static contextType = BackofficeContext;
    constructor(props) {
        super(props);
        this.state = {
            ProductSales: [],
            token: '',
            loading: false
        }
    }

    componentDidMount() {
        this.setState({loading: true})
        const userToken = this.context;
        if (localStorage.getItem("token") == null) {
            if (userToken.token.length > 0) {
                this.state.token = userToken.token;

            } else {
                history.push('/');
            }
        } else {
            this.state.token = localStorage.getItem("token")
        }
        ProductService.getProductSales(this.state.token).then((res) => {
            this.setState({ProductSales: res.data, loading: false});
        }).catch(
            this.setState({loading:false})
        )
    }
    orderTable =()=>{
        if(!this.state.ProductSales){
            return <h2>Satış bilgisi bulunamadı!</h2>
        }
        return (
            <div className="row">
                <Table striped bordered hover>
                    <thead>
                    <tr>
                        <th>Siparis Numarası</th>
                        <th>Siparis Tarihi</th>
                        <th>Garson Adı</th>
                        <th>Urun Adeti</th>
                        <th>Toplam Fiyat</th>
                    </tr>
                    </thead>
                    <tbody>
                    {

                        this.state.ProductSales.map(
                            product =>
                                <tr key={product.id}>
                                    <td>{product.id}</td>
                                    <td>{product.date}</td>
                                    <td>{product.productId}</td>
                                    <td>{product.totalCount}</td>
                                    <td>{product.totalAmount}</td>

                                </tr>
                        )
                    }
                    </tbody>
                </Table>
            </div>
        )
    }

    render() {
        return (
            <div>
                <HeaderComponent/>
                <div className="container productlist tbody-heigth">
                    <h2 className="text-center">Urun Satış Tablosu</h2>
                    <div className="row">
                    </div>
                    {this.orderTable()}
                </div>
                {this.state.loading ? <FullPageLoading/> : null}
            </div>

        );
    }
}

export default ProductSalesTable;