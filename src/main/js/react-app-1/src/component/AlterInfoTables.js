import React from 'react';
import PropTypes from 'prop-types';


const AlterInfoTables = ({ actionAddAlterModal, tableDatas }) => {
    return (
        <div>
            <div className="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-0 ">
                <h2>Alter schedule</h2>
                <div className="btn-toolbar mb-2 mb-md-0">
                    <div className="btn-group mr-2">
                    </div>
                    <button type="button" className="btn btn-big btn-primary" data-toggle="modal" data-target="#addAlterSchedule"
                        onClick={() => {actionAddAlterModal(0)}}
                    > 
                        ADD
                    </button>
                </div>
            </div>
            <div className="table-responsive">
                <table className="table table-striped table-sm">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>Start</th>
                        <th>End</th>
                        <th>Shard Name</th>
                        <th>Database Name</th>
                        <th>Table NAME</th>
                        
                        <th>ETA (H)</th>
                        <th>Registration Date</th>
                        <th>Status</th>
                        <th>Updator</th>
                        <th>Edit</th>
                    </tr>
                </thead>
                <tbody>
                    {tableDatas.map(item => {

                            var progressStatus = "Unknown"

                            var createtime = ((item.createTimestamp).replace('T',' ')).slice(0,19)
                            var updatetime = ((item.updateTimestamp).replace('T',' ')).slice(0,19)
                            
                            var timestampSubstract = (new Date(item.updateTimestamp) - new Date(item.createTimestamp)) / 1000 / 60 / 60
                            var etaValue =  Math.round(timestampSubstract * 10 ) /10 

                            /*
                            return <tr key={item.alterTransactionNo}>
                                <td>{item.alterTransactionNo}</td>
                                <td>{item.alterExcuteDate}</td>
                                <td>{item.alterExcuteHour}</td>
                                <td>{item.alterShardName}</td>
                                <td>{item.alterDatabaseName}</td>
                                <td>{item.alterTableName}</td>
                                <td>{item.alterTableSize}</td>
                                <td>{item.alterTableRows}</td>
                                <td>{item.alterETA}</td>
                                <td>{item.alterRegistrationDate}</td>
                                <td>{item.alterStatus}</td>
                                <td>{item.alterRequistor}</td>
                                <td><button type="button" className="btn btn-sm btn-primary" data-toggle="modal" data-target="#addAlterSchedule" onClick={() => {actionAddAlterModal(item.alterTransactionNo)}}>Edit</button></td>
                            </tr>;

                            <th>Table Size (GB)</th>
                            <th>Table Size (Rows)</th>
                            
                            <td>N/A</td>
                            <td>N/A</td>

                            */
                           
                           if(item.progressStatus === 2){
                                progressStatus = "Completed"
                           }else if(item.progressStatus === 1){
                                progressStatus = "In progress"
                                updatetime = "N/A"
                                etaValue = "N/A"
                           }

                           return <tr key={item.orderId}>
                                 <td>{item.orderId+1}</td>
                                 <td>{createtime}</td>
                                 <td>{updatetime}</td>
                                 <td>{item.clusterName}</td>
                                 <td>{item.tableSchema}</td>
                                 <td>{item.tableName}</td>

                                 <td>{etaValue}</td>
                                 <td>{createtime}</td>
                                 <td>{progressStatus}</td>
                                 <td>{item.registerEmail}</td>
                                <td><button type="button" className="btn btn-sm btn-primary" data-toggle="modal" data-target="#addAlterSchedule" onClick={() => {actionAddAlterModal(item.orderId)}}>Edit</button></td>
                            </tr>;
                        }
                    )} 
                </tbody>
                </table>
            </div>
        </div>

    );
}
AlterInfoTables.propTypes = {
    actionAddAlterModal: PropTypes.func.isRequired,
    tableDatas: PropTypes.array.isRequired
}





export default AlterInfoTables