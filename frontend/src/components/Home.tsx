import { useState } from "react";
import API from "../config/ApiConfig";

export interface Product {
  id: number
  name: string
  description: string
  price: number
}

function Home() {
  const [products, setProducts] = useState([]);
  API.get('products')
  .then((response) => {
    setProducts(response.data)
  })
  .catch(() => window.location.href = '/login')  

  function order(id: number) {
    API.post('order', null, {
      params: {
        id: id
      }
    })
    .then((response)=>alert(response.data))
    .catch((response)=>alert('Error'))
  }
  return (<>
    <table>
      {products && products.map((item: Product) => {
      return <tr>
        <td>{item.name}</td>
        <td>{item.description}</td>
        <td>{(item.price / 100).toLocaleString("en-US", {style: "currency", currency: "USD", minimumFractionDigits: 2})}</td>
        <td><button onClick={()=>order(item.id)}>Order</button></td>
      </tr>
      })}
    </table>
  </>);
}

export default Home;