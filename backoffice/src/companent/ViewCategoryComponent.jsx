import React, {Component} from 'react';
import UserService from "../services/UserService";
import CategoryService from "../services/CategoryService";
import HeaderComponent from "./HeaderComponent";

class ViewCategoryComponent extends Component {
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
        CategoryService.viewCategory(sessionStorage.getItem("view-category")).then(res =>{
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
                    <h2 className="text-center">Kullanıcı Detayları</h2>
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

export default ViewCategoryComponent;