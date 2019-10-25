import React from 'react'

/*  ***************************
    This is the side menu component
    
*************************** */ 
const MySideMenu = () => {
    return(
        <nav className="col-md-2 d-none d-md-block bg-light sidebar">
            <div className="sidebar-sticky" style={{marginTop: '1.5em'}}>
                <ul className="nav flex-column">
                    <li className="nav-item">
                        <a className="nav-link active" href="dashborad.html">
                            <span data-feather="home"></span>
                                Dashboard
                            <span className="sr-only">(current)</span>
                        </a>
                    </li>
                    <li className="nav-item">
                        <a className="nav-link" href="alterhistory.html">
                            <span data-feather="file"></span>
                            Alter history 
                        </a>
                    </li>
                </ul>
            </div>
        </nav>
    );
}

export default MySideMenu