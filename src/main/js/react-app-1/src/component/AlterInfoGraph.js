import React from 'react';
import PropTypes from 'prop-types';
import CanvasJSReact from '../../src/canvasjs.react';

// var CanvasJS = CanvasJSReact.CanvasJS;
var CanvasJSChart = CanvasJSReact.CanvasJSChart;


const AlterInfoGraph = ({ graghOption }) => {
    /*
    const options = {
        title: {
          text: "Basic Column Chart in React"
        },
        data: [{				
                  type: "column",
                  dataPoints: [
                      { label: "Apple",  y: 10  },
                      { label: "Orange", y: 15  },
                      { label: "Banana", y: 25  },
                      { label: "Mango",  y: 30  },
                      { label: "Grape",  y: 28  }
                  ]
         }]
    }
    */
   /* 
   const options = {
        title:{
            text: "Alter - per day"
        },
        axisX: {
            valueFormatString: "MMM-DD",
            interval:1,
            intervalType: "day"
          },
        data: [
            {
                type: "line",
        
                dataPoints: [
                    { x: new Date(2019, 3, 1), y: 450 },
                    { x: new Date(2019, 3, 2), y: 414 },
                    { x: new Date(2019, 3, 3), y: 200 },
                    { x: new Date(2019, 3, 4), y: 100 },
                    { x: new Date(2019, 3, 5), y: 150 },
                    { x: new Date(2019, 3, 6), y: 250 },
                    { x: new Date(2019, 3, 7), y: 320 }
                ]
        }]
    }
    */

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
            <canvas ></canvas>
            <CanvasJSChart className="my-4 w-100" id="myChart" width="900" height="380"
                options = {graghOption}
            /* onRef = {ref => this.chart = ref} */
            />
        </div>
    );
}
AlterInfoGraph.propTypes = {
    graghOption: PropTypes.object.isRequired
}


export default AlterInfoGraph