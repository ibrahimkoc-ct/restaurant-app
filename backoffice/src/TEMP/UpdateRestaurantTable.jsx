// import React, {Component} from 'react';
// import HeaderComponent from "../companent/HeaderComponent";
// import FooterComponent from "../companent/FooterComponent";
// import ProductService from "../services/ProductService";
// import RestaurantTableService from "../services/RestaurantTableService";
//
// class UpdateRestaurantTable extends Component {
//     constructor(props) {
//         super(props);
//         this.state = {
//             id: this.props.match.params.id,
//             title: '',
//             description: '',
//             category: ''
//
//         }
//         this.chargeTitleHandler=this.chargeTitleHandler.bind(this);
//         this.chargeDescriptionHandler=this.chargeDescriptionHandler.bind(this);
//         this.updateTable=this.updateTable.bind(this);
//     }
//     chargeTitleHandler =(event) =>{
//         this.setState({title:event.target.value});
//     }
//     chargeDescriptionHandler =(event) =>{
//         this.setState({description:event.target.value});
//     }
//
//
//     cancel(){
//         this.props.history.push('/restaurant-table');
//     }
//     updateTable = (e) =>{
//         e.preventDefault()
//         let table={title: this.state.title,description: this.state.description,category:this.state.category};
//         console.log(table)
//         RestaurantTableService.updateTable(table,this.state.id).then(res =>{
//             this.props.history.push('/restaurant-table');
//         })
//
//     }
//     render() {
//         return (
//             <div>
//                 <HeaderComponent/>
//                 <div className="container">
//                     <div className="row">
//                         <div className="card col-md-6 offset-md-3 offset-md-3">
//                             <h3 className="text-center">Masa Guncelle</h3>
//                             <div className="card-body">
//                                 <form>
//                                     <div className="form-group">
//                                         <label>Masa Adı</label>
//                                         <input placeholder="Urun Adı" name="title" className="form-control"
//                                                value={this.state.title} onChange={this.chargeTitleHandler}/>
//
//                                     </div>
//                                     <div className="form-group">
//                                         <label>Masa Acıklaması</label>
//                                         <input placeholder="Urun acıklaması" name="description" className="form-control"
//                                                value={this.state.description} onChange={this.chargeDescriptionHandler}/>
//                                     </div>
//                                     <button className="btn btn-success" onClick={this.updateTable}>Kaydet</button>
//                                     <button className="btn btn-danger" onClick={this.cancel.bind(this)} style={{marginLeft:"10px"}}>Iptal</button>
//                                 </form>
//                             </div>
//                         </div>
//                     </div>
//                 </div>
//                 <FooterComponent/>
//
//             </div>
//         );
//     }
// }
//
// export default UpdateRestaurantTable;