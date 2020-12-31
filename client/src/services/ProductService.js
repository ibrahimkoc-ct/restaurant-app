import axios from 'axios';

const Product_Api_Base_URL = "http://localhost:8080/category/list";
const Product_Api_Pay_URL = "http://localhost:8080/order";
const Product_Api_URL = "http://localhost:8080/product/search/";

class ProductService {

    getCategory(token) {
        return axios.get(Product_Api_Base_URL, {
            headers: {
                Authorization: token

            }
        });
    }

    pay(order, token) {
        return axios.post(Product_Api_Pay_URL, order, {
            headers: {
                Authorization: token

            }
        });

    }
    getProduct(token,id,page){
        return axios.get(Product_Api_URL+id+"/?page="+page,{
        headers: {
            Authorization: token
        }
    });
    }
}

export default new ProductService()