import React, {useState} from 'react'
import {Link, useNavigate} from "react-router-dom";
import './signup_form_wisher.css'
import {register} from "../../../../networks/hooks/UseRegister";


function SignupForm () {
    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const navigate = useNavigate();

    const handleSubmit = async (event) => {
        event.preventDefault();
        const success = await register(firstName, lastName, email, password)
        if (success) {
            navigate('/signin')
        }
    };

    return (
        <form onSubmit={handleSubmit}>
            <div className='signupForm'>

                <div className='signupFormTitle'>
                    <span className='signupFormTitleText'>Sign Up to </span>
                    <span className='signupFormTitleDestinex'>Destinex</span>
                </div>

                <div className='signupFormInput'>
                    <div className='nameContainer'>
                        <div className='firstName'>
                            <label className='firstNameText'>First Name</label>
                            <input
                                type='firstName'
                                value={firstName}
                                onChange={(e) => setFirstName(e.target.value)}
                                placeholder='Enter your first name'/>
                        </div>

                        <div className='lastName'>
                            <label className='lastNameText'>Last Name</label>
                            <input
                                type='lastName'
                                value={lastName}
                                onChange={(e) => setLastName(e.target.value)}
                                placeholder='Enter your last name'/>
                        </div>
                    </div>

                    <div className='email'>
                        <label className='emailText'>Email</label>
                        <input
                            type='email'
                            value={email}
                            onChange={(e) => setEmail(e.target.value)}
                            placeholder='Enter your email address'/>
                    </div>

                    <div className='password'>
                        <label className='passwordText'>Password</label>
                        <input
                            type='password'
                            value={password}
                            onChange={(e) => setPassword(e.target.value)}
                            placeholder='Enter your password'/>
                    </div>

                    <div className='agreement'>
                        <input type='checkbox'/>
                        <span className='agreementText'>
                            I agree with Destinex’s <a href="link-to-terms" className="linkStyle">Terms of Services</a>, <a href="link-to-privacy" className="linkStyle">Privacy Policy</a>, and default <a href="link-to-notifications" className="linkStyle">Notification Settings</a>.
                        </span>
                    </div>

                    <button type={'submit'} className='signupButtonText'>
                        Sign Up
                    </button>

                    <div className='alreadyHaveAccount'>
                        <span className='alreadyHaveAccountText'>Already have an account?</span>
                        <Link to="/signin" className='alreadyHaveAccountLogin'>Sign In</Link>
                    </div>
                </div>

                {/*<div className='email'>*/}
                {/*    <label className='emailText'>Email</label>*/}
                {/*    <input type='email' placeholder='Enter your email address'/>*/}
                {/*</div>*/}

                {/*<div className='password'>*/}
                {/*    <label className='passwordText'>Password</label>*/}
                {/*    <input type='password' placeholder='Enter your password'/>*/}
                {/*</div>*/}

                {/*<div className='agreement'>*/}
                {/*    <input type='checkbox'/>*/}
                {/*    <span className='agreementText'>*/}
                {/*        I agree with Destinex’s <a href="link-to-terms" className="linkStyle">Terms of Services</a>, <a href="link-to-privacy" className="linkStyle">Privacy Policy</a>, and default <a href="link-to-notifications" className="linkStyle">Notification Settings</a>.*/}
                {/*    </span>*/}
                {/*</div>*/}

                {/*<Link to="/signin" className='signupButton'>*/}
                {/*    Sign Up*/}
                {/*</Link>*/}

                {/*<div className='alreadyHaveAccount'>*/}
                {/*    <span className='alreadyHaveAccountText'>Already have an account?</span>*/}
                {/*    <Link to="/signin" className='alreadyHaveAccountLogin'>Sign In</Link>*/}
                {/*</div>*/}
            </div>
        </form>
    )
}

export default SignupForm;