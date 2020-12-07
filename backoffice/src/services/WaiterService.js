import axios from "axios";

const Waiter_Api_Base_URL="http://localhost:8080/waiter/list";
const Waiter_Api_Add_URL="http://localhost:8080/waiter/add"
const Waiter_Api_Delete_URL="http://localhost:8080/waiter/delete";
const Waiter_Api_View_URL="http://localhost:8080/waiter/id";
const Waiter_Api_Update_URL="http://localhost:8080/waiter/update";

class WaiterService{
    getWaiter() {
        return axios.get(Waiter_Api_Base_URL,{
            headers: {
                Authorization:sessionStorage.getItem("token")
            }});


    }
    addWaiter(waiter) {
        return axios.post(Waiter_Api_Add_URL,waiter,{
            headers: {
                Authorization:sessionStorage.getItem("token")
            }});

    }
    deleteWaiter(id) {
        return axios.delete(Waiter_Api_Delete_URL+ '/' + id,{
            headers: {
                Authorization:sessionStorage.getItem("token")
            }});

    }
    viewWaiter(id) {
        return axios.get(Waiter_Api_View_URL+'/'+id,{
            headers: {
                Authorization:sessionStorage.getItem("token")
            }});

    }
    updateWiew(waiter) {
        return axios.put(Waiter_Api_Update_URL,waiter,{
            headers: {
                Authorization:sessionStorage.getItem("token")
            }});

    }
}
export default new WaiterService()