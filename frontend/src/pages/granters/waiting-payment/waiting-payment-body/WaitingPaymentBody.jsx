import React, { useState } from 'react';
import './WaitingPaymentBody.css';
import Hourglass_icon from "../../../../assets/img/Hourglass.png"
import {Link} from "react-router-dom";

function WaitingPaymentBody () {

    return (
        <div className='waitingPaymentBody'>
            <div className={'waitingPaymentIcon'}>
                <img className={'hourglassIcon'} src={Hourglass_icon} alt={"Hourglass Icon"}/>
            </div>
            <div className={'waitingPaymentTitle'}>
                <span className={'waitingPaymentTitleText'}>Waiting for a Payment</span>
            </div>
            <div className={'waitingPaymentTextBox'}>
                <span className={'waitingPaymentText'}>Payment takes two business days to process.</span>
            </div>
            <div className={'waitingPaymentTextBox'}>
                <span className={'waitingPaymentText'}>Page while be automatically redirected to the main page or click below button.</span>
            </div>
            <div className={"homepageBtn"}>
                <Link to={"/"} className={"waitingToHome"}>Go to Home Page</Link>
            </div>
        </div>
    )
}

export default WaitingPaymentBody;