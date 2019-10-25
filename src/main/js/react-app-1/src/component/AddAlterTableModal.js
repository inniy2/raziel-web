import React from 'react';
import PropTypes from 'prop-types';
import Button from 'react-bootstrap/Button';
import Modal from 'react-bootstrap/Modal';


const AddAlterTableModal = ({ 
    isShow, 
    actionAddAlterModal, 
    actionValueChange,
    modalAlterTransactionNo,
    modalAlterShardName,
    modalAlterDatabaseName,
    modalAlterTableName,
    modalAlterSyntax,
    modalAlterDate,
    modalAlterHour
    }) =>
    <Modal show={isShow} onHide={()=>{actionAddAlterModal(modalAlterTransactionNo)}}>
        <Modal.Header closeButton>
            <Modal.Title>ADD ALTER</Modal.Title>
        </Modal.Header>
        <Modal.Body>
            <div>
                <form>
                    <div className="form-group">
                        <label htmlFor="inputShardName">Shard Name</label>
                        <select id="inputShardName" className="form-control"  
                            name="modalAlterShardName" 
                            value={modalAlterShardName} 
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
                            value={modalAlterDatabaseName}
                            onChange={actionValueChange}
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="inputTableName">Table Name</label>
                        <input type="text" className="form-control" id="inputTableName" placeholder="ex) order"
                            name="modalAlterTableName"
                            value={modalAlterTableName}
                            onChange={actionValueChange}
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="inputAlterSyntax">Alter Syntax</label>
                        <textarea className="form-control" id="inputAlterSyntax" rows="10" placeholder="ex) ADD KEY `idx_user_id` (`user_id`)" 
                            name="modalAlterSyntax"
                            value={modalAlterSyntax}
                            onChange={actionValueChange}
                        ></textarea>
                    </div>
                    <div className="form-row">
                        <div className="form-group col-md-6">
                        <label htmlFor="inputAlterDate">Alter Date</label>
                        <input type="text" className="form-control" id="inputAlterDate" placeholder="ex) 15-08-2019"
                            name="modalAlterDate"
                            value={modalAlterDate}
                            onChange={actionValueChange}
                        />
                        </div>
                        <div className="form-group col-md-6">
                            <label htmlFor="inputAlterTime">Alter Time</label>
                            <input type="text" className="form-control" id="inputAlterTime" placeholder="ex) 23:39"
                                name="modalAlterHour"
                                value={modalAlterHour}
                                onChange={actionValueChange}
                            />
                        </div>
                    </div>
                    <input type="hidden" 
                        name="modalAlterTransactionNo"
                        value={modalAlterTransactionNo}
                        onChange={actionValueChange}
                    />
                </form>
            </div>
        </Modal.Body>
        <Modal.Footer>
            <Button name="closeButton" variant="secondary" onClick={()=>{actionAddAlterModal(modalAlterTransactionNo)}}>
            Close
            </Button>
            <Button name="addAlterTableSubmitButton" type='submit' variant="primary" className="btn btn-primary" 
                onClick={actionValueChange}
            >
                ADD
            </Button>
        </Modal.Footer>
    </Modal>;
AddAlterTableModal.propTypes = {
    isShow: PropTypes.bool.isRequired,
    actionAddAlterModal: PropTypes.func.isRequired,
    actionValueChange: PropTypes.func.isRequired,
    modalAlterTransactionNo: PropTypes.number.isRequired,
    modalAlterShardName: PropTypes.string.isRequired,
    modalAlterDatabaseName: PropTypes.string.isRequired,
    modalAlterTableName: PropTypes.string.isRequired,
    modalAlterSyntax: PropTypes.string.isRequired,
    modalAlterDate: PropTypes.string.isRequired,
    modalAlterHour: PropTypes.string.isRequired,
}



export default AddAlterTableModal