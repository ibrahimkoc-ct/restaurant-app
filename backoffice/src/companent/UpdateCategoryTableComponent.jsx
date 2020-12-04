import React, {Component} from 'react';
import CategoryService from "../services/CategoryService";
import HeaderComponent from "./HeaderComponent";
import FooterComponent from "./FooterComponent";
import CategoryTable from "../services/CategoryTable";

class UpdateCategoryTableComponent extends Component {
    constructor(props) {
        super(props);
        this.state = {
            id: this.props.match.params.id,
            name: '',
            description: '',
            imageToUrl: '',
            tableAmount:''
        }
        this.chargeDescriptionHandler=this.chargeDescriptionHandler.bind(this);
        this.chargeNameHandler=this.chargeNameHandler.bind(this);
        this.chargeurlToImageHandler=this.chargeurlToImageHandler.bind(this);
        this.updateCategory=this.updateCategory.bind(this);
        this.chargeTableAmountHandler=this.chargeTableAmountHandler.bind(this);


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
    chargeTableAmountHandler=(event)=>{
        this.setState({tableAmount:event.target.value});
    }
    cancel(){
        this.props.history.push('/categorytable-table');

    }

    updateCategory= (e) =>{
        e.preventDefault()
        let category={id:this.state.id,name: this.state.name,description: this.state.description,imageToUrl: this.state.imageToUrl,tableAmount:this.state.tableAmount};
        console.log('category=>'+JSON.stringify(category));
        CategoryTable.updateCategory(category).then(res =>{
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
                                        <input placeholder="Kategori Resim" name="imageToUrl" className="form-control"
                                               value={this.state.imageToUrl} onChange={this.chargeurlToImageHandler}/>
                                    </div>
                                    <div className="form-group">
                                        <label>Masa Sayisi</label>
                                        <input placeholder="Masa Sayisi" name="category" className="form-control"
                                               value={this.state.tableAmount} onChange={this.chargeTableAmountHandler}/>
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

export default UpdateCategoryTableComponent;