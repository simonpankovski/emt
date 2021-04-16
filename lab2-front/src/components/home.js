import React from 'react';
import Book from './books';

export default class Home extends React.Component {
    constructor(props){
        super(props)
        this.state = {
            books: []
        }
    }
    componentDidMount() {
        fetch('http://localhost:9090/books')
        .then(res=> res.json())
        .then((res) => {
         console.log(res)
         this.updateState(res)
        }
        )
        
    }
    updateState(res){
      this.setState({books:res})
    }

    render() {
      return (
        <div>
          <ul>
            {this.state.books.map(item=>{
              return (<li><Book book={item}></Book></li>)
              
            })}
            </ul>
        </div>
      );
    }
  }

