import React from 'react'
import { NavLink, Link } from 'react-router-dom'

import Container from 'react-bootstrap/Container'
import Dropdown from 'react-bootstrap/Dropdown'
import Navbar from 'react-bootstrap/Navbar'
import Nav from 'react-bootstrap/Nav'

import '../styles/navbar.css'

const selected = {
        color: "rgba(0,0,0,.4)"
    }

const navbar = (props) => {
    return (
        <Navbar variant="light" expand="md" fixed="top">
            <Container className="d-flex justify-content-between">
                {!props.welcome && (
                    <React.Fragment>
                        <Navbar.Toggle aria-controls="navbar" />
                        <Navbar.Collapse id="navbar" className="justify-content-end">
                            <Nav>         
                                <Nav.Item>                          
                                    <Dropdown as="div">                                                                            
                                        <Dropdown.Toggle as="div"><NavLink to="/akademik" activeStyle={selected}>Akademik</NavLink></Dropdown.Toggle>
                                        <Dropdown.Menu>
                                        </Dropdown.Menu>
                                    </Dropdown>
                                </Nav.Item>
                                <Nav.Item>                          
                                    <Dropdown as="div">                                    
                                        <Dropdown.Toggle as="div">
                                            <NavLink to="/kampus" activeStyle={selected}>Info Silk</NavLink>
                                        </Dropdown.Toggle>
                                        <Dropdown.Menu>
                                            <Dropdown.Item href="/silk/kampus#fakultas">Silk</Dropdown.Item>
                                        </Dropdown.Menu>
                                    </Dropdown>
                                </Nav.Item>
                                <Nav.Item>                          
                                    <Dropdown as="div">                                    
                                        <Dropdown.Toggle as="div">
                                            <NavLink to="/silk" activeStyle={selected}>Info Silk</NavLink>
                                        </Dropdown.Toggle>
                                        <Dropdown.Menu>
                                            <Dropdown.Item href="/silk/test#test">Test</Dropdown.Item>
                                        </Dropdown.Menu>
                                    </Dropdown>
                                </Nav.Item>   
                                <Nav.Item>
                                    <NavLink to="/informasi" activeStyle={selected}>Kanal informasi</NavLink>
                                </Nav.Item>                        
                            </Nav>
                        </Navbar.Collapse>
                    </React.Fragment>
                ) }                
            </Container>
        </Navbar>
    )
}

export default navbar
