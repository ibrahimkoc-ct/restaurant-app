import React, {Component} from 'react';
import Table from "react-bootstrap/Table";
import UserService from "../services/UserService";
import ProductService from "../services/ProductService";

class UserListComponent extends Component {
    constructor(props) {
        super(props)
        this.state = {
            userlist: []
        }

        this.editUser=this.editUser.bind(this);
        this.deleteUser=this.deleteUser.bind(this);
    }
    componentDidMount(){
        UserService.getUser().then((res)=>{
            this.setState({ userlist:res.data});
        });

    }
    deleteUser(id){
        UserService.deleteUser(id).then(res =>{
            this.setState({userlist:this.state.userlist.filter(product => product.id !==id)})
        })

    }
    editUser(id){
        this.props.history.push('/update-user/'+id);
        console.log(id)

    }
    render() {
        return (
            <div>
                <h2 className="text-center">Kullanıcılar</h2>
                <div className="row">



                </div>
                <div className="row">
                    <Table striped bordered hover >
                        <thead>
                        <tr>
                            <th>Kullanıcı ID</th>
                            <th>Kullanıcı Adı</th>
                            <th>Parola</th>
                            <th>Rol</th>
                            <th >Actions</th>
                        </tr>
                        </thead>
                        <tbody>
                        {
                            this.state.userlist.map(
                                user =>
                                    <tr >
                                        <td>{user.id}</td>
                                        <td>{user.username}</td>
                                        <td itemType="password">{user.password}</td>
                                        <td>{user.role}</td>
                                        <td>
                                            <button  onClick={()=>this.editUser(user.id)} className=" btn btn-info btn-sm ">Edit</button>
                                            <button style={{marginLeft: "10px"}} onClick={()=>this.deleteUser(user.id)} className="btn btn-danger btn-sm">Sil</button>
                                        </td>
                                    </tr>
                            )
                        }
                        </tbody>
                    </Table>
                </div>

            </div>
                

        );
    }
}

export default UserListComponent;