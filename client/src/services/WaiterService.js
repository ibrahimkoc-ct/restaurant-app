import axios from "axios";

const Waiter_Api_Base_URL="http://localhost:8080/waiter/list";
class WaiterService{
    getWaiter() {
        return axios.get(Waiter_Api_Base_URL,{
            headers: {
                Authorization:sessionStorage.getItem("token")
            }});


    }
}
export default new WaiterService()