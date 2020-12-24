import React, {Component} from 'react';
import axios from 'axios';


const Login_Api_List_URL = "http://localhost:8080/user/user-login";

class UserService extends Component {


    getLogin(token) {
        return axios.get(Login_Api_List_URL, {
            headers: {
                Authorization: token
            }
        })
    }
}

export default new UserService;