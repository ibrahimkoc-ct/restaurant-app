import React, {Component} from 'react';
import CategoryService from "../services/CategoryService";
import HeaderComponent from "./HeaderComponent";
import FooterComponent from "./FooterComponent";
import CategoryTable from "../services/CategoryTable";
import BackofficeContext from "../BackofficeContext";
import createBrowserHistory from 'history/createBrowserHistory';
const history = createBrowserHistory({forceRefresh:true});

class CreateCategoryTableComponent extends Component {
    static contextType = BackofficeContext;
    constructor(props) {
        super(props);
        this.state = {
            name: '',
            description: '',
            imageToUrl: '',
            tableAmount:'',
            token:'',
            products:[]
        }
        this.chargeDescriptionHandler=this.chargeDescriptionHandler.bind(this);
        this.chargeNameHandler=this.chargeNameHandler.bind(this);
        this.chargeurlToImageHandler=this.chargeurlToImageHandler.bind(this);
        this.saveCategory=this.saveCategory.bind(this);
        this.chargeTableAmountHandler=this.chargeTableAmountHandler.bind(this);

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


    }
    chargeNameHandler =(event) =>{
        this.setState({name:event.target.value});
    }
    chargeDescriptionHandler =(event) =>{
        this.setState({description:event.target.value});
    }
    chargeurlToImageHandler =(event) =>{
        this.setState({imageToUrl:event.target.value});
    }
    chargeTableAmountHandler=(event)=>{
        this.setState({tableAmount:event.target.value});
    }
    saveCategory = (e) =>{
        e.preventDefault()
        let category={name: this.state.name,description: this.state.description,imageToUrl: this.state.imageToUrl,products:this.state.products,tableAmount:this.state.tableAmount};
        console.log('category=>'+JSON.stringify(category));
        CategoryTable.addCategory(category,this.state.token).then(res =>{
            this.props.history.push('/categorytable-table');
        })
    }


    cancel(){
        this.props.history.push('/categorytable-table');
    }
    render() {
        return (
            <div>
                <HeaderComponent/>
                <div className="container">
                    <div className="row">
                        <div className="card col-md-6 offset-md-3 offset-md-3">
                            <h3 className="text-center">Kategori Ekle</h3>
                            <div className="card-body">
                                <form>

                                    <div className="form-group">
                                        <label>Kategori Adı</label>
                                        <input placeholder="Kategori Adı" name="name" className="form-control"
                                               value={this.state.name} onChange={this.chargeNameHandler}/>

                                    </div>
                                    <div className="form-group">
                                        <label>Kategori Acıklaması</label>
                                        <input placeholder="Kategori acıklaması" name="description" className="form-control"
                                               value={this.state.description} onChange={this.chargeDescriptionHandler}/>

                                    </div>
                                    <div className="form-group">
                                        <label>Kategori Resim Url</label>
                                        <input placeholder="Kategori Resim" name="category" className="form-control"
                                               value={this.state.imageToUrl} onChange={this.chargeurlToImageHandler}/>
                                    </div>
                                    <div className="form-group">
                                        <label>Masa Sayisi</label>
                                        <input type ="number" placeholder="Masa Sayisi" name="category" className="form-control"
                                               value={this.state.tableAmount} onChange={this.chargeTableAmountHandler}/>
                                    </div>

                                    <button className="btn btn-success" onClick={this.saveCategory}>Kaydet</button>
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

export default CreateCategoryTableComponent;