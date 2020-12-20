import axios from 'axios';
const CategoryTable_Api_Base_URL="http://localhost:8080/categorytable/list";
const CategoryTable_Api_add_URL="http://localhost:8080/categorytable/add";
const CategoryTable_Api_Delete_URL="http://localhost:8080/categorytable/delete";
const CategoryTable_Api_Update_URL="http://localhost:8080/categorytable/update";
const CategoryTable_Api_getProductByCategort_URL="http://localhost:8080/categorytable/table/id";
const CategoryTable_Api_getByCategort_URL="http://localhost:8080/categorytable/id";



class CategoryTable  {
    getCategory(token) {
        return axios.get(CategoryTable_Api_Base_URL,{
            headers: {
                Authorization:token
            }});


    }
    addCategory(category,token) {
        return axios.post(CategoryTable_Api_add_URL,category,{
            headers: {
                Authorization:token
            }});

    }

    deleteCategory(id,token) {
        return axios.delete(CategoryTable_Api_Delete_URL+ '/' + id,{
            headers: {
                Authorization:token
            }});

    }
    viewCategory(id,token) {
        return axios.get(CategoryTable_Api_getByCategort_URL+'/'+id,{
            headers: {
                Authorization:token
            }});

    }
    updateCategory(category,token) {
        return axios.put(CategoryTable_Api_Update_URL,category,{
            headers: {
                Authorization:token
            }});

    }

}

export default new CategoryTable;