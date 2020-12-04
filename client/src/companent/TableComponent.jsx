import React, {Component} from 'react';
import HeaderComponent from "./HeaderComponent";
import {Link} from "react-router-dom";
import Table from "react-bootstrap/Table";
import ProductService from "../services/ProductService";
import TableService from "../services/TableService";
import axios from "axios";

class TableComponent extends Component {
    constructor(props) {
        super(props)
        this.state = {
            categorylist: [],
            tablelist: [],
            categoryName:'',
            tableNumber:0,
            items: []
        }

    }

    componentDidMount() {
        TableService.getCategory().then((res) => {
            this.setState({categorylist: res.data});
            console.log(res.data)
        });


    }

    onClickSidebar = (category) => {
        this.state.items=[];
       this.setState({tableNumber:category.tableAmount})

        for (let i=1; i<=category.tableAmount; i++)  {
            this.state.items.push(<button className="btn btn-secondary cardbutton "
                                          onClick={() =>
                                              this.addProduct(category,i)}>Masa {i}</button>)
        }
        console.log((this.state.items))
        return this.state.items;

    }
    addProduct= (product,i)=>{
            console.log(i);
        sessionStorage.setItem("product","Secili Kategori: "+product.name+" Secili Masa: "+i)
        this.props.history.push('/products')
    }
    onClickExit(){
        sessionStorage.setItem("product","Secili Masa Yok")
    }

    render() {

        return (
           <div>
               <HeaderComponent/>
               <Link to="/homepage">
                   <button className="btn btn-info backbutton fas fa-edit buttonhome" onClick={
                       this.onClickExit}></button>
               </Link>

               <Table>
                   <tbody>
                   <tr>
                       <th className="categorylable">
                           <div className="card categorylable">
                               <div className="card-header">
                                   <h4 className="d-inline">Kategoriler</h4>
                               </div>
                               <div className="card-body">
                                   {
                                       this.state.categorylist.map(
                                           category => {
                                               return (
                                                       <button className="btn btn-info btn-block "onClick={() =>
                                                           this.onClickSidebar(category)}>{category.name}</button>


                                               )
                                           })}

                               </div>
                           </div>
                       </th>
                       <th>

                           <div>
                           <div className="card tablecardbody">

                               <div className="card-header">

                                   <h4 className="d-inline">Masalar</h4>
                               </div>
                               <div className="card-body ">
                                   {this.state.items}

                               </div>







                               </div>
                           </div>




                       </th>
                   </tr>
                   </tbody>
               </Table>
            </div>


        );
    }
}

export default TableComponent;