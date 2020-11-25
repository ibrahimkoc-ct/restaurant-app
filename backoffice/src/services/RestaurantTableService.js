import axios from 'axios';
const RestaurantTable_Api_Base_URL="http://localhost:8080/restauranttable/list";
const RestaurantTable_Api_add_URL="http://localhost:8080/restauranttable/add";
const RestaurantTable_Api_Delete_URL="http://localhost:8080/restauranttable/delete";
const RestaurantTable_Api_Update_URL="http://localhost:8080/restauranttable/update";
const RestaurantTable_Api_BaseId_URL="http://localhost:8080/restauranttable/id";

class RestaurantTableService {

    getRestaurantTable() {
        return axios.get(RestaurantTable_Api_Base_URL, {
            headers: {
                Authorization: sessionStorage.getItem("token")

            }
        });

    }
    deleteRestaurantTable(table) {
        return axios.delete(RestaurantTable_Api_Delete_URL + '/' + table, {
            headers: {
                Authorization: sessionStorage.getItem("token")
            }
        });

    }
    getTableById(tableId) {
        return axios.get(RestaurantTable_Api_BaseId_URL + '/' + tableId, {
            headers: {
                Authorization: sessionStorage.getItem("token")
            }
        });
    }

        addTableid(table,tableId) {
            return axios.post(RestaurantTable_Api_add_URL+'/'+tableId,table)

        }
    updateTable(table,tableId){
        return axios.put(RestaurantTable_Api_Update_URL+'/'+tableId,table,{
            headers: {
                Authorization:sessionStorage.getItem("token")
            }});

    }

}

export default new RestaurantTableService;