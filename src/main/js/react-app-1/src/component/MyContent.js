import React from 'react';
import MyCanvas from './MyCanvas';
import MyTable from './MyTable';
// import { Button } from 'react-bootstrap';

const MyContent = () => {
    return(
        <main  role="main" className="col-md-9 ml-sm-auto col-lg-10 px-4" style={{marginTop: '1.5em'}}>
            <MyCanvas/>
            <MyTable/>
        </main>
    );
}

export default MyContent 
