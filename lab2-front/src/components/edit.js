import React from 'react';
export default class Edit extends React.Component {
    constructor(props){
        super(props)
        this.state = {   
            
                name:"",
                availableCopies:0,
                category:"",
                id:0,
                author:{}
            
        }
       this.save = this.save.bind(this)
       this.handleChange = this.handleChange.bind(this)
    }
    componentDidMount(){
        let id = window.location.href.split("/")[4]
        let token = document.cookie.split("=")[1]
        
        fetch("http://localhost:9090/books/edit/"+id,{headers:{"Authorization":token}}).then(res=>res.json()).then(res=>{console.log(res)
         this.setState({name:res.name,availableCopies:res.availableCopies,category:res.category,id:res.id,author:res.author})} )
    }
    save(e){
        e.preventDefault()
        let token = document.cookie.split("=")[1]
        fetch("http://localhost:9090/books/edit",{method:"POST",body:JSON.stringify(this.state),headers:{"Authorization":token,'Content-Type': 'application/json'}}).then(res=>window.location.href="http://localhost:3000/")
    }
    handleChange(evt) {
       
        const value = evt.target.value;
        this.setState({
            ...this.state,
            [evt.target.name]: value
          
        });
      }
    render() {
      return (
        <div>
            <form>
                Title: <input type="text" name="name" defaultValue={this.state.name} onChange={this.handleChange}/>
                Copies: <input type="number" name="availableCopies" value={this.state.availableCopies} onChange={this.handleChange}/>
                Category: <input type="text" name="category" value={this.state.category}onChange={this.handleChange}/>
                <input type="hidden" name="id" value={this.state.id}/>
                <input type="hidden" name="author" value={this.state.author}/>
                <button onClick={this.save}>save</button>
            </form>
           
           
            
        </div>
      );
    }
  }



