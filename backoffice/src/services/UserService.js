import axios from 'axios';
import CreateProductComponent from "../companent/CreateProductComponent";
const User_Api_Base_URL="http://localhost:8080/user/list";
const User_Api_Add_URL="http://localhost:8080/user/add";
const User_Api_Update_URL="http://localhost:8080/user/update";
const User_Api_Delete_URL="http://localhost:8080/user/delete";
class UserService{
    getUser(){
        return axios.get(User_Api_Base_URL);
    }

    createUser(user){
        return axios.post(User_Api_Add_URL,user);

    }
    updateUser(user,userId){
        return axios.put(User_Api_Update_URL+'/'+userId,user);

    }
    deleteUser(userId){
        return axios.delete(User_Api_Delete_URL+'/'+userId);

    }
}
export default new UserService()