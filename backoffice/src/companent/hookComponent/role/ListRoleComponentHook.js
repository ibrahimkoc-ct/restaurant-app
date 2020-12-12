import React, { useState,useContext,useEffect } from 'react';
import HeaderComponent from "../../homepage/HeaderComponent";
import FooterComponent from "../../homepage/FooterComponent";
import createBrowserHistory from 'history/createBrowserHistory';
import {AppContext} from "../../../App";
import UserService from "../../../services/UserService";
import {Link} from "react-router-dom";
import Table from "react-bootstrap/Table";

const history = createBrowserHistory({forceRefresh:true});

const ListRoleComponentHook=()=>{
    const context=useContext(AppContext);
    const [token,setToken]=useState();
    const [listRole,setListRole]=useState([]);

    useEffect(()=>{
        let appState=Object.assign({},context.appState);
        setToken(appState.token)
        UserService.getAuth(token).then((res)=>{
            setListRole(res.data)
        })
    },[]);

    const updateRole=(id)=>{
            history.push('/update-auth/'+id);
    }
    const viewRole=(id)=>{
        history.push('/view-auth/'+id);
    }
    const deleteRole=(id)=>{
        UserService.deleteAuth(id,token)
        setListRole(listRole.filter(role=>role.id!==id))
    }
    return(
        <div>
            <HeaderComponent/>
            <Link to="/role-add">
                <button className="btn btn-info addbutton">Yetki Ekle</button>
            </Link>
            <div className="container productlist">
                <h2 className="text-center">Yetkiler</h2>
                <div className="row">
                </div>
                <div className="row">
                    <Table striped bordered hover >
                        <thead>
                        <tr>
                            <th >Rol Id</th>
                            <th>Rol Adı</th>
                            <th>Actions</th>
                        </tr>
                        </thead>
                        <tbody>
                        {
                            listRole.map ((role,key) => (

                                    <tr>
                                        <td key={key}>{role.id}</td>
                                        <td key={key}>{role.name}</td>
                                        <td>
                                            <button  className=" btn btn-info  "onClick={()=>updateRole(role.id)}>Güncelle</button>
                                            <button style={{marginLeft: "10px"}} className="btn btn-success" onClick={()=>viewRole(role.id)}>Görüntüle</button>
                                            <button className="btn btn-danger" style={{marginLeft:"10px"}} onClick={()=>deleteRole(role.id)}>Sil</button>
                                        </td>
                                    </tr>
                            ))
                                }

                        </tbody>
                    </Table>
                </div>
            </div>
                <FooterComponent/>
        </div>
    )

}
export default ListRoleComponentHook;