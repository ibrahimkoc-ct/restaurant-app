
import './App.css';
import React from 'react';
import ListComponent from "./companent/product/ListComponent";

import {BrowserRouter as Router,Route,Switch} from 'react-router-dom';
import CreateProductComponent from "./companent/product/CreateProductComponent";
import UpdateProductComponent from "./companent/product/UpdateProductComponent";
import ProductSalesTable from "./companent/orders/ProductSalesTable";
import UserListComponent from "./companent/user/UserListComponent"
import CreateUserComponent from "./companent/user/CreateUserComponent";
import UpdateUserComponent from "./companent/user/UpdateUserComponent";
import LoginComponent from "./companent/homepage/LoginComponent"
import ViewUserComponent from "./companent/user/ViewUserComponent";
import ViewProductComponent from "./companent/product/ViewProductComponent"
import AuthListComponent from "./companent/role/AuthListComponent";
import UpdateAuthComponent from "./companent/role/UpdateAuthComponent";
import ViewAuthComponwnt from "./companent/role/ViewAuthComponwnt";
import CategoryListComponent from "./companent/category/CategoryListComponent";
import CreateCategoryComponent from "./companent/category/CreateCategoryComponent";
import ViewCategoryComponent from "./companent/category/ViewCategoryComponent";
import UpdateCategoryComponent from "./companent/category/UpdateCategoryComponent";
import CategoryProductList from "./companent/category/CategoryProductList";
import ServerListInfoComponent from "./companent/homepage/ServerListInfoComponent";
import CategoryTableListComponent from "./companent/categoryTable/CategoryTableListComponent";
import CreateCategoryTableComponent from "./companent/categoryTable/CreateCategoryTableComponent";
import UpdateCategoryTableComponent from "./companent/categoryTable/UpdateCategoryTableComponent";
import ViewCategoryTable from "./companent/categoryTable/ViewCategoryTable";
import WaiterListComponent from "./companent/waiter/WaiterListComponent";
import CreateWaiterComponent from "./companent/waiter/CreateWaiterComponent";
import ViewWaiterComponent from "./companent/waiter/ViewWaiterComponent";
import UpdateWaiterComponent from "./companent/waiter/UpdateWaiterComponent";
import CreateMediaComponent from "./companent/hookComponent/media/CreateMediaComponent";
import {BackofficeProvider} from "./BackofficeContext";
import CreateRoleComponentHook from "./companent/hookComponent/role/CreateRoleComponentHook"
import {useState} from 'react';
import UpdateRoleComponentHook from "./companent/hookComponent/role/UpdateRoleComponentHook";
import ListRoleComponentHook from "./companent/hookComponent/role/ListRoleComponentHook";
import ViewRoleComponent from "./companent/hookComponent/role/ViewRoleComponent";
import OrdersTable from "./companent/hookComponent/orders/OrdersTable";


const backoffice={token:'',username:''}
export const AppContext=React.createContext();


function App() {
    const[appState,setAppState]=useState({
        username:'admin',
        password:'password',
        token:'Basic YWRtaW46cGFzczE='
    })


  return (

      <div>
          <AppContext.Provider value={{appState:appState,setAppState:setAppState}}>
          <BackofficeProvider value={backoffice}>
          <Router>


      <div>
          <Switch>
              <Route path="/" exact component={LoginComponent}></Route>
              <Route path="/products" component={ListComponent}></Route>
              <Route path="/add-product" component={CreateProductComponent}></Route>
              <Route path="/update-product/:id" component={UpdateProductComponent}></Route>
              <Route path="/salestable" component={ProductSalesTable}></Route>
              <Route path="/user-table" component={UserListComponent}></Route>
              <Route path="/add-users" component={CreateUserComponent}></Route>
              <Route path="/update-user/:id" component={UpdateUserComponent}></Route>
              <Route path="/update-auth/:id" component={UpdateAuthComponent}></Route>
              <Route path="/view-user/:id" component={ViewUserComponent}></Route>
              <Route path="/view-product/:id" component={ViewProductComponent}></Route>
              <Route path="/view-auth/:id" component={ViewAuthComponwnt}></Route>
              <Route path="/auth-table" component={AuthListComponent}></Route>
              <Route path="/category-table" component={CategoryListComponent}></Route>
              <Route path="/add-category" component={CreateCategoryComponent}></Route>
              <Route path="/update-category/:id" component={UpdateCategoryComponent}></Route>
              <Route path="/update-categorytable/:id" component={UpdateCategoryTableComponent}></Route>
              <Route path="/view-category/:id" component={ViewCategoryComponent}></Route>
              <Route path="/view-product-category/:id" component={CategoryProductList}></Route>
              <Route path="/server-info" component={ServerListInfoComponent}></Route>
              <Route path="/categorytable-table" component={CategoryTableListComponent}></Route>
              <Route path="/add-categorytable" component={CreateCategoryTableComponent}></Route>
              <Route path="/view-categorytable/:id" component={ViewCategoryTable}></Route>
              <Route path="/waiter-table" component={WaiterListComponent}></Route>
              <Route path="/add-waiter-table" component={CreateWaiterComponent}></Route>
              <Route path="/add-media" component={CreateMediaComponent}></Route>
              <Route path="/role-add" component={CreateRoleComponentHook}></Route>
              <Route path="/view-waiter-table/:id" component={ViewWaiterComponent}></Route>
              <Route path="/update-waiter-table/:id" component={UpdateWaiterComponent}></Route>
              <Route path="/update-role/:id" component={UpdateRoleComponentHook}></Route>
              <Route path="/list-role" component={ListRoleComponentHook}></Route>
              <Route path="/view-role/:id" component={ViewRoleComponent}></Route>
              <Route path="/orders-table" component={OrdersTable}></Route>

          </Switch>
      </div>
          </Router>

          </BackofficeProvider>
          </AppContext.Provider>
      </div>

  );

}

export default App;
