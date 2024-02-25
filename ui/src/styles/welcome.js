import styled from 'styled-components'

const Styles = styled.div`
    background-color: #F8F8F8;
    min-height: 100vh;
    text-align: center;

    .circle1, .circle2, .circle3, .circle4 {
        background-color: rgba(102, 153, 255, .2);
        border-radius: 50%;
        position: fixed;
        z-index: 0;
    }

    .circle1, .circle2 {
        background-color: rgba(255, 98, 209, 0.25);
    }

    .circle1 {
        width: 21vw;
        height: 21vw;
        left: 20%;
        top: -10%;
    }

    .circle2 {
        width: 22vw;
        height: 22vw;
        right: -10%;
        top: 75%;
    }

    .circle3 {
        width: 30vw;
        height: 30vw;
        left: -5%;
        bottom: -15%;
    }

    .circle4 {
        width: 20vw;
        height: 20vw;
        left: 60%;
        top: 30%;
    }

    #welcome {
        position: relative;
        z-index: 1;
        min-height: 100vh;
    }

    .navbar-light{
        background: #F4F4F4;
    }

    p {
        max-width: 500px;
        margin: 2rem auto 5rem auto;
    }
    
    .card-img-top {
        border-radius: 30px;
    }

    .card {
        padding: 0;
    }

    .card:hover {
        position: relative;
        animation: up;
        animation-duration: 0.5s;
        bottom:20px;        
        border-radius: 30px;
        overflow: hidden;
    }

    .card a:hover {        
        color: white;
        text-decoration: none;
        transition: all .7s ease; 
    }

    .akademik:hover {
        background-color: #05AC72;
    }

    .kampus:hover {
        background-color: #FFB400;
    }

    .fasilkom:hover {
        background-color: #6699FF;
    }

    .informasi:hover {
        background-color: #FF62D1;
    }

    @media (max-width:991px) {
        .card-deck > .card{
            min-width: 40%;
            max-width: 50%;
            margin-bottom: 2rem;
        }        
    }

    @media (max-width:576px) {
        .card-deck .card{
            min-width: 100%;
            max-width: 100%;
        }
        .circle1 {
            width: 25vh;
            height: 25vh;
        }    
        .circle2 {
            width: 22vh;
            height: 22vh;
        }    
        .circle3 {
            width: 30vh;
            height: 30vh;
        }    
        .circle4 {
            width: 20vh;
            height: 20vh;
        }
    }

    @keyframes up{
        from{
            bottom:0px;
            opacity: 0.5;
        }
        to{
            bottom:20px;
            opacity: 1;
        }
    }
    
    @keyframes down{
        from{
            bottom:20px;
        }
        to{
            bottom:0px;
        }
    }
`

export default Styles