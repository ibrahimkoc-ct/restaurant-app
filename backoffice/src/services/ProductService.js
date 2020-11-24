import axios from 'axios';
const Product_Api_Base_URL="http://localhost:8080/backoffice/product/list";
const Product_Api_Add_URL="http://localhost:8080/backoffice/product/add";
const Product_Api_Update_URL="http://localhost:8080/backoffice/product/update";
const Product_Api_Delete_URL="http://localhost:8080/backoffice/product/delete";
const Product_Api_ProductSales="http://localhost:8080/productsales/list";
const Product_Api_View_URL="http://localhost:8080/backoffice/product/id";
const Product_Api_Addid_URL="http://localhost:8080/backoffice/product/category/add";

class ProductService{

   getProduct(){

   return  axios.get('http://localhost:8080/backoffice/product/list/', {
        headers: {
            Authorization:sessionStorage.getItem("token")
        }
    })
    }

   createProduct(product){
       return axios.post(Product_Api_Add_URL,product,{
           headers: {
               Authorization:sessionStorage.getItem("token")
           }});

   }
   updateProduct(product,productId){
       return axios.put(Product_Api_Update_URL+'/'+productId,product,{
           headers: {
               Authorization:sessionStorage.getItem("token")
           }});

   }
    deleteProduct(productId){
        return axios.delete(Product_Api_Delete_URL+'/'+productId,{
            headers: {
                Authorization:sessionStorage.getItem("token")
            }});

    }
    getProductSales(){
       return axios.get(Product_Api_ProductSales,{
           headers: {
               Authorization:sessionStorage.getItem("token")
           }});
    }
    getProductById(productId){
        return axios.get(Product_Api_View_URL+'/'+productId,{
            headers: {
                Authorization: sessionStorage.getItem("token")
            }});

    }
    addProductId(product,id){
        return axios.post(Product_Api_Addid_URL+'/'+id,product,{
            headers: {
                Authorization:sessionStorage.getItem("token")
            }});

    }

}
export default new ProductService()