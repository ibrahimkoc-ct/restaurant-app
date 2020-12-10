import React, {Component} from 'react';
import HeaderComponent from "./HeaderComponent";
import {Link} from "react-router-dom";
import Table from "react-bootstrap/Table";
import ProductService from "../services/ProductService";
import TableService from "../services/TableService";
import axios from "axios";
import {Modal} from "react-bootstrap";
import WaiterService from "../services/WaiterService";
import ResponsiveProduct from "./ResponsiveProduct";
import ClientContext from "../ClientContext";
import createBrowserHistory from 'history/createBrowserHistory';
const history = createBrowserHistory({forceRefresh:true});
class TableComponent extends Component {
    static contextType=ClientContext;
    constructor(props) {
        super(props)
        this.state = {
            categorylist: [],
            tablelist: [],
            categoryName:'',
            tableNumber:0,
            items: [],
            show:false,
            waiterList:[],
            orderList:[],
            showDetailTable:false,
            selectedTableComp:'',
            category:{},
            selectedTableDetail:[],
            ViewTableDetail:[],
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
            this.state.token=localStorage.getItem("token")
        }
        TableService.getCategory(this.state.token).then((res) => {
            this.setState({categorylist: res.data});

        });
        WaiterService.getWaiter(this.state.token).then((res)=>{
            this.setState({waiterList:res.data})
        })


    }

    onClickSidebar = (category) => {
        this.state.items=[];
       this.setState({tableNumber:category.tableAmount ,category:category})
        let orders = ResponsiveProduct.getOrderFromStorage();
        let table= [];
        let piece=0;
            for(let i=0; i<orders.length; i++){
                const text=category.name;
                if(orders[i][0].selectedtable.indexOf(text)!==-1){

                     for(let j=0; j<orders[i].length; j++){
                         piece+=orders[i][j].piece
                     }
                     table.push(orders[i][0].selectedtable+" "+piece+"  $");
                    piece=0;
                }
            }

            let tableString=JSON.stringify(table);


        for (let i=1; i<=category.tableAmount; i++)  {
            let searchTest=`Masa ${i}# `
            if(tableString.indexOf(searchTest)!==-1) {
                let startIndex=tableString.indexOf(`Masa ${i}`)+searchTest.length;
                let endIndex=tableString.indexOf("$");
                let tmp=tableString.substr(startIndex,3);

                this.state.items.push(
                   <button className="btn btn-warning cardbutton "
                                 onClick={() =>
                                     this.DetailTable(category, i)}><h4>Masa {i}</h4>{tmp} Adet Ürün</button>
                )


            }
            else {
                this.state.items.push(<button className="btn btn-secondary cardbutton "
                                              onClick={() =>
                                                  this.addProduct(category, i)}>Masa {i}</button>)
            }
        }
        return this.state.items;

    }
    onClickTableDetail(){
        let orders = ResponsiveProduct.getOrderFromStorage();

        localStorage.setItem("product",this.state.selectedTableComp);
        this.setState({showDetailTable:false ,show:true})

    }
    onClickWaiter=(waiter1)=>{

        this.setState({show:false})
        this.props.history.push('/products')
        const{waiter,setWaiter}=this.context
        setWaiter(waiter1.name)
    }
    DetailTable=(category,i)=>{
        const{selectedTableDetail}=this.state;
        selectedTableDetail.length=0;
        this.setState({showDetailTable:true})
        this.state.selectedTableComp="Secili Kategori: "+category.name+" Secili Masa "+i+"#"

        let orders = ResponsiveProduct.getOrderFromStorage();
        let table= [];

        for(let i=0; i<orders.length; i++){
            const text=this.state.selectedTableComp;
            if(orders[i][0].selectedtable.indexOf(text)!==-1){
                table.push(orders[i])
                selectedTableDetail.push(orders[i])
            }
        }
        this.state.ViewTableDetail=selectedTableDetail[0]
        console.log(selectedTableDetail)

    }
    addProduct= (product,i)=>{
        this.setState({show:true})

        localStorage.setItem("product","Secili Kategori: "+product.name+" Secili Masa "+i+"#");

    }
    onClickExit(){
        localStorage.setItem("product","Secili Masa Yok")
    }
    onClickWaiterCancel(){
        this.setState({show:false})
    }
    onClickTableDetailCancel(){
        this.setState({showDetailTable:false })
    }

    pay(){
        ProductService.pay(this.state.ViewTableDetail,this.state.token).then(res => {
            this.props.history.push('/homepage')
        });
        localStorage.setItem("product", "Secili Masa Yok");
        const{waiter,setWaiter}=this.context
        setWaiter("Seçili Garson Yok")
        let orders = ResponsiveProduct.getOrderFromStorage();
        const{selectedTableDetail}=this.state;

        orders.forEach(function(orderTable, index){
            if(JSON.stringify(orderTable).indexOf(JSON.stringify(selectedTableDetail[0]))!==-1){
                console.log(JSON.stringify(orderTable))
                orders.splice(index, 1);
            }
        });
        localStorage.setItem("orders", JSON.stringify(orders));
        this.setState({showDetailTable:false })
        this.props.history.push('/homepage')

    }


    render() {
        const{selectedTableDetail}=this.state;
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
               <Modal show={this.state.show}>
                   <Modal.Header>Garson Seçimi</Modal.Header>
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
                           this.onClickWaiterCancel()}>İptal</button>
                   </Modal.Footer>
               </Modal>

               <Modal show={this.state.showDetailTable}>
                   <Modal.Header>
                           <h4>Masa Özellikleri</h4>
                   </Modal.Header>
                   <Modal.Body className="ModalBody">
                       <Table>
                           <thead>
                           <th>Ürün Adı</th>
                           <th>Ürün Adeti</th>
                           <th>Ürün Fiyatı</th>
                           </thead>
                           <tbody>
                       {

                           this.state.ViewTableDetail.map(
                               table => {
                                   return (
                                       <tr>
                                           <td>{table.title}</td>
                                           <td>{table.piece} Adet</td>
                                           <td>{table.price*table.piece} ₺</td>
                                       </tr>


                                   )
                               })}
                           </tbody>
                       </Table>

                   </Modal.Body>
               <Modal.Footer>

                   <button className="btn btn-outline-danger" onClick={() =>
                       this.onClickTableDetailCancel()}>iptal</button>
                   <button className="btn btn-info" onClick={() =>
                       this.onClickTableDetail()}>Masaya Git</button>
                   <button className="btn btn-success" onClick={() => this.pay()}>Öde</button>
               </Modal.Footer>
               </Modal>
            </div>


        );
    }
}

export default TableComponent;