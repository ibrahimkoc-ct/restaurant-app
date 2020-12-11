import React, {Component} from 'react';
import axios from 'axios';
const User_Api_Base_URL="http://localhost:8080/users/listall";
const Login_Api_List_URL="http://localhost:8080/user/user-login";
class UserService extends Component {
    getList(){
        return axios.get(User_Api_Base_URL,{
            headers: {
                Authorization: 'Basic YWRtaW46cGFzczE='

            }});

    }
    getLogin(token){
        return axios.get(Login_Api_List_URL,{
            headers:{
                Authorization: token
            }
        })
    }
}

export default new UserService;