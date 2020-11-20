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
function App() {
  return (
      <div>
          <Router>

          <HeaderComponent/>
      <div className="container">
          <Switch>
              <Route path="/" exact component={ListComponent}></Route>
              <Route path="/products" component={ListComponent}></Route>
              <Route path="/add-product" component={CreateProductComponent}></Route>
              <Route path="/update-product/:id" component={UpdateProductComponent}></Route>
              <Route path="/salestable" component={ProductSalesTable}></Route>
              <Route path="/user-table" component={UserListComponent}></Route>
              <Route path="/add-users" component={CreateUserComponent}></Route>
              <Route path="/update-user/:id" component={UpdateUserComponent}></Route>


          </Switch>
      </div>
          <FooterComponent/>

          </Router>
      </div>
  );
}

export default App;
