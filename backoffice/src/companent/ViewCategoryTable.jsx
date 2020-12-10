import React, {Component} from 'react';
import CategoryService from "../services/CategoryService";
import CategoryTable from "../services/CategoryTable";
import HeaderComponent from "./HeaderComponent";
import createBrowserHistory from 'history/createBrowserHistory';
import BackofficeContext from "../BackofficeContext";
const history = createBrowserHistory({forceRefresh:true});

class ViewCategoryTable extends Component {
    static contextType = BackofficeContext;
    constructor(props) {
        super(props);
        this.state= {

            name: "",
            description: "",
            imageToUrl: "",

            category: [],
            token:''


        }
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


        CategoryTable.viewCategory(sessionStorage.getItem("view-categorytable"),this.state.token).then(res =>{
            this.setState({
                category:res.data,})
        })
    }

    render() {
        return (
            <div>
                <HeaderComponent/>
                <br></br>
                <div className="card col-md-6 offset-md-3" >
                    <h2 className="text-center">Masa Kategori Detayları</h2>
                    <div className="card-body">
                        <div className="row">
                            <h3>Kategori Adı: {this.state.category.name}</h3>
                        </div>
                        <hr></hr>

                        <div className="row">

                            <h3>Kategori Bilgileri:  {this.state.category.description}</h3>
                        </div>
                        <hr></hr>
                        <div className="row">
                            <h3>Fotograf: {this.state.category.imageToUrl}</h3>
                        </div>
                        <hr></hr>
                        <div className="row">
                            <h3>Masa Sayisi: {this.state.category.tableAmount}</h3>
                        </div>


                    </div>
                </div>

            </div>
        );
    }
}

export default ViewCategoryTable;