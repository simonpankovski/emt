import logo from './logo.svg';
import './App.css';
import Home from './components/home';
import Login from './components/login';
import Header from './components/header';
import Edit from './components/edit';
import { BrowserRouter, Switch, Route } from "react-router-dom";
function App() {
  return (
    <div className="App">
      <BrowserRouter>
      <Header/>
        <Switch>
        <Route path="/" exact component={Home}/>
        <Route path="/login" exact component={Login}/>
        <Route path="/edit"  component={Edit}/>
        </Switch>
      </BrowserRouter>
    </div>
  );
}

export default App;
