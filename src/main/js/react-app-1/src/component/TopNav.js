import React from 'react';
import PropTypes from 'prop-types';


const TopNav = ({ children }) => {
    
    return(
        <div>
            <nav className="navbar navbar-dark fixed-top bg-dark flex-md-nowrap p-0 shadow">
                <a className="navbar-brand col-sm-3 col-md-2 mr-0" href="#">Bolt</a> 
                <ul className="navbar-nav px-3">
                    <li className="nav-item text-nowrap">
                        {children}
                    </li>
                </ul>
            </nav>
            
        </div>
    );
}
TopNav.propTypes = {
    children: PropTypes.node.isRequired
}


export default TopNav 