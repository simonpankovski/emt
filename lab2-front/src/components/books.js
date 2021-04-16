import React from 'react';
export default class Book extends React.Component {
    constructor(props){
        super(props)
        this.state = {   

        }
        this.taken = this.taken.bind(this)
        this.remove = this.remove.bind(this)
        this.edit = this.edit.bind(this)
    }
    taken(){
     
      let token = document.cookie.split("=")[1]
      let id = this.props.book.id
      console.log(token)
      fetch("http://localhost:9090/books/decrement?id="+id,{method:"POST",headers:{
        "Authorization": token
      }}).then(res=>window.location.reload())
    }
    remove(){
      let token = document.cookie.split("=")[1]
      let id = this.props.book.id
      
      fetch("http://localhost:9090/books/delete?id="+id,{method:"POST",headers:{
        "Authorization": token
      }}).then(res=>window.location.reload())
    }
    edit(){
      window.location.href = "http://localhost:3000/edit/"+this.props.book.id
    }
    render() {
      return (
        <div>
            <p>Title: {this.props.book.name}</p>
            <p>Author: {this.props.book.author.name + " " +this.props.book.author.surname } </p>
            <p>Available Copies: {this.props.book.availableCopies > 0 ? this.props.book.availableCopies : 0}</p>
           
            <button onClick={this.edit}> Edit</button>
            <button onClick={this.remove}> Delete</button>
            <button onClick={this.taken}> Taken</button>
        </div>
      );
    }
  }



