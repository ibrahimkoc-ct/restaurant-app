import axios from 'axios';
const Product_Api_Base_URL="http://localhost:8080/backoffice/product/list";
const Product_Api_Add_URL="http://localhost:8080/backoffice/product/add";
const Product_Api_Update_URL="http://localhost:8080/backoffice/product/update";
const Product_Api_Delete_URL="http://localhost:8080/backoffice/product/delete";
const Product_Api_ProductSales="http://localhost:8080/productsales/list";
const Product_Api_View_URL="http://localhost:8080/backoffice/product/id";
const Product_Api_Addid_URL="http://localhost:8080/backoffice/product/category/add";

class ProductService{

   getProduct(token){

   return  axios.get('http://localhost:8080/backoffice/product/list/', {
        headers: {
            Authorization:token
        }
    })
    }

   createProduct(product,token){
       return axios.post(Product_Api_Add_URL,product,{
           headers: {
               Authorization:token
           }});

   }
   updateProduct(product,productId,token){
       return axios.put(Product_Api_Update_URL+'/'+productId,product,{
           headers: {
               Authorization:token
           }});

   }
    deleteProduct(productId,token){
        return axios.delete(Product_Api_Delete_URL+'/'+productId,{
            headers: {
                Authorization:token
            }});

    }
    getProductSales(token){
       return axios.get(Product_Api_ProductSales,{
           headers: {
               Authorization:token
           }});
    }
    getProductById(productId,token){
        return axios.get(Product_Api_View_URL+'/'+productId,{
            headers: {
                Authorization: token
            }});

    }
    addProductId(product,id,token){
        return axios.post(Product_Api_Addid_URL+'/'+id,product,{
            headers: {
                Authorization:token
            }});

    }

}
export default new ProductService()