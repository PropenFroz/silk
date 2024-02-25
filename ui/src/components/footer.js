import React from 'react'
import Navbar from 'react-bootstrap/Navbar'

export default function footer() {
    return (
        <Navbar variant="light" expand="md" sticky="bottom" className="justify-content-center"> 
           <strong> Created with <span role="img" aria-label="love">&#128150;</span> </strong>
        </Navbar>
    )
}
