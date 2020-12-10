import axios from 'axios';
const Table_Api_Base_URL="http://localhost:8080/categorytable/list";

class TableService {
    getCategory(token) {
        return axios.get(Table_Api_Base_URL,{
            headers: {
                Authorization: token

            }});
    }




}
export default new TableService;