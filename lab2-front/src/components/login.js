import React from 'react';


export default class Login extends React.Component {
    constructor(props){
        super(props)
        this.state = {
            books: [],
            username:"",
            password:""
        }
        this.handleClick = this.handleClick.bind(this);
        this.handleUser = this.handleUser.bind(this);
        this.handlePass = this.handlePass.bind(this);
    }
    handleUser(event) {
        this.setState({username: event.target.value});
      }
      handlePass(event) {
        this.setState({password: event.target.value});
      }
    handleClick(e){
        e.preventDefault()
        console.log(this.state)
        let user = {username:this.state.username,password:this.state.password}
        
       let res = fetch('http://localhost:9090/login', {
        method: 'POST', // *GET, POST, PUT, DELETE, etc.
        
       
        headers: {
          'Content-Type': 'application/json'
          // 'Content-Type': 'application/x-www-form-urlencoded',
        },
      
        body: JSON.stringify(user) // body data type must match "Content-Type" header
      }
            ).then(res=>res.text().then(res=>document.cookie="token=Bearer "+res))
         
    }

    render() {
      return (
        <div>
            <form>
                <input type="text" value={this.state.username} onChange={this.handleUser} />
                <input type="password" value={this.state.password} onChange={this.handlePass} />
                <button onClick={this.handleClick}>Submit</button>
            </form>
        </div>
      );
    }
  }

