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

     getUser() {


        return axios.get(User_Api_List_URL, {
            headers: {
                Authorization: sessionStorage.getItem("token")

            }
        });

    }

     getAuth() {
        return axios.get(AutOne_Base_URL, {
            headers: {
                Authorization: sessionStorage.getItem("token")

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

     createUser(user) {
        return axios.post(User_Api_Add_URL, user, {
            headers: {
                Authorization: sessionStorage.getItem("token")
            }
        });

    }

     createAuth(user) {
        return axios.post(Auth_Api_Add_URL, user, {
            headers: {
                Authorization: sessionStorage.getItem("token")
            }
        });

    }

     updateUser(user, userId) {
        return axios.put(Users_Api_Update_URL, user, {
            headers: {
                Authorization: sessionStorage.getItem("token")
            }
        });

    }

     updateAuth(auth, authid) {
        return axios.put(Auth_Api_Update_URL, auth, {
            headers: {
                Authorization: sessionStorage.getItem("token")
            }
        });

    }

     deleteUser(username) {
        return axios.delete(User_Api_Delete_URL + '/' + username, {
            headers: {
                Authorization: sessionStorage.getItem("token")
            }
        });

    }

     deleteAuth(username) {
        return axios.delete(Auth_Api_Delete_URL + '/' + username, {
            headers: {
                Authorization: sessionStorage.getItem("token")
            }
        });

    }


     getUserById(userId) {
        return axios.get(User_Api_Viwe_URL + '/' + userId, {
            headers: {
                Authorization: sessionStorage.getItem("token")
            }
        });

    }

     getUsersById(userId) {
        return axios.get(Users_Api_Viwe_URL + '/' + userId, {
            headers: {
                Authorization: sessionStorage.getItem("token")
            }
        });

    }

     getAuthById(userId) {
        return axios.get(Auth_Api_Viwe_URL + '/' + userId, {
            headers: {
                Authorization: sessionStorage.getItem("token")
            }
        });

    }

     oneUser() {
        return axios.get(UserOne_URL)
    }

     oneAuth() {
        return axios.get(AutOne_URL)

    }

     getServerInfo() {

        return axios.get(ServerInfo_Api_Viwe_URL, {
            headers: {
                Authorization: sessionStorage.getItem("token")
            }
        });
    }
}

export default new UserService();
