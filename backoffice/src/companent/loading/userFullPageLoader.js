import React, {useState} from "react";
import FullPageLoading from "./FullPageLoading";

const useFullPageLoader =()=>{
    const [loading,setLoading] =useState(false);
    return[
        loading ?<FullPageLoading/>:null,
        ()=>setLoading(true),
        ()=>setLoading(false)
    ];
};
export default useFullPageLoader;