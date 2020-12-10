import React, {Component} from 'react'

const ClientContext = React.createContext()

class ClientContextProvider extends Component{
    state = {
        token: {},
        username:{},
        waiter:{}
    }
    setToken = (token) => {
        this.setState((prevState) => ({token}))
    }
    setUsername=(username)=>{
        this.setState((prevState)=>({username}))
    }
    setWaiter=(waiter)=>{
        this.setState((prevState)=>({waiter}))
    }

    render() {
        const {children} = this.props
        const {token} = this.state
        const {setToken} = this
        const {username} = this.state
        const {setUsername} = this
        const {waiter} = this.state
        const {setWaiter} = this
        return (
            <ClientContext.Provider value={{token, setToken,username,setUsername,waiter,setWaiter}}>{children}</ClientContext.Provider>
        )
    }
}

export default ClientContext
export {ClientContextProvider}