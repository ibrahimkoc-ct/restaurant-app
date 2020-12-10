import axios from "axios";

const Waiter_Api_Base_URL="http://localhost:8080/waiter/list";
class WaiterService{
    getWaiter(token) {
        return axios.get(Waiter_Api_Base_URL,{
            headers: {
                Authorization:token
            }});


    }
}
export default new WaiterService()