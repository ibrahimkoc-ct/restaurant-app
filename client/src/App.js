import LoginComponent from "./companent/LoginComponent";
import ProductCompanent from "./TEMP/ProductCompanent";
import {BrowserRouter as Router,Route,Switch} from 'react-router-dom';
import HomePageCompanent from "./companent/HomePageCompanent";
import TableComponent from "./companent/TableComponent";
import ResponsiveProduct from "./companent/ResponsiveProduct";

function App()  {





        return (
            <div>
                <Router>


                    <div className="">
                        <Switch>
                            <Route path="/" exact component={LoginComponent}></Route>
                            <Route path="/products" component={ResponsiveProduct}></Route>
                            <Route path="/homepage" component={HomePageCompanent}></Route>
                            <Route path="/table" component={TableComponent}></Route>
                            <Route path="/example" component={<ProductCompanent></ProductCompanent>}></Route>
                        </Switch>
                    </div>


                </Router>
            </div>

        );


}
export default App;
