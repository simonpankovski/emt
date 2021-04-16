import React from 'react';
export default class Header extends React.Component {
    constructor(props){
        super(props)
        this.state = {   

        }
        this.handleClick = this.handleClick.bind(this);
    }
    handleClick(){
        document.cookie = "token=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";
        window.location.href = "http://localhost:3000/"
    }
    render() {
      return (
        <div>
            asd
            <a href="http://localhost:3000/">books</a> <br></br>
            <a href="http://localhost:3000/new">new</a>
            <a href="http://localhost:3000/login">login</a>
            <a href="#" onClick={this.handleClick}>logout</a>

        </div>
      );
    }
  }
