import React from 'react'
import { Link } from 'react-router-dom'

import Navbar from '../components/navbar'
import Footer from '../components/footer'
import Container from 'react-bootstrap/Container'
import CardDeck from 'react-bootstrap/CardDeck'
import Card from 'react-bootstrap/Card'

import Styles from '../styles/welcome'

const directory = [
        { title: ["Laporan", "Transaksi"], to: "/laporan", class:"laporan" },
    ]

export default function Welcome() {
    return (
        <Styles>
            <Navbar welcome={true} />
                <div className="circle1"></div>
            <div id="welcome">
                <Container>
                    <div className="gap"></div>
                    <h2>Selamat Datang,</h2>
                    <h1><b>Karyawan Purwacarakaa!</b></h1>
                    <p> ini ada di src/pages/welcome.js </p>
                    <div className="gap"></div>
                </Container>
            </div>
            <Footer />
        </Styles>
    )
}

