import {useContext, useEffect, useState} from "react";
import {AppContext} from "../../../App";


const CreateUserComponentHook = () => {
    const context = useContext(AppContext);
    const [token, setToken] = useState();
    const [role, setRole] = useState();

    useEffect(() => {
        let appState = Object.assign({}, context.appState);
        setToken(appState.token)
    });
    const cancelButton = () => {

        history.push('/user-table');
    }

}
export default CreateUserComponentHook;