import LoginComponent from "./companent/LoginComponent";
import ProductCompanent from "./companent/ProductCompanent";
import {BrowserRouter as Router,Route,Switch} from 'react-router-dom';

function App()  {





        return (
            <div>
                <Router>


                    <div className="">
                        <Switch>
                            <Route path="/" exact component={LoginComponent}></Route>
                            <Route path="/products" component={ProductCompanent}></Route>
                        </Switch>
                    </div>


                </Router>
            </div>

        );


}
export default App;
