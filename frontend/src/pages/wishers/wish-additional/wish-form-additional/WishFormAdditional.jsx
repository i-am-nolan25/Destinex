import React, { useState } from 'react';
import {Link, useNavigate} from "react-router-dom";
import './wish_form_additional.css';

function WishFormAdditional() {

    const [description, setDescription] = useState('');
    const [error, setError] = useState('');

    const navigate = useNavigate();

    const handleDescriptionChange = (event) => {
        const newDesc = event.target.value
        setDescription(newDesc)
        sessionStorage.setItem("description", newDesc)
    }

    const checkRequiredField = () => {
        if (!description) {
            setError('Description must not be blank!');
            return false;
        }
        navigate('/wish-summary');
        return true;
    }

    return (
        <div className="wishFormForAdditional">

            <div className="wishFormForAdditionalTitle">
                <span className="wishFormForAdditionalTitleText">Make a </span>
                <span className="wishFormForAdditionalTitleWish">Wish</span>
                <p>{error}</p>
            </div>

            <div className='wishFormForAdditionalContainer'>
                <div className='step3'>
                    <span className='step3Text'>Step 3 of 4</span>
                </div>

                <div className='wishFormForAdditionalSubTitle'>
                    <span className='wishFormForAdditionalSubTitleText'>Please provide anything you want to include:</span>
                </div>

                <div className='deliveryInstruction'>
                    <label className='deliveryInstructionText'>Delivery Instructions/Notes</label>
                    <input className='deliveryInstructionInput' type='text'
                        onChange={handleDescriptionChange}
                    />
                </div>

                <div className='attachFile'>
                    <label className='attachFileText'>Attach File</label>
                    <input type='file' placeholder='Attach file'/>
                </div>

                <div className='backAndNextButtons'>
                    <Link to='/wish-recipient' className='backButton'>
                        Back
                    </Link>
                    <button className='nextButton' onClick={checkRequiredField}>
                        Next
                    </button>
                </div>
            </div>

        </div>
    )
}

export default WishFormAdditional;