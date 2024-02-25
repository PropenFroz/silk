import React, { Component } from 'react'
import styled from 'styled-components'
import Container from 'react-bootstrap/Container'

export default class landing extends Component {

    bg = require(`../assets/${this.props.page}/bg.jpg`)
    mobile = require(`../assets/${this.props.page}/bg-mobile.jpg`)    

    render() {  
        const Landing = styled.div`
            height: calc(100vh - 56px); 
            margin-top: 56px;
            background: url(${this.bg});
            -webkit-background-position: center ${this.props.title === "Info Silk" ? "top" : "bottom"};
            -moz-background-position: center ${this.props.title === "Info Silk" ? "top" : "bottom"};
            -o-background-position: center ${this.props.title === "Info Silk" ? "top" : "bottom"};
            background-position: center ${this.props.title === "Info Silk" ? "top" : "bottom"};
            -webkit-background-size: cover;
            -moz-background-size: cover;
            -o-background-size: cover;
            background-size: cover;
            background-repeat: no-repeat;

            display: flex;
            align-items: center;

            h1 {
                font-weight: bold;
                max-width: 50%;
            }

            p {
                font-weight: normal;
                opacity: .7;
            }

            .bar {
                max-width: 75%;
                height: 7px;
                background: ${this.props.color};
            }
            
            @media (max-width:576px) {
                background: url(${this.mobile});
                align-items: flex-end;
                justify-content: center;
                text-align: center;
                -webkit-background-size: cover;
                -moz-background-size: cover;
                -o-background-size: cover;
                background-size: cover;
                background-repeat: no-repeat;
                .container {
                    padding-bottom: 25vh;
                }
                h1 {
                    max-width: 80%;
                    margin: 0 10% 1rem;
                }
                .bar {
                    max-width: 100%;
                }
            }

            @media (min-width:576px) {
                p {
                    max-width: 50%;
                }
            }
        `
        return (
            <Landing>
                <Container>
                    <h1>
                        {this.props.title}<br/>
                        <div className="bar"></div>
                    </h1>                
                    <p>{this.props.desc}</p>
                </Container>
            </Landing>
        )
    }
}