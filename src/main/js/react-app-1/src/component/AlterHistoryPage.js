import React from 'react';
import PropTypes from 'prop-types';
import AlterHistoryInfoTables from './AlterHistoryInfoTables';

const AlterHistoryPage = ({ tableDatas }) => {
    
    return(
        <div>
            <AlterHistoryInfoTables tableDatas={tableDatas}
             />
        </div>
    );
}
AlterHistoryPage.propTypes = {    
    tableDatas: PropTypes.array.isRequired
}

export default AlterHistoryPage 
