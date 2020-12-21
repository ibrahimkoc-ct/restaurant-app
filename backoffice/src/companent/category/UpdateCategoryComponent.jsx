import React, {Component} from 'react';
import HeaderComponent from "../homepage/HeaderComponent";
import FooterComponent from "../homepage/FooterComponent";
import CategoryService from "../../services/CategoryService";
import createBrowserHistory from 'history/createBrowserHistory';
import BackofficeContext from "../../BackofficeContext";
import FullPageLoading from "../loading/FullPageLoading";
import axios from "axios";

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
            category:[],
            media:[],
            mediaSelect:{},
            loading:false
        }

        this.chargeDescriptionHandler=this.chargeDescriptionHandler.bind(this);
        this.chargeNameHandler=this.chargeNameHandler.bind(this);
        this.chargeurlToImageHandler=this.chargeurlToImageHandler.bind(this);
        this.updateCategory=this.updateCategory.bind(this);


    }
    debugBase64(base64URL){
        var win = window.open();
        win.document.write('<iframe src="' + base64URL  + '" frameborder="0" style="border:0; top:0px; left:0px; bottom:0px; right:0px; width:100%; height:100%;" allowfullscreen></iframe>');
    }
    componentDidMount() {
        this.setState({loading: true})

        const userToken = this.context;
        if(localStorage.getItem("token")==null){
            if(userToken.token.length>0){
                this.state.token=userToken.token;
            }
            else{
                history.push('/');
            }
        }
        else {
            this.state.token=localStorage.getItem("token")
        }
        CategoryService.viewCategory(this.state.id,this.state.token).then((res)=>{
            this.setState({category:res.data,loading:false})

        })
        axios.get("http://localhost:8080/file/list").then((res)=>{
            this.setState({media:res.data, loading: false});
        });


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
        this.setState({loading: true})

        e.preventDefault()
        let category={id:this.state.id,name: this.state.name,description: this.state.description,imageToUrl: this.state.imageToUrl,products:this.state.products,mediaDTO:this.state.mediaSelect};
        CategoryService.updateCategory(category,this.state.token).then(res =>{
            this.props.history.push('/category-table');
            this.setState({loading:false})

        })

    }
    changeSelect=(media)=>{
        this.state.mediaSelect=media;
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
                                        <label>Kategori Resmi</label>
                                        <div className="form-group">
                                            <div className="form-check" style={{height:"4rem",overflow:"auto"}}>
                                                {
                                                    this.state.media.map(
                                                        media=>
                                                            <div  key={media.name} className="row col-md -12 custom-control custom-radio">
                                                                <input  className="form-check-input" name="customRadio" type="radio" onClick={()=>this.changeSelect(media)} />
                                                                <label className="form-check-label">
                                                                    <a onClick={()=>this.debugBase64('data:image/png;base64,' + media.fileContent)}>{media.name}</a></label>
                                                            </div>
                                                    )
                                                }
                                            </div>
                                        </div>
                                    </div>
                                            <button className="btn btn-success" onClick={this.updateCategory}>Kaydet</button>
                                    <button className="btn btn-danger" onClick={this.cancel.bind(this)} style={{marginLeft:"10px"}}>Iptal</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
                <FooterComponent/>
                {
                    this.state.loading ? <FullPageLoading/> : null
                }
            </div>
        );
    }
}

export default UpdateCategoryComponent;