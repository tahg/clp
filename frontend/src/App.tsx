import React from 'react';
import { BrowserRouter, Routes, Route } from 'react-router-dom'
import './App.css';
import Home from './components/Home';
import Login from './components/Login';
import Message from './components/Message';
import Signup from './components/Signup';

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path='/' element= {<Home />} /> 
        <Route path='/signup' element= {<Signup />} /> 
        <Route path='/login' element= {<Login />} /> 
        <Route path='/message' element= {<Message />} /> 
      </Routes>
    </BrowserRouter>
  );
}

export default App;
