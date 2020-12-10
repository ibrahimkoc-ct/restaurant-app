import axios from "axios";

const Category_Api_Base_URL="http://localhost:8080/category/list";
const Category_Api_Add_URL="http://localhost:8080/category/add"
const Category_Api_Delete_URL="http://localhost:8080/category/delete";
const Category_Api_View_URL="http://localhost:8080/category/id";
const Category_Api_Update_URL="http://localhost:8080/category/update";
const Category_Api_ProductId_URL="http://localhost:8080/category/product/id";



class CategoryService {
    getCategory(token) {
        return axios.get(Category_Api_Base_URL,{
            headers: {
                Authorization:token
            }});

    }
    addCategory(category,token) {
        return axios.post(Category_Api_Add_URL,category,{
            headers: {
                Authorization:token
            }});

    }
    deleteCategory(id,token) {
        return axios.delete(Category_Api_Delete_URL+ '/' + id,{
            headers: {
                Authorization:token
            }});

    }
    viewCategory(id,token) {
        return axios.get(Category_Api_View_URL+'/'+id,{
            headers: {
                Authorization:token
            }});

    }
    updateCategory(category,token) {
        return axios.put(Category_Api_Update_URL,category,{
            headers: {
                Authorization:token
            }});

    }
    getCategoryProductId(id,token){
        return axios.get(Category_Api_ProductId_URL+'/'+id,{
            headers: {
                Authorization:token
            }});
    }





}
export default new CategoryService()