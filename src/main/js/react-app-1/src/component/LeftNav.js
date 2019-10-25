import React from 'react'
import PropTypes from 'prop-types';


const LeftNav = ({ children }) => {
    return(
        <nav className="col-md-2 d-none d-md-block bg-light sidebar">
            <div className="sidebar-sticky" style={{marginTop: '1.5em'}}>
                <ul className="nav flex-column">
                    {children}
                </ul>
            </div>
        </nav>
    );
}
LeftNav.propTypes = {
    children: PropTypes.node.isRequired
}

export default LeftNav