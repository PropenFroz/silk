
// import axios from 'axios'; 

// import React, { useState, useEffect } from 'react';
// import './App.css';

// function App() {
//   const [response, setResponse] = useState('')
//   const [error, setError] = useState('')
//   const url = 'https://silk-purwa.up.railway.app/api/test'
//   axios
//     .get(url)
//     .then((resp) => {
//       setResponse(resp.data)
//     })
//     .catch((err) => {
//       setError(err.toString())
//     })
//   return (
//     <div>
//       <h1>Spring React Testing PROPENFROZ</h1>
//       <hr />
//       {error ? <div>{error}</div> : <div>{response}</div>}
//     </div>
//   )
// }

// export default App

import React from 'react';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom'

import WelcomePage from './pages/welcome'
import TestBackend1 from './pages/testbackend1'

function App() {  
  return (
    <Router basename="/">
      <Switch>
        <Route path="/welcome"><WelcomePage /></Route>
        <Route path="/testbackend1"><TestBackend1 /></Route>
      </Switch>
    </Router>
  );
}

export default App;

