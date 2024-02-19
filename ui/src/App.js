
// function App() {
//   const [response, setResponse] = useState('')
//   const [error, setError] = useState('')
//   const url = 'http://silk-purwa.up.railway.app/api/test'
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

import { BrowserRouter as Router, Route, Routes } from 'react-router-dom'; // Mengimpor Routes
import axios from 'axios'; 

import React, { useState, useEffect } from 'react';
import './App.css';

function App() {
  const [response, setResponse] = useState('');
  const [error, setError] = useState('');

  useEffect(() => {
    const fetchData = async () => {
      try {
        const url = 'http://silk-purwa.up.railway.app/api/test';
        const resp = await axios.get(url);
        setResponse(resp.data);
      } catch (err) {
        setError(err.toString());
      }
    };

    fetchData();
  }, []);

  return (
    <Router>
      <div>
        <Routes> {/* Menggunakan Routes */}
          <Route path="/home" element={<div> {/* Gunakan element prop untuk menentukan komponen yang akan ditampilkan */}
              <h1>Spring React Testing PROPENFROZ</h1>
              <hr />
              {error ? <div>{error}</div> : <div>{response}</div>}
            </div>}
          />
        </Routes>
      </div>
    </Router>
  );
}

export default App;
