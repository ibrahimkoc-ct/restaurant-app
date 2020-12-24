import axios from 'axios';
const Customer_Api_Base_URL="http://localhost:8080/customer";
const Customer_Api_Page_URL="http://localhost:8080/customer/page?page=";

class CustomerService{
    getCustomer(token){
      return axios.get(Customer_Api_Base_URL,{
            headers: {
                Authorization:token
            }
        })
    }
    addCustomer(customer,token){
        return axios.post(Customer_Api_Base_URL,customer,{
            headers: {
                Authorization:token
            }
        })
    }
    updateCustomer(customer,token){
        return axios.put(Customer_Api_Base_URL,customer,{
            headers: {
                Authorization:token
            }
        })
    }
    deleteCustomer(id,token){
        return axios.delete(Customer_Api_Base_URL+'/'+id,{
            headers: {
                Authorization:token
            }
        })
    }
    getCustomerById(id,token){
        return axios.get(Customer_Api_Base_URL+'/'+id,{
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