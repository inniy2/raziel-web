import React from 'react';
import PropTypes from 'prop-types';


const MyTable = ({ actionAddAlterModal, tableDatas }) => {
    return (
        <div>
            <div className="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-0 ">
                <h2>Alter schedule</h2>
                <div className="btn-toolbar mb-2 mb-md-0">
                    <div className="btn-group mr-2">
                    </div>
                    <button type="button" className="btn btn-big btn-primary" data-toggle="modal" data-target="#addAlterSchedule"
                        onClick={actionAddAlterModal}
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
                        <th>Date</th>
                        <th>Time</th>
                        <th>Shard Name</th>
                        <th>Database Name</th>
                        <th>Table NAME</th>
                        <th>Table Size (GB)</th>
                        <th>Table Size (Rows)</th>
                        <th>ETA (H)</th>
                        <th>Registration Date</th>
                        <th>Status</th>
                        <th>Updator</th>
                        <th>Edit</th>
                    </tr>
                </thead>
                <tbody>
                    {tableDatas.map(item => {
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
                                <td><button type="button" className="btn btn-sm btn-primary" data-toggle="modal" data-target="#addAlterSchedule">Edit</button></td>
                            </tr>;
                        }
                    )} 
                </tbody>
                </table>
            </div>
        </div>

    );
}
MyTable.propTypes = {
    actionAddAlterModal: PropTypes.func.isRequired,
    tableDatas: PropTypes.array.isRequired
}





export default MyTable