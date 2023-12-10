import * as registerService from "../api/RegisterService"
import {useState} from "react";

export const register = async(firstName, lastName, email, password, role = 1) => {
    // const [error, setError] = useState(null);
    // const [isRegistered, setIsRegistered] = useState(false);
    try {
            const response = await registerService.register({firstName, lastName, email, password, role})
        if (response.data) {
            // setIsRegistered(true);
            return true;
        } else {
            // setError('Register failed: Please try again');
            return false;
        }
    } catch(err) {
        // setError(err.message);
        console.log(err.message)
        return false;
    }
}