import axios from 'axios';
const Table_Api_Base_URL="http://localhost:8080/categorytable/list";

class TableService {
    getCategory() {
        return axios.get(Table_Api_Base_URL,{
            headers: {
                Authorization: sessionStorage.getItem("token")

            }});
    }




}
export default new TableService;