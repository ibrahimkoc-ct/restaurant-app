import axios from 'axios';
const CategoryTable_Api_Base_URL="http://localhost:8080/categorytable/list";
const CategoryTable_Api_add_URL="http://localhost:8080/categorytable/add";
const CategoryTable_Api_Delete_URL="http://localhost:8080/categorytable/delete";
const CategoryTable_Api_Update_URL="http://localhost:8080/categorytable/update";
const CategoryTable_Api_getProductByCategort_URL="http://localhost:8080/categorytable/table/id";
const CategoryTable_Api_getByCategort_URL="http://localhost:8080/categorytable/id";



class CategoryTable  {
    getCategory() {
        return axios.get(CategoryTable_Api_Base_URL,{
            headers: {
                Authorization:sessionStorage.getItem("token")
            }});


    }
    addCategory(category) {
        return axios.post(CategoryTable_Api_add_URL,category,{
            headers: {
                Authorization:sessionStorage.getItem("token")
            }});

    }

    deleteCategory(id) {
        return axios.delete(CategoryTable_Api_Delete_URL+ '/' + id,{
            headers: {
                Authorization:sessionStorage.getItem("token")
            }});

    }
    viewCategory(id) {
        return axios.get(CategoryTable_Api_getByCategort_URL+'/'+id,{
            headers: {
                Authorization:sessionStorage.getItem("token")
            }});

    }
    updateCategory(category) {
        return axios.put(CategoryTable_Api_Update_URL,category,{
            headers: {
                Authorization:sessionStorage.getItem("token")
            }});

    }
    getCategoryTableId(id){
        return axios.get(CategoryTable_Api_getProductByCategort_URL+'/'+id,{
            headers: {
                Authorization:sessionStorage.getItem("token")
            }});
    }

}

export default new CategoryTable;