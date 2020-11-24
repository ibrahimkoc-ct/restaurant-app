import axios from 'axios';
const Product_Api_Base_URL="http://localhost:8080/category/list";
const Product_Api_Category_URL="http://localhost:8080/client/product/category/"
const Product_Api_Pay_URL="http://localhost:8080/productsales/add";
class ProductService {
    getProduct() {
        return axios.get(Product_Api_Base_URL,{
            headers: {
                Authorization: sessionStorage.getItem("token")

            }});
    }
    getCategory(){
        return axios.get(Product_Api_Base_URL,{
            headers: {
                Authorization: sessionStorage.getItem("token")

            }});
    }
    pay(product){
        return axios.post(Product_Api_Pay_URL,product,{
            headers: {
                Authorization: sessionStorage.getItem("token")

            }});

        }
}
export default new ProductService()