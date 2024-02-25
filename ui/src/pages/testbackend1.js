import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom'

import Navbar from '../components/navbar'
import Footer from '../components/footer'
import Container from 'react-bootstrap/Container'
import CardDeck from 'react-bootstrap/CardDeck'
import Card from 'react-bootstrap/Card'

import Styles from '../styles/welcome'

// const directory = [
//         { title: ["Laporan", "Transaksi"], to: "/laporan", class:"laporan" },
//     ]

export default function TestBackend1() {
    const [response, setResponse] = useState(null);
    const [error, setError] = useState(null);

    useEffect(() => {
        const url = 'http://localhost:8080/api/test';

        fetch(url)
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                return response.json();
            })
            .then(data => {
                setResponse(data);
            })
            .catch(error => {
                setError(error.toString());
            });
    }, []);

    return (
        <Styles>
            <Navbar testbackend1={true} />
            <div className="circle1"></div>
            <div id="testbackend1">
                <Container>
                    <div className="gap"></div>
                    <h2>Ini ngarah ke,</h2>
                    <h1><b>localhost:8080/api/test</b></h1>
                    {error ? <div>{error}</div> : (response ? <div>{JSON.stringify(response)}</div> : <div>Loading...</div>)}
                    <p> ini messagenya </p>
                    <div className="gap"></div>
                </Container>
            </div>
            <Footer />
        </Styles>
    );
}