import axios from 'axios';
const Customer_Api_Base_URL="http://localhost:8080/customer";
const Customer_Api_Page_URL="http://localhost:8080/customer/slice?page=";

class CustomerService{
     addCustomer(customer,token){
        return axios.post(Customer_Api_Base_URL,customer,{
            headers: {
                Authorization:token
            }
        })
    }

    getPageCustomer(token,page){
        return axios.get(Customer_Api_Page_URL+page,{
            headers: {
                Authorization:token
            }});
    }

}
export default new CustomerService();