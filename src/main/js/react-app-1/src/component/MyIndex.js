import React from 'react';
import MySideMenu from './MySideMenu';
import MyContent from './MyContent';

/*  ***************************
    This is the Below top
*************************** */ 
const MyIndex = () => {
    return(
        <div className="container-fluid">
            <div className="row">
                <MySideMenu/>
                <MyContent/>
            </div>
        </div>
    );
}

export default MyIndex 
