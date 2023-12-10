import React from 'react';
import './requirement.css';
import requirementPic from '../../../../assets/img/requirement.png';

function Requirement () {

    return (
        <div className='requirement'>

            <div className='requirementTitle'>
                <span className='requirementTitleText'>Set Up to Become a </span>
                <span className='requirementTitleGranter'>Granter</span>
            </div>

            <div className='requirementSubTitle'>
                <span className='requirementSubTitleText'>Requirements</span>
            </div>

            <div className='requirementContent'>
                <img className={"requirementPic"} src={requirementPic} alt={"requirement.png"} />
            </div>

        </div>
    );
}

export default Requirement;