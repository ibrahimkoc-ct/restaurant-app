import React, { useState,useContext,useEffect } from 'react';
import HeaderComponent from "../../homepage/HeaderComponent";
import FooterComponent from "../../homepage/FooterComponent";
import createBrowserHistory from 'history/createBrowserHistory';


import UserService from "../../../services/UserService";

const history = createBrowserHistory({forceRefresh:true});
const CreateRoleComponentHook=()=> {
    const context=useContext(AppContext);
    const [token,setToken]=useState();
    const [role, setRole] = useState();

    useEffect(()=>{
        let appState=Object.assign({},context.appState);
        setToken(appState.token)
    });

  const cancelButton =()=>{

  history.push('/auth-table');
    }
    const onRoleChange =event=>{
      setRole(event.target.value)

    }
    const saveRole =(e)=>{
      if(!role){
          window.alert("Rol seçili değil!")
      }
      let selectedRole={name:role};
      UserService.createAuth(selectedRole,token)
        history.push('/auth-table');
    }

    return (
        <div>
            <HeaderComponent/>
            <div className="container">
                <div className="row">
                    <div className="card col-md-6 offset-md-3 offset-md-3">
                        <h3 className="text-center">Rol Ekle</h3>
                        <div className="card-body">
                            <form>
                                <div className="form-group">
                                    <label>Rol Adı</label>
                                    <input id="button" placeholder="Rol Adı" name="title" className="form-control"
                                           onChange={(e)=> onRoleChange(e)}  />

                                </div>
                                <hr/>

                            </form>
                            <button className="btn btn-success" onClick={()=>saveRole()}  >Kaydet</button>
                            <button className="btn btn-danger" onClick={()=> cancelButton()} style={{marginLeft:"10px"}}>Iptal</button>
                        </div>
                    </div>

                </div>

            </div>
            <FooterComponent/>
        </div>

    );
}
export default CreateRoleComponentHook;