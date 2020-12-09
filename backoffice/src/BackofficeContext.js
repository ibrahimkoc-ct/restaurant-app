import React, {Component} from 'react'

const BackofficeContext = React.createContext()

class BackofficeProvider extends Component {
    state = {
        token: {},
        username:{}
    }
    setToken = (token) => {
        this.setState((prevState) => ({token}))
    }
    setUsername=(username)=>{
        this.setState((prevState)=>({username}))
    }


    render() {
        const {children} = this.props
        const {token} = this.state
        const {setToken} = this
        const {username} = this.state
        const {setUsername} = this
        return (
            <BackofficeContext.Provider value={{token, setToken,username,setUsername}}>{children}</BackofficeContext.Provider>
        )
    }
}

export default BackofficeContext
export {BackofficeProvider}