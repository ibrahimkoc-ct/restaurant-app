import logo from './logo.svg';
import './App.css';
import {useState, useEffect} from 'react';
import Product from "./companent/Product";
import Navbar from "./companent/Navbar";
import AddProduct from "./companent/addProduct";
function App() {
  const [content, setContent] = useState();

  useEffect(() => {
    fetch('http://localhost:8080/product/list')
        .then(response => response.json())
        .then(data => {
          setContent(data);
        }).catch(e => {
      console.warn("e : ", e);
    });
  }, []);
  if (!content) {
    return null;
  }
  return (
      <div className="container">
        <Navbar title="Ürün Giriş Paneli"/>
          <hr/>
          <Navbar title="Ürünler"/>
        <hr/>
        {
          content.map(v => {
            return (<div>

              <Product
                  title={v.title}
                  description={v.description}
                  category={v.category}
                  price={v.price}
                  id={v.id}

              />

              <br/>
              <hr/>

            </div>)

          })
        }
        <AddProduct/>
      </div>
  );
}

export default App;
