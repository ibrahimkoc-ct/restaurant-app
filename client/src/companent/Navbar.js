import React from "react";
import PropTypes from "prop-types"

const Navbar =(props) => {
    return(
        <div>

            <h1 className="text-center">{props.title}</h1>

        </div>
    )
}
Navbar.propTypes={
    title: PropTypes.string.isRequired
}
Navbar.defaultProps ={
    title: "Default App"
}
export default Navbar;