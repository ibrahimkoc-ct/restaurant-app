import React, { useState,useContext,useEffect } from 'react';
import HeaderComponent from "../../homepage/HeaderComponent";
import FooterComponent from "../../homepage/FooterComponent";
import createBrowserHistory from 'history/createBrowserHistory';
import {AppContext} from "../../../App";
import UserService from "../../../services/UserService";
import {Link} from "react-router-dom";
import Table from "react-bootstrap/Table";

const history = createBrowserHistory({forceRefresh:true});
const ViewRoleComponent=()=>{
    const context=useContext(AppContext);
    const [token,setToken]=useState();
    const [id,setId]=useState();
    const [role,setRole]=useState({})

    useEffect(()=>{
        let appState=Object.assign({},context.appState);
        setToken(appState.token)
        UserService.getAuthById(1,token).then((res)=>
        setRole(res.data))
    })
    return(
        <div>
            <HeaderComponent/>
            <br></br>
            <div className="card col-md-6 offset-md-3" >
                <h2 className="text-center">Yetkinlik Detayları</h2>
                <div className="card-body">
                    <div className="row">
                        <h3>Rol id: {role.id}</h3>
                    </div>
                    <hr></hr>
                    <div className="row">

                        <h3>Rol Adı:  {role.name}</h3>
                    </div>

                </div>
            </div>
        </div>
    )




}
export default ViewRoleComponent;