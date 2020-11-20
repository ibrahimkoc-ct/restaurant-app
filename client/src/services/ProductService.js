import axios from 'axios';
const Product_Api_Base_URL="http://localhost:8080/client/product/list";
const Product_Api_Category_URL="http://localhost:8080/client/product/category/"
const Product_Api_Pay_URL="http://localhost:8080//productsales//add";
class ProductService {
    getProduct() {
        return axios.get(Product_Api_Base_URL);
    }
    getCategory(){
        return axios.get(Product_Api_Category_URL);
    }
    pay(product){
        return axios.post(Product_Api_Pay_URL,product);
    }
}
export default new ProductService()