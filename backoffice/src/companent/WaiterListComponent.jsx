import React, {Component} from 'react';
import WaiterService from "../services/WaiterService";
import HeaderComponent from "./HeaderComponent";
import Table from "react-bootstrap/Table";
import {Link} from "react-router-dom";

class WaiterListComponent extends Component {
    constructor(props) {

        super(props);
        this.state = {
            waiterList: []
        }
    }

    componentDidMount() {
        WaiterService.getWaiter().then((res) => {
            this.setState({waiterList: res.data})

        });
        console.log(this.state.waiterList)
    }
    deleteWaiter=(waiter)=>{
        WaiterService.deleteWaiter(waiter.id).then(res=>{
            this.setState({waiterList: this.state.waiterList.filter(a => a.id !== waiter.id)})

        })
    }
    editWaiter(waiter) {
        this.props.history.push('/update-waiter-table/'+waiter.id);
    }
    viewWaiter(waiter) {
        this.props.history.push('/view-waiter-table/'+waiter.id);
    }


    render() {
        return (
            <div>
                <HeaderComponent/>
                <Link to="/add-waiter-table">
                    <button className="btn btn-info addbutton">Garson Ekle</button>
                </Link>
                <div className="container productlist">
                    <h2 className="text-center ">Garson Listesi</h2>

                    <div className="row">
                    </div>
                    <div className="row">
                        <Table striped bordered hover>
                            <thead>
                            <tr>
                                <th>Garson Adi</th>
                                <th>Garson Maili</th>
                                <th>Garson Telefon Numarası</th>
                                <th>Garson Maaşı</th>
                                <th className="actions123">Actions</th>
                            </tr>
                            </thead>
                            <tbody>
                            {
                                this.state.waiterList.map(
                                    waiter =>
                                        <tr key={waiter.id}>
                                            <td>{waiter.name}</td>
                                            <td>{waiter.mail}</td>
                                            <td>{waiter.phoneNumber}</td>
                                            <td>{waiter.salary}</td>
                                            <td>
                                                <button  onClick={()=>this.editWaiter(waiter)} className=" btn btn-info  ">Güncelle</button>
                                                <button style={{marginLeft: "10px"}} onClick={()=>this.deleteWaiter(waiter)} className="btn btn-danger">Sil</button>
                                                <button style={{marginLeft: "10px"}}  onClick={()=>this.viewWaiter(waiter)} className="btn btn-success">Görüntüle</button>
                                            </td>

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

       export default WaiterListComponent;