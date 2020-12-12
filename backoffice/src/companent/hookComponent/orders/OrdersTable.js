import React, { useState,useContext,useEffect } from 'react';
import HeaderComponent from "../../homepage/HeaderComponent";
import createBrowserHistory from 'history/createBrowserHistory';
import {AppContext} from "../../../App";
import Table from "react-bootstrap/Table";
import ProductService from "../../../services/ProductService";

const history = createBrowserHistory({forceRefresh:true});

const OrdersTable =()=>{
    const context=useContext(AppContext);
    const [token,setToken]=useState();
    const [listOrders,setListOrders]=useState([]);

    useEffect  (()=>{
        let appState=Object.assign({},context.appState);
         setToken(appState.token)

      ProductService.getProductSales("Basic YWRtaW46cGFzczE=").then((res)=>{
           setListOrders(res.data)
       })
    },[]);

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

                            listOrders.map((order,key)=>(
                                    <tr>
                                        <td key={key}>{order.orderId}</td>
                                        <td key={key}>{order.createDate}</td>
                                        <td key={key}>{order.waiterName}</td>
                                        <td key={key}>{order.selectedtable}</td>
                                        <td key={key}>{order.title}</td>
                                        <td key={key}>{order.id}</td>
                                        <td key={key}>{order.piece}</td>
                                        <td key={key}>{order.price}</td>
                                        <td key={key}>{order.price*order.piece}</td>

                                    </tr>
                            ))
                        }
                        </tbody>
                    </Table>
                </div>

            </div>
        </div>

    );

}
export default OrdersTable;