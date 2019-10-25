import React from 'react';
import PropTypes from 'prop-types';
import AlterValidationInput from './AlterValidationInput';


const AlterValidationPage = ({ renderAlterExecute, ansibleData, dryrunData, actionValueChange }) => {
    
    return(
        <div>
            <AlterValidationInput
                renderAlterExecute={renderAlterExecute}
                ansibleData={ansibleData}
                dryrunData={dryrunData}
                actionValueChange={actionValueChange}
            />
        </div>
    );
}
AlterValidationPage.propTypes = {
    renderAlterExecute: PropTypes.func.isRequired,
    ansibleData: PropTypes.array.isRequired,
    dryrunData: PropTypes.array.isRequired,
    actionValueChange: PropTypes.func.isRequired,
}

export default AlterValidationPage
