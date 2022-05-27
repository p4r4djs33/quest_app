import logo from './logo.svg';
import './App.css';
import Post from './components/post/Post';
import { BrowserRouter } from 'react-router-dom';
import { Switch } from 'react-router-dom';
import { Route } from 'react-router-dom';
import Home from './components/home/Home';
import User from './components/user/User';
import Navbar from './components/navbar/Navbar';

function App() {
  return (
    <div className="App">
      <BrowserRouter>
      <Navbar/>
        <Switch>
          <Route exact path={"/"} component={Home}></Route>
          <Route exact path={"/users/:userId"} component={User}></Route>
        </Switch>
      </BrowserRouter>
    </div>
  );
}

export default App;
