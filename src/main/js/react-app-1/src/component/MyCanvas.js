import React from 'react';

const MyCanvas = () => {
    return(
        <div>
            <div className="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                <h1 className="h2">Dashboard</h1>
                <div className="btn-toolbar mb-2 mb-md-0">
                    <div className="btn-group mr-2">
                    </div>
                    <button type="button" className="btn btn-sm btn-outline-secondary dropdown-toggle">
                        <span data-feather="calendar"></span>
                        This week 
                    </button>
                </div>
            </div>
            <canvas className="my-4 w-100" id="myChart" width="900" height="380"></canvas>
        </div>
    );
}


export default MyCanvas