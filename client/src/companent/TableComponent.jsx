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
            categoryName:''

        }

    }

    componentDidMount() {
        TableService.getCategory().then((res) => {
            this.setState({categorylist: res.data});
            console.log(res.data)
        });


    }

    onClickSidebar = (category) => {

        axios.get("http://localhost:8080/categorytable/table/id/" + category.id, {
            headers: {
                Authorization: sessionStorage.getItem("token")

            }
        }).then((res) => {
            this.setState({tablelist: res.data,
            categoryName:category.name});
            console.log(res.data)
        });
    }
    addProduct= (product)=>{

        sessionStorage.setItem("product","Secili Kategori: "+this.state.categoryName+" Secili Masa: "+product.title)
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
                                   {
                                       this.state.tablelist.map(
                                           table =>{
                                               return(
                                                           <button className="btn btn-secondary cardbutton "onClick={()=>this.addProduct(table)}>{table.title}</button>





                                               )


                                           }


                                       )
                                   }











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