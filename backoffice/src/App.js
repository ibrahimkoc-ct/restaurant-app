import logo from './logo.svg';
import './App.css';

import ListComponent from "./companent/ListComponent";
import HeaderComponent from "./companent/HeaderComponent";
import FooterComponent from "./companent/FooterComponent";
import {BrowserRouter as Router,Route,Switch} from 'react-router-dom';
import CreateProductComponent from "./companent/CreateProductComponent";
import UpdateProductComponent from "./companent/UpdateProductComponent";
import ProductSalesTable from "./companent/ProductSalesTable";
import UserListComponent from "./companent/UserListComponent"
import CreateUserComponent from "./companent/CreateUserComponent";
import UpdateUserComponent from "./companent/UpdateUserComponent";
import LoginComponent from "./companent/LoginComponent"
import React from "react";
import LoginHeaderComponent from "./companent/LoginHeaderComponent";
import ViewUserComponent from "./companent/ViewUserComponent";
import ViewProductComponent from "./companent/ViewProductComponent"
import AuthListComponent from "./companent/AuthListComponent";
import UpdateAuthComponent from "./companent/UpdateAuthComponent";
import ViewAuthComponwnt from "./companent/ViewAuthComponwnt";
import CategoryListComponent from "./companent/CategoryListComponent";
import CreateCategoryComponent from "./companent/CreateCategoryComponent";
import ViewCategoryComponent from "./companent/ViewCategoryComponent";
import UpdateCategoryComponent from "./companent/UpdateCategoryComponent";
import CategoryProductList from "./companent/CategoryProductList";
import ServerListInfoComponent from "./companent/ServerListInfoComponent";
import CategoryTableListComponent from "./companent/CategoryTableListComponent";
import CreateCategoryTableComponent from "./companent/CreateCategoryTableComponent";
import UpdateCategoryTableComponent from "./companent/UpdateCategoryTableComponent";
import ViewCategoryTable from "./companent/ViewCategoryTable";
import WaiterListComponent from "./companent/WaiterListComponent";
import CreateWaiterComponent from "./companent/CreateWaiterComponent";
import ViewWaiterComponent from "./companent/ViewWaiterComponent";
import UpdateWaiterComponent from "./companent/UpdateWaiterComponent";

// import UpdateRestaurantTable from "./companent/UpdateRestaurantTable";
// import ViewRestaurantTable from "./companent/ViewRestaurantTable";
// import CreateRestaurantTableComponent from "./companent/CreateRestaurantTableComponent";
// import RestaurantTableListComponent from "./companent/RestaurantTableListComponent";

function App() {
  return (
      <div>
          <Router>


      <div className="">
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
              <Route path="/view-waiter-table/:id" component={ViewWaiterComponent}></Route>
              <Route path="/update-waiter-table/:id" component={UpdateWaiterComponent}></Route>

          </Switch>
      </div>


          </Router>
      </div>
  );

}

export default App;
