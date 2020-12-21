import axios from 'axios';

const Product_Api_Base_URL = "http://localhost:8080/category/list";
const Product_Api_Pay_URL = "http://localhost:8080/productsales/add";

class ProductService {

    getCategory(token) {
        return axios.get(Product_Api_Base_URL, {
            headers: {
                Authorization: token

            }
        });
    }

    pay(product, token) {
        return axios.post(Product_Api_Pay_URL, product, {
            headers: {
                Authorization: token

            }
        });

    }
}

export default new ProductService()