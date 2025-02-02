import React, {Component} from 'react';
import WaiterService from "../../services/WaiterService";
import HeaderComponent from "../homepage/HeaderComponent";
import Table from "react-bootstrap/Table";
import {Link} from "react-router-dom";
import createBrowserHistory from 'history/createBrowserHistory';
import BackofficeContext from "../../BackofficeContext";
import FullPageLoading from "../loading/FullPageLoading";

const history = createBrowserHistory({forceRefresh: true});

class WaiterListComponent extends Component {
    static contextType = BackofficeContext;

    constructor(props) {
        super(props);
        this.state = {
            waiterList: [],
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

        WaiterService.getWaiter(this.state.token).then((res) => {
            this.setState({waiterList: res.data, loading: false})
        }).catch(this.setState({loading:false}));
    }
    deleteWaiter = (waiter) => {
        this.setState({loading: true})
        WaiterService.deleteWaiter(waiter.id, this.state.token).then(res => {
            this.setState({waiterList: this.state.waiterList.filter(a => a.id !== waiter.id), loading: false})
        })
    }
    editWaiter(waiter) {
        this.props.history.push({
            pathname:`update-waiter-table/{waiter.id}`,
            state:{
                waiter:waiter
            }
        });
    }
    viewWaiter(waiter) {
        this.props.history.push({
            pathname:`view-waiter-table/{waiter.id}`,
            state:{
                waiter:waiter
            }
        });
    }
    waiterListForm=()=>{
        if(!this.state.waiterList){
            return <h2>Bir hata oluştu. Lütfen daha sonra tekrar deneyiniz</h2>
        }
        return (

            <div className="row">
                <Table striped bordered hover>
                    <thead>
                    <tr>
                        <th>Garson Adi</th>
                        <th>Garson Maili</th>
                        <th>Garson Telefon Numarası</th>
                        <th>Garson Resmi</th>
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
                                    <td align="center"><img
                                        src={'data:image/png;base64,' + waiter.mediaDTO.fileContent}
                                        width="100"/></td>

                                    <td>{waiter.salary}</td>
                                    <td>
                                        <button onClick={() => this.editWaiter(waiter)}
                                                className=" btn btn-info  ">Güncelle
                                        </button>
                                        <button style={{marginLeft: "10px"}}
                                                onClick={() => this.deleteWaiter(waiter)}
                                                className="btn btn-danger">Sil
                                        </button>
                                        <button style={{marginLeft: "10px"}}
                                                onClick={() => this.viewWaiter(waiter)}
                                                className="btn btn-success">Görüntüle
                                        </button>
                                    </td>

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
                <Link to="/add-waiter-table">
                    <button className="btn btn-info addbutton">Garson Ekle</button>
                </Link>
                <div className="container productlist">
                    <h2 className="text-center ">Garson Listesi</h2>
                    {this.waiterListForm()}
                </div>
                {this.state.loading ? <FullPageLoading/> : null}
            </div>

        );
    }
}

export default WaiterListComponent;