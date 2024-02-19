import axios from 'axios'
import React, { useState } from 'react'
import './App.css'

function App() {
  const [response, setResponse] = useState('')
  const [error, setError] = useState('')
  const url = 'http://silk-purwa.up.railway.app/api/test'
  axios
    .get(url)
    .then((resp) => {
      setResponse(resp.data)
    })
    .catch((err) => {
      setError(err.toString())
    })
  return (
    <div>
      <h1>Spring React Testing PROPENFROZ</h1>
      <hr />
      {error ? <div>{error}</div> : <div>{response}</div>}
    </div>
  )
}

export default App
