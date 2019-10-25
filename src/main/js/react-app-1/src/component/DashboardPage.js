import React from 'react';
import PropTypes from 'prop-types';
import AlterInfoGraph from './AlterInfoGraph';
import AlterInfoTables from './AlterInfoTables';

const DashboardPage = ({ actionAddAlterModal, tableDatas, graghOption }) => {
    
    return(
        <div>
            <AlterInfoGraph graghOption={graghOption} />
            <AlterInfoTables 
                actionAddAlterModal={actionAddAlterModal} 
                tableDatas={tableDatas}
             />
        </div>
    );
}
DashboardPage.propTypes = {
    actionAddAlterModal: PropTypes.func.isRequired,
    tableDatas: PropTypes.array.isRequired,
    graghOption: PropTypes.object.isRequired
}

export default DashboardPage 
