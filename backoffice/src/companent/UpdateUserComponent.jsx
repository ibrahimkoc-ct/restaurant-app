import React, {Component} from 'react';
import UserService from "../services/UserService";
import HeaderComponent from "./HeaderComponent";
import FooterComponent from "./FooterComponent";
import createBrowserHistory from 'history/createBrowserHistory';
import BackofficeContext from "../BackofficeContext";
const history = createBrowserHistory({forceRefresh:true});

class UpdateUserComponent extends Component {
    static contextType = BackofficeContext;
    constructor(props) {
        super(props);
        this.state ={
            id:this.props.match.params.id,
            username:'',
            password:'',
            enabled:'',
            email:'',
            enabled_message:'Atkiflik Seciniz',
            token:'',
            role:[],
            selectedRole:[],

        }
        this.chargeUsernameHandler=this.chargeUsernameHandler.bind(this);
        this.chargePasswordHandler=this.chargePasswordHandler.bind(this);
        this.updateUser=this.updateUser.bind(this);

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
        UserService.getAuth(this.state.token).then((res)=>{
            this.setState({role:res.data})

        })

    }

    changeMultiSelect=(role)=>{
        if(this.state.selectedRole.filter(select=>select.id===role.id).length>0){
            this.state.selectedRole.pop(role)
        }
        else {
            this.state.selectedRole.push(role);
        }

        console.log(this.state.selectedRole)
    }
    updateUser = (e) =>{
        e.preventDefault()
        let user={id:this.state.id,username: this.state.username,password: this.state.password,enabled: this.state.enabled,email:this.state.email,
            roles:this.state.selectedRole};
        UserService.updateUser(user,this.state.token).then(res =>{

            console.log('user=>'+JSON.stringify(user));
            this.props.history.push('/user-table');
        })

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
    chargeMailHandler=(event)=>{
        this.setState({email:event.target.value});
}
    onClickTrueItem=()=>{
        this.setState({enabled_message:"TRUE",
            enabled:"true"});

    }
    onClickFalseItem=()=>{
        this.setState({enabled_message:"FALSE",
            enabled:"false"});

    }


    render() {
        return (
            <div>
                <HeaderComponent/>
                <div className="container">
                    <div className="row">
                        <div className="card col-md-6 offset-md-3 offset-md-3">
                            <h3 className="text-center">Kullanıcı Güncelle</h3>
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
                                        <label>Email</label>
                                        <input placeholder="E mail" name="mail" className="form-control"
                                               value={this.state.email} onChange={this.chargeMailHandler.bind(this)}/>

                                    </div>
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
                                    <button className="btn btn-success" onClick={this.updateUser}>Guncelle</button>
                                    <button className="btn btn-danger" onClick={this.cancel.bind(this)} style={{marginLeft:"10px"}}>Iptal</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
                <FooterComponent/>
            </div>
        );
    }
}

export default UpdateUserComponent;