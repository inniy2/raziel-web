import React, { Component} from 'react';



class MyLogin extends Component {

    constructor(props){
        super(props);
        this.state = {
            show: false
        };
        this.handleChange = this.handleChange.bind(this);
        this.setParentProps = this.setParentProps.bind(this);
    }
    
    handleChange(event){
        const {name, value} = event.target;

        if(name === "loginModal" || name === "closeButton"){
            this.setState({ show: false });
        }
        
    }
    
    componentWillUpdate(){
        //this.setState({show: this.props.show })
    }
   
    setParentProps(){
        this.setState({show: this.props.show})
    }
    

    render(){
        
        return (
            <div>N/A</div>
        );
    }
}

export default MyLogin