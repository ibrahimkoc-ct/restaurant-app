import React, {Component} from 'react';
import axios from 'axios';
const User_Api_Base_URL="http://localhost:8080/users/listall";
class UserService extends Component {
    getList(){
        return axios.get(User_Api_Base_URL,{
            headers: {
                Authorization: 'Basic YWRtaW46cGFzczE='

            }});

    }
}

export default new UserService;