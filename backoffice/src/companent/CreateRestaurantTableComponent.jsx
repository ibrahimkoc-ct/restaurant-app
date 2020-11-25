import React, {Component} from 'react';
import CategoryService from "../services/CategoryService";
import HeaderComponent from "./HeaderComponent";
import FooterComponent from "./FooterComponent";
import ProductService from "../services/ProductService";
import RestaurantTableService from "../services/RestaurantTableService";
import CategoryTable from "../services/CategoryTable";

class CreateRestaurantTableComponent extends Component {
    constructor(props) {
        super(props);
        this.state = {
            title: '',
            description: '',
            category: '',
            categoryid:'',
            categorylist:[],
            categoryName:"Kategori Seçiniz"
        }
        this.chargeDescriptionHandler=this.chargeDescriptionHandler.bind(this);
        this.chargeTitleHandler=this.chargeTitleHandler.bind(this);
        this.chargeCategoryHandler=this.chargeCategoryHandler.bind(this);
        this.saveTable=this.saveTable.bind(this);
        this.chargeCategoryIdHandler=this.chargeCategoryIdHandler.bind(this);

    }
    chargeTitleHandler =(event) =>{
        this.setState({title:event.target.value});
    }
    chargeDescriptionHandler =(event) =>{
        this.setState({description:event.target.value});
    }
    chargeCategoryHandler =(event) =>{
        this.setState({category:event.target.value});
    }
    chargeCategoryIdHandler =(event) =>{
        this.setState({categoryid:event.target.value});
    }

    saveTable = (e) =>{
        e.preventDefault()
        let table={id:this.state.id,title: this.state.title,description: this.state.description,category: this.state.categoryName};
        console.log('table=>'+JSON.stringify(table));
        RestaurantTableService.addTableid(table,this.state.categoryid).then(res =>{
            this.props.history.push('/restaurant-table');
        })
    }
    componentDidMount() { CategoryTable.getCategory().then((res)=>{
        this.setState({categorylist:res.data});
    });
    }

    cancel(){
        this.props.history.push('/restaurant-table');
    }
    onClickItem(e){
        this.setState({categoryid:e.id,
            categoryName:e.name
        })

    }

    render() {
        return (
            <div>
                <HeaderComponent/>
                <div className="container">
                    <div className="row">
                        <div className="card col-md-6 offset-md-3 offset-md-3">
                            <h3 className="text-center">Masa Ekle</h3>
                            <div className="card-body">
                                <form>
                                    <div className="form-group">
                                        <label>Masa Adı</label>
                                        <input placeholder="Urun Adı" name="title" className="form-control"
                                               value={this.state.title} onChange={this.chargeTitleHandler}/>

                                    </div>
                                    <div className="form-group">
                                        <label>Masa Acıklaması</label>
                                        <input placeholder="Urun acıklaması" name="description" className="form-control"
                                               value={this.state.description} onChange={this.chargeDescriptionHandler}/>

                                    </div>
                                    <div className="dropdown show">
                                        <a className="btn btn-secondary btn-block dropdown-toggle" href="#" role="button" id="dropdownMenuLink"
                                           data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                            {this.state.categoryName}
                                        </a>

                                        <div className="dropdown-menu btn-block" aria-labelledby="dropdownMenuLink">
                                            {
                                                this.state.categorylist.map(
                                                    category =>
                                                        <a className="dropdown-item" onClick={this.onClickItem.bind(this,category)}>{category.name}</a>
                                                )
                                            }

                                        </div>
                                    </div>
                                    <hr/>
                                    <button className="btn btn-success" onClick={this.saveTable}>Kaydet</button>
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

export default CreateRestaurantTableComponent;