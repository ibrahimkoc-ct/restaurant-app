import React, {Component} from 'react';
import ProductService from "../../services/ProductService";
import Table from "react-bootstrap/Table";

import {Link} from 'react-router-dom';
import HeaderComponent from "../homepage/HeaderComponent";
import CategoryService from "../../services/CategoryService";
import createBrowserHistory from 'history/createBrowserHistory';
import BackofficeContext from "../../BackofficeContext";
import FullPageLoading from "../loading/FullPageLoading";

const history = createBrowserHistory({forceRefresh:true});

class ListComponent extends Component {
    static contextType = BackofficeContext;
    constructor(props) {
        super(props)
        this.state = {
            productslist: [],
            categorylist: [],
            token:'',
            loading:false
        }

        this.editProduct=this.editProduct.bind(this);
        this.deleteProduct=this.deleteProduct.bind(this);
        this.ViewCategory=this.ViewCategory.bind(this);
    }
    async componentDidMount() {
        const userToken = this.context;
        this.setState({loading: true})

        if (localStorage.getItem("token") == null) {
            if (userToken.token.length > 0) {
                this.state.token = userToken.token;

            } else {
                history.push('/');
            }
        } else {
            this.state.token = localStorage.getItem("token")
        }

        await ProductService.getProduct(this.state.token).then((res) => {
            this.setState({productslist: res.data});
        });
        CategoryService.getCategory(this.state.token).then((res) => {
            this.setState({categorylist: res.data, loading: false});
        });


    }

    editProduct(id){
        this.props.history.push('/update-product/'+id);



    }
    deleteProduct(id){
        this.setState({loading: true})
            ProductService.deleteProduct(id,this.state.token).then(res =>{
                this.setState({productslist:this.state.productslist.filter(product => product.id !==id),loading:false})

            })

    }
    viewProduct(id){
        this.props.history.push('/view-product/'+id);
    }
    ViewCategory=(category)=> {

        this.setState({loading: true})
        this.setState({
            productslist:this.state.productslist.filter(product => product.category==category),
            loading:false

        })
    }


    render() {

        return (
            <div>
                <HeaderComponent/>
                <Link to="/add-product">
                    <button className="btn btn-info addbutton">Ürün Ekle</button>

                </Link>

                <div className="container productlist">



                <h2 className="text-center ">Urun Listesi</h2>

                <div className="row">



                </div>
                <div className="row tbody-heigth">
                    <Table striped bordered hover >
                        <thead>
                        <tr>
                            <th>Urun Adi</th>
                            <th>Urun Icerigi</th>
                            <th style={{width:'200px'}}>Urun Kategorisi</th>
                            <th>Urun Resmi</th>
                            <th>Urun Fiyati</th>
                            <th className="actions123">Actions</th>
                        </tr>
                        </thead>
                        <tbody>
                        {
                            this.state.productslist.map(
                                product =>

                                    <tr key={product.id}>
                                        <td>{product.title}</td>
                                        <td>{product.description}</td>
                                        <td>

                                            <button className="btn btn-link">
                                                {
                                                    product.categories.map(
                                                        category=>
                                                        <ul className="text-left" key={category.name}>
                                                           <li onClick={()=>this.ViewCategory(category.name)}>{category.name}</li>
                                                        </ul>
                                                    )

                                                }

                                            </button>
                                        </td>
                                        <td align="center"><img src={'data:image/png;base64,' + product.mediaDTO.fileContent} width="100"/></td>
                                        <td>{product.price}</td>
                                        <td>

                                            <button  onClick={()=>this.editProduct(product.id)} className=" btn btn-info">Güncelle</button>
                                            <button style={{marginLeft: "10px"}} onClick={()=>this.deleteProduct(product.id)} className="btn btn-danger">Sil</button>
                                            <button style={{marginLeft: "10px"}} onClick={()=>this.viewProduct(product.id)} className="btn btn-success">Görüntüle</button>
                                        </td>
                                    </tr>

                            )
                        }
                        </tbody>
                    </Table>
                </div>

                </div>

                { this.state.loading ? <FullPageLoading/> : null}

            </div>
        );

    }
}
export default ListComponent;