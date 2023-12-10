import React, {useState} from 'react'
import {Link, useNavigate} from "react-router-dom"
import { useAuth } from "../../../../networks/hooks/UseAuth";

import './signin_form_wisher.css'

function SigninForm () {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const { login, error } = useAuth();
    const navigate = useNavigate();

    const handleSubmit = async (event) => {
        event.preventDefault();
        const success = await login(username, password);
        if (success) {
            navigate('/');
        }
    };

    return (
        <form onSubmit={handleSubmit}>
            <div className='signinForm'>
                <div className='signinFormTitle'>
                    <span className='signinFormTitleText'>Sign In to </span>
                    <span className='signupFormTitleDestinex'>Destinex</span>
                </div>

                <div className='signinFormInput'>
                    <div className='email'>
                        <label className='emailText'>Email</label>
                        <input
                            type='email'
                            value={username}
                            onChange={(e) => setUsername(e.target.value)}
                            placeholder='Enter your email address'/>
                    </div>

                    <div className='password'>
                        <label className='passwordText'>Password</label>
                            <input
                                type='password'
                                value={password}
                                onChange={(e) => setPassword(e.target.value)}
                                placeholder='Enter your password'/>
                        <div className='forgotPassword'> {/* This should be inside the password div */}
                            <span className='forgotPasswordText'>Forgot Password?</span>
                            <br/>
                            {error && <span>{error}</span>}
                        </div>
                    </div>
                </div>

                <div className='signinButton'>
                    {/*<Link to="/granter-home" className='signinButtonText'>Sign In</Link>*/}
                    <button type="submit" className={'signinButtonText'}>Sign In</button>
                </div>

                <div className='createAccount'>
                    Don’t have an account yet?
                    <Link to="/signup" className='createAccountSignup'>Sign Up</Link>
                </div>

            </div>
        </form>
    )
}

export default SigninForm;