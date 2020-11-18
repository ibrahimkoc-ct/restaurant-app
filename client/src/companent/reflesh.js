import React from "react";
import PropTypes from "prop-types"

const Reflesh =(props) => {
    let onclick1;
    onclick1 =() => {
        window.location.reload();
    }

    return(
        <div>
            <button className="d-inline" onClick={window.location.reload(false)}></button>
        </div>
    )
}
export default Reflesh;