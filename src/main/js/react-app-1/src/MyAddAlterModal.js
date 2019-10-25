import React from 'react';

const MyAddAlterModal = () =>
    <div>
        <form>
            <div className="form-group">
                <label htmlFor="inputShardName">Shard Name</label>
                <select id="inputShardName" className="form-control">
                    <option>Choose...</option>
                    <option>ORDER-EU</option>
                    <option>ORDER_AU</option>
                </select>
            </div>
            <div className="form-group">
                <label htmlFor="inputDatabaseName">Database Name</label>
                <input type="text" className="form-control" id="inputDatabaseName" placeholder="ex) taxify_company"/>
            </div>
            <div className="form-group">
                <label htmlFor="inputTableName">Table Name</label>
                <input type="text" className="form-control" id="inputTableName" placeholder="ex) order"/>
                </div>
            <div className="form-group">
                <label htmlFor="inputAlterSyntax">Alter Syntax</label>
                <textarea className="form-control" id="inputAlterSyntax" rows="10" placeholder="ex) ADD KEY `idx_user_id` (`user_id`)" ></textarea>
            </div>
            <div className="form-row">
                <div className="form-group col-md-6">
                <label htmlFor="inputAlterDate">Alter Date</label>
                <input type="text" className="form-control" id="inputAlterDate" placeholder="ex) 15-08-2019"/>
                </div>
                <div className="form-group col-md-6">
                <label htmlFor="inputAlterTime">Alter Time</label>
                <input type="text" className="form-control" id="inputAlterTime" placeholder="ex) 23:39"/>
                </div>
            </div>
            <div className="form-group">
                <div className="form-check">
                <input className="form-check-input" type="checkbox" id="gridCheck"/>
                </div>
            </div>
        </form>
    </div>

export default MyAddAlterModal