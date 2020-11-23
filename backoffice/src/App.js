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


          </Switch>
      </div>


          </Router>
      </div>
  );

}

export default App;
