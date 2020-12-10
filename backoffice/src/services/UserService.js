import axios from 'axios';

import {Component} from "react";
import LoginComponent from "../companent/LoginComponent";
const User_Api_Add_URL="http://localhost:8080/users/add";
const Auth_Api_Add_URL="http://localhost:8080/auth/add";

const User_Api_Viwe_URL="http://localhost:8080/user/id";

const User_Api_List_URL="http://localhost:8080/users/listall";
const UserOne_URL="http://localhost:8080/users/loadadminusers/";
const AutOne_URL="http://localhost:8080/auth/loadadminauth/";
const AutOne_Base_URL="http://localhost:8080/auth/listall/";
const Auth_Api_Delete_URL="http://localhost:8080/auth/delete";
const User_Api_Delete_URL="http://localhost:8080/users/delete";
const Users_Api_Update_URL="http://localhost:8080/users/update";
const Auth_Api_Update_URL="http://localhost:8080/auth/update";
const Users_Api_Viwe_URL="http://localhost:8080/users/id";
const Auth_Api_Viwe_URL="http://localhost:8080/auth/id";
const ServerInfo_Api_Viwe_URL="http://localhost:8080/server-info";




class UserService {

     getUser(token) {


        return axios.get(User_Api_List_URL, {
            headers: {
                Authorization: token

            }
        });

    }

     getAuth(token) {
        return axios.get(AutOne_Base_URL, {
            headers: {
                Authorization: token

            }
        });

    }

     getList() {
        return axios.get(User_Api_List_URL, {
            headers: {
                Authorization: 'Basic YWRtaW46cGFzczE='

            }
        });

    }

     createUser(user,token) {
        return axios.post(User_Api_Add_URL, user, {
            headers: {
                Authorization: token
            }
        });

    }

     createAuth(user,token) {
        return axios.post(Auth_Api_Add_URL, user, {
            headers: {
                Authorization: token
            }
        });

    }

     updateUser(user,token) {
        return axios.put(Users_Api_Update_URL, user, {
            headers: {
                Authorization: token
            }
        });

    }

     updateAuth(auth,token) {
        return axios.put(Auth_Api_Update_URL, auth, {
            headers: {
                Authorization: token
            }
        });

    }

     deleteUser(username,token) {
        return axios.delete(User_Api_Delete_URL + '/' + username, {
            headers: {
                Authorization: token
            }
        });

    }

     deleteAuth(username,token) {
        return axios.delete(Auth_Api_Delete_URL + '/' + username, {
            headers: {
                Authorization: token
            }
        });

    }


     getUserById(userId,token) {
        return axios.get(User_Api_Viwe_URL + '/' + userId, {
            headers: {
                Authorization: token
            }
        });

    }

     getUsersById(userId,token) {
        return axios.get(Users_Api_Viwe_URL + '/' + userId, {
            headers: {
                Authorization: token
            }
        });

    }

     getAuthById(userId,token) {
        return axios.get(Auth_Api_Viwe_URL + '/' + userId, {
            headers: {
                Authorization: token
            }
        });

    }

     oneUser() {
        return axios.get(UserOne_URL)
    }

     oneAuth() {
        return axios.get(AutOne_URL)

    }

     getServerInfo(token) {

        return axios.get(ServerInfo_Api_Viwe_URL, {
            headers: {
                Authorization: token
            }
        });
    }
}

export default new UserService();
