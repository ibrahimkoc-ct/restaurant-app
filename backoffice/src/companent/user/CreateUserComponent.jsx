import React, {Component} from 'react';
import UserService from '../../services/UserService';
import HeaderComponent from "../homepage/HeaderComponent";
import FooterComponent from "../homepage/FooterComponent";
import {BrowserRouter as Router} from "react-router-dom";
import createBrowserHistory from 'history/createBrowserHistory';
import BackofficeContext from "../../BackofficeContext";
import FullPageLoading from "../loading/FullPageLoading";
const history = createBrowserHistory({forceRefresh:true});
class CreateUserComponent extends Component {
    static contextType = BackofficeContext;
    constructor(props) {
        super(props);
        this.state ={
            username:'',
            password:'',
            enabled:'',
            authority:'',
            email:'',
            enabled_message:'Atkiflik Seciniz',
            token:'',
            role:[],
            selectedRole:[],
            loading:false,
        }
        this.chargeUsernameHandler=this.chargeUsernameHandler.bind(this);
        this.chargePasswordHandler=this.chargePasswordHandler.bind(this);
        this.chargeEmailHandler=this.chargeEmailHandler.bind(this);
        this.saveUser=this.saveUser.bind(this);


    }
    componentDidMount() {
        this.setState({loading:true})
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
        UserService.getAuth(this.state.token).then((res)=>{
           this.setState({role:res.data,loading:false})

        })
    }
    saveUser = (e) =>{
        this.setState({loading:true})

        let user={username: this.state.username,password:this.state.password,enabled: this.state.enabled,email:this.state.email
            ,roles:this.state.selectedRole};


        UserService.createUser(user,this.state.token).then(res =>{
            this.setState({loading:false})
            this.props.history.push('/user-table');
        })
        e.preventDefault()
    }

    cancel(){
        this.props.history.push('/user-table');
    }
    chargeUsernameHandler =(event) =>{
        this.setState({username:event.target.value});
    }
    chargePasswordHandler =(event) =>{
        this.setState({password:event.target.value});
    }
    chargeEmailHandler=(event)=>[
        this.setState({email:event.target.value})
    ]


    onClickTrueItem=()=>{
        this.setState({enabled_message:"TRUE",
            enabled:"true"});

    }
    onClickFalseItem=()=>{
        this.setState({enabled_message:"FALSE",
            enabled:"false"});
    }
    changeMultiSelect=(role)=>{
        if(this.state.selectedRole.filter(select=>select.id===role.id).length>0){
            this.state.selectedRole.pop(role)
        }
        else {
            this.state.selectedRole.push(role);
        }

    }

    render() {
        return (
            <div>
                <HeaderComponent/>
                <div className="container">
                    <div className="row">
                        <div className="card col-md-6 offset-md-3 offset-md-3">
                            <h3 className="text-center">Kullanıcı Ekle</h3>
                            <div className="card-body">
                                <form>
                                    <div className="form-group">
                                        <label>Kullanıcı Adı</label>
                                        <input placeholder="Kullanıcı Adı" name="username" className="form-control"
                                               value={this.state.username} onChange={this.chargeUsernameHandler}/>

                                    </div>
                                    <div className="form-group">
                                        <label>Parola</label>
                                        <input type ="password" placeholder="Parola" name="password" className="form-control"
                                               value={this.state.password} onChange={this.chargePasswordHandler}/>

                                    </div>
                                    <div className="form-group">
                                        <label>E mail</label>
                                        <input placeholder="E mail" name="e-mail" className="form-control"
                                               value={this.state.email} onChange={this.chargeEmailHandler}/>

                                    </div>
                                    <hr/>
                                    <div className="dropdown show">
                                        <a className="btn btn-secondary btn-block dropdown-toggle" href="#" role="button" id="dropdownMenuLink"
                                           data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                            {this.state.enabled_message}
                                        </a>

                                        <div className="dropdown-menu btn-block" aria-labelledby="dropdownMenuLink">
                                            <a className="dropdown-item" onClick={this.onClickTrueItem.bind(this)}>TRUE</a>
                                            <a className="dropdown-item" onClick={this.onClickFalseItem.bind(this)}>FALSE</a>

                                        </div>
                                    </div>
                                    <hr/>
                                    <div className="checkbox" style={{height:"4rem",overflow:"auto"}}>
                                        {
                                            this.state.role.map(
                                                role=>
                                                    <div className="row col-md -12">
                                                        <label><input type="checkbox" value="" onClick={()=>this.changeMultiSelect(role)}/>{role.name}</label>
                                                    </div>
                                            )
                                        }
                                    </div>
                                    <hr/>




                                    <button className="btn btn-success" onClick={this.saveUser}>Kaydet</button>
                                    <button className="btn btn-danger" onClick={this.cancel.bind(this)} style={{marginLeft:"10px"}}>Iptal</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
                <FooterComponent/>
                { this.state.loading ? <FullPageLoading/> : null}
            </div>
        );
    }
}

export default CreateUserComponent;