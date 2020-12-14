import axios from 'axios';

const User_Api_List_URL="http://localhost:8080/user/list";
const AutOne_Base_URL="http://localhost:8080/role/list";
const Auth_Api_Delete_URL="http://localhost:8080/role/delete";
const User_Api_Delete_URL="http://localhost:8080/user/delete";
const Users_Api_Update_URL="http://localhost:8080/user/update";
const Auth_Api_Update_URL="http://localhost:8080/role/update/";
const Users_Api_Viwe_URL="http://localhost:8080/user/id";
const Auth_Api_Viwe_URL="http://localhost:8080/role/id";
const ServerInfo_Api_Viwe_URL="http://localhost:8080/server-info";
const User_Api_Add_URL="http://localhost:8080/user/add";
const Auth_Api_Add_URL="http://localhost:8080/role/add";
const Login_Api_List_URL="http://localhost:8080/user/admin-login";


class UserService {
    getLogin(token){
        return axios.get(Login_Api_List_URL, {
            headers: {
                Authorization: token

            }
        });
    }

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

     deleteUser(id,token) {
        return axios.delete(User_Api_Delete_URL + '/' + id, {
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

     getServerInfo(token) {

        return axios.get(ServerInfo_Api_Viwe_URL, {
            headers: {
                Authorization: token
            }
        });
    }
}

export default new UserService();
