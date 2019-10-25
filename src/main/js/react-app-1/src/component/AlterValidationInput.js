import React from 'react';
import PropTypes from 'prop-types';
import Button from 'react-bootstrap/Button';


const AlterValidationInput = ({ 
    renderAlterExecute,
    ansibleData,
    dryrunData,
    actionValueChange
    }) => {
    return (
        <div>


            {/* Heading Alter Validation & button */}

            <div className="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-0 ">
                <h2>Alter Validation</h2>
                <div className="btn-toolbar mb-2 mb-md-0">
                    <div className="btn-group mr-2">
                    </div>
                    
                </div>
            </div>


            {/* Table contents */}

            <div className="card card-outline-secondary my-4">
                <div className="card-header">
                    Alter Dry Run
                </div>
                <div className="card-body">
                    <form>
                        <div className="form-group">
                            <label htmlFor="inputShardName">Shard Name</label>
                            <select id="inputShardName" className="form-control"  
                                name="modalAlterShardName"  
                                onChange={actionValueChange}
                            >
                                    <option value="default">Choose...</option>
                                    <option value="test-cluster">TEST-CLUSTER</option>
                                    <option value="order-eu">ORDER-EU</option>
                                    <option value="order-na">ORDER-NA</option>
                                    <option value="order-au">ORDER-AU</option>
                                    <option value="order-gb">ORDER-GB</option>
                                    <option value="order-ru">ORDER-RU</option>
                                    <option value="campaign">CAPMPAIGN</option>
                                    <option value="central">CENTRAL</option>
                                    <option value="delivery">DELIVERY</option>
                                    <option value="fraud">FRAUD</option>
                                    <option value="invoicing">INVOICING</option>
                                    <option value="payment">PAYMENT</option>
                            </select>
                        </div>
                        <div className="form-group">
                            <label htmlFor="inputDatabaseName">Database Name</label>
                            <input type="text"  className="form-control" id="inputDatabaseName" placeholder="ex) taxify_company"
                                name="modalAlterDatabaseName"
                                onChange={actionValueChange}
                            />
                        </div>
                        <div className="form-group">
                            <label htmlFor="inputTableName">Table Name</label>
                            <input type="text" className="form-control" id="inputTableName" placeholder="ex) order"
                                name="modalAlterTableName"
                                onChange={actionValueChange}
                            />
                        </div>
                        <div className="form-group">
                            <label htmlFor="inputAlterSyntax">Alter Syntax</label>
                            <textarea className="form-control" id="inputAlterSyntax" rows="10" placeholder="ex) ADD KEY `idx_user_id` (`user_id`)" 
                                name="modalAlterSyntax"
                                onChange={actionValueChange}
                            ></textarea>
                        </div>
                        <div className="form-row">
                            <div className="form-group col-md-6">
                                <label htmlFor="inputAlterDate">Alter Date</label>
                                <input type="text" className="form-control" id="inputAlterDate" placeholder="ex) 15-08-2019"
                                    name="modalAlterDate"
                                    onChange={actionValueChange}
                                />
                            </div>
                            <div className="form-group col-md-6">
                                <label htmlFor="inputAlterTime">Alter Time</label>
                                <input type="text" className="form-control" id="inputAlterTime" placeholder="ex) 23:39"
                                    name="modalAlterHour"
                                    onChange={actionValueChange}
                                    />
                            </div>
                        </div>
                        
                        <div className="form-row">
                            <div className="form-group col-md-6">
                                <Button name="DryRunSubmitButton" type='button' variant="primary" className="btn btn-primary" 
                                    onClick={actionValueChange}
                                >
                                    Dry run
                                </Button>
                            </div>
                        </div>
                        
                        

                    </form>   
                    


                </div>
            </div>


            {/* Sansible result */}

            <div className="card card-outline-secondary my-4">
                <div className="card-header">
                    Ansible Result
                </div>
                <div className="card-body">
                
                    {ansibleData.map(item => {
                        return <div>
                                {item}
                                </div>
                    })}

                </div>
            </div>


            {/* Sansible result */}

            <div className="card card-outline-secondary my-4">
                <div className="card-header">
                    Dry Run Result
                </div>
                <div className="card-body">

                    {dryrunData.map(item => {
                        return <div>
                                {item}
                                </div>
                    })}
                   
                </div>

                <div className="card-body">

                    {renderAlterExecute(actionValueChange)}
                   
                </div>
                
            </div>





        </div>

    );
}
AlterValidationInput.propTypes = {
    renderAlterExecute: PropTypes.func.isRequired,
    ansibleData: PropTypes.array.isRequired,
    dryrunData: PropTypes.array.isRequired,
    actionValueChange: PropTypes.func.isRequired
}





export default AlterValidationInput