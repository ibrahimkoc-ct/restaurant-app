import React, {Component} from 'react';
import CategoryService from "../services/CategoryService";
import CategoryTable from "../services/CategoryTable";
import HeaderComponent from "./HeaderComponent";

class ViewCategoryTable extends Component {
    constructor(props) {
        super(props);
        this.state= {

            name: "",
            description: "",
            imageToUrl: "",

            category: []


        }
    }
    componentDidMount() {
       CategoryTable.viewCategory(sessionStorage.getItem("view-categorytable")).then(res =>{
            this.setState({
                category:res.data,


            })

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


                    </div>
                </div>

            </div>
        );
    }
}

export default ViewCategoryTable;