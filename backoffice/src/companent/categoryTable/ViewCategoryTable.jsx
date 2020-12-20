import React, {Component} from 'react';
import CategoryTable from "../../services/CategoryTable";
import HeaderComponent from "../homepage/HeaderComponent";
import createBrowserHistory from 'history/createBrowserHistory';
import BackofficeContext from "../../BackofficeContext";
import FullPageLoading from "../loading/FullPageLoading";
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
            token:'',
            loading:false

        }
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


        CategoryTable.viewCategory(sessionStorage.getItem("view-categorytable"),this.state.token).then(res =>{
            this.setState({
                category:res.data,loading:false})
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
                { this.state.loading ? <FullPageLoading/> : null}
            </div>
        );
    }
}

export default ViewCategoryTable;