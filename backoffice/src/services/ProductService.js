import axios from 'axios';
const Product_Api_Base_URL="http://localhost:8080/backoffice/product/list";
const Product_Api_Add_URL="http://localhost:8080/backoffice/product/add";
const Product_Api_Update_URL="http://localhost:8080/backoffice/product/update";
const Product_Api_Delete_URL="http://localhost:8080/backoffice/product/delete";
const Product_Api_ProductSales="http://localhost:8080/productsales/list";
class ProductService{
   getProduct(){
       return axios.get(Product_Api_Base_URL);
   }

   createProduct(product){
       return axios.post(Product_Api_Add_URL,product);

   }
   updateProduct(product,productId){
       return axios.put(Product_Api_Update_URL+'/'+productId,product);

   }
    deleteProduct(productId){
        return axios.delete(Product_Api_Delete_URL+'/'+productId);

    }
    getProductSales(){
       return axios.get(Product_Api_ProductSales);
    }

}
export default new ProductService()