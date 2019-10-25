import React, { Component } from 'react';
import Button from 'react-bootstrap/Button';
import Modal from 'react-bootstrap/Modal';
// import MyLogin from './MyLogin';

/*  ***************************
    This is the top black
*************************** */ 
class MyNavi extends Component {
    // <NavLink className="nav-link active" to='/login'>Log In</NavLink>
    constructor(props){
        super(props);
        this.state = {
            loginButton: "LOG IN",
            show: false,
            userEmail: '',
            userPassword: '',
        };
        this.handleModalOpenClose = this.handleModalOpenClose.bind(this);
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    };


    handleModalOpenClose(){
        this.setState(prevState => ({
            show: !prevState.show
        }))
    }

    handleChange(event){
        const { name, value } = event.target;
        this.setState({ [name] : value })
    }

    handleSubmit(event){
        console.log("call handleSubmit")
        
    }

    componentDidMount(){
        console.log("call componentDidMount")
    }
   

    render(){
        return(
            <div>
                <nav className="navbar navbar-dark fixed-top bg-dark flex-md-nowrap p-0 shadow">
                    <a className="navbar-brand col-sm-3 col-md-2 mr-0" href="/" >Bolt</a> 
                    <ul className="navbar-nav px-3">
                        <li className="nav-item text-nowrap">
                            <button name="loginButton" className="btn btn-big btn-primary" onClick={this.handleModalOpenClose}>
                                {this.state.loginButton}
                            </button>
                        </li>
                    </ul>
                </nav>
                <Modal show={this.state.show} onHide={this.handleModalOpenClose}>
                    <Modal.Header closeButton>
                        <Modal.Title>Log In</Modal.Title>
                    </Modal.Header>
                    <Modal.Body>
                        <div className="container">
                            <div className="starter-template">
                                <form>
                                    <div className="form-group">
                                        <label htmlFor="exampleInputEmail1">Email address</label>
                                        <input type="email" name="userEmail" className="form-control"  id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter email"
                                            value={this.state.userEmail}
                                            onChange={this.handleChange}
                                        />
                                        
                                        <small id="emailHelp" className="form-text text-muted">We'll never share your email with anyone else.</small>
                                    </div>
                                    <div className="form-group">
                                        <label htmlFor="exampleInputPassword1">Password</label>
                                        <input type="password" name="userPassword" className="form-control" id="exampleInputPassword1" placeholder="Password"
                                            value={this.state.userPassword}
                                            onChange={this.handleChange}
                                        />
                                    </div>
                                    
                                </form>
                            </div>
                        </div>
                    </Modal.Body>
                    <Modal.Footer>
                        <Button name="closeButton" variant="secondary" onClick={this.handleModalOpenClose}>
                        Close
                        </Button>
                        <Button name="submitButton" type='submit' variant="primary" className="btn btn-primary" onClick={this.handleSubmit}>
                            Login
                         </Button>
                    </Modal.Footer>
                </Modal>
            </div>
        );
    };
    
}

export default MyNavi 
