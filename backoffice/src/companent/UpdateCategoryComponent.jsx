import React, {Component} from 'react';
import HeaderComponent from "./HeaderComponent";
import FooterComponent from "./FooterComponent";
import ProductService from "../services/ProductService";
import CategoryService from "../services/CategoryService";
import createBrowserHistory from 'history/createBrowserHistory';
import BackofficeContext from "../BackofficeContext";
const history = createBrowserHistory({forceRefresh:true});


class UpdateCategoryComponent extends Component {
    static contextType = BackofficeContext;
    constructor(props) {
        super(props);
        this.state = {
            id: this.props.match.params.id,
            name: '',
            description: '',
            imageToUrl: '',
            token: '',
            products: [],
            category:[]
        }

        this.chargeDescriptionHandler=this.chargeDescriptionHandler.bind(this);
        this.chargeNameHandler=this.chargeNameHandler.bind(this);
        this.chargeurlToImageHandler=this.chargeurlToImageHandler.bind(this);
        this.updateCategory=this.updateCategory.bind(this);


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
        CategoryService.viewCategory(this.state.id,this.state.token).then((res)=>{
            this.setState({category:res.data})

        })


    }
    chargeNameHandler =(event) =>{
        this.setState({name:
            event.target.value});

    }
    chargeDescriptionHandler =(event) =>{
        this.setState({description:
            event.target.value});

    }
    chargeurlToImageHandler =(event) =>{
        this.setState({imageToUrl:
            event.target.value});

    }
    cancel(){
        this.props.history.push('/categorytable-table');

    }

    updateCategory= (e) =>{
        console.log(this.state.category)
        e.preventDefault()
        let category={id:this.state.id,name: this.state.name,description: this.state.description,imageToUrl: this.state.imageToUrl,products:this.state.products,mediaDTO:this.state.category.mediaDTO};
        console.log('category=>'+JSON.stringify(category));
        CategoryService.updateCategory(category,this.state.token).then(res =>{
            this.props.history.push('/categorytable-table');
        })

    }


    render() {

        return (
            <div>
                <HeaderComponent/>
                <div className="container">
                    <div className="row">
                        <div className="card col-md-6 offset-md-3 offset-md-3">
                            <h3 className="text-center">Kategori Güncelle</h3>
                            <div className="card-body">
                                <form>

                                    <div className="form-group">
                                        <label>Kategori Adı</label>
                                        <input placeholder="Kategori Adı" name="this.state.category.name" className="form-control"
                                               value={this.state.name} onChange={this.chargeNameHandler}/>

                                    </div>
                                    <div className="form-group">
                                        <label>Kategori Acıklaması</label>
                                        <input placeholder={"Kategori acıklaması"} name="description" className="form-control"
                                               value={this.state.description} onChange={this.chargeDescriptionHandler}/>

                                    </div>
                                    <div className="form-group">
                                        <label>Kategori Resim Url</label>
                                        <input placeholder="Kategori Resim" name="imageToUrl" className="form-control"
                                               value={this.state.imageToUrl} onChange={this.chargeurlToImageHandler}/>
                                    </div>

                                    <button className="btn btn-success" onClick={this.updateCategory}>Kaydet</button>
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

export default UpdateCategoryComponent;