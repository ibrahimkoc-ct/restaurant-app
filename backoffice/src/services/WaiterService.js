import axios from "axios";

const Waiter_Api_Base_URL = "http://localhost:8080/waiter/list";
const Waiter_Api_Add_URL = "http://localhost:8080/waiter/add"
const Waiter_Api_Delete_URL = "http://localhost:8080/waiter/delete";
const Waiter_Api_View_URL = "http://localhost:8080/waiter/id";
const Waiter_Api_Update_URL = "http://localhost:8080/waiter/update";

class WaiterService {
    getWaiter(token) {
        return axios.get(Waiter_Api_Base_URL, {
            headers: {
                Authorization: token
            }
        });


    }

    addWaiter(waiter, token) {
        return axios.post(Waiter_Api_Add_URL, waiter, {
            headers: {
                Authorization: token
            }
        });

    }

    deleteWaiter(id, token) {
        return axios.delete(Waiter_Api_Delete_URL + '/' + id, {
            headers: {
                Authorization: token
            }
        });

    }

    viewWaiter(id, token) {
        return axios.get(Waiter_Api_View_URL + '/' + id, {
            headers: {
                Authorization: token
            }
        });

    }

    updateWiew(waiter, token) {
        return axios.put(Waiter_Api_Update_URL, waiter, {
            headers: {
                Authorization: token
            }
        });

    }
}

export default new WaiterService()