import React, { useState, useEffect } from 'react';
import SockJS from 'sockjs-client';
import Stomp from 'stompjs';

import './wish-tracking.css';
import {useSearchParams} from "react-router-dom";
import axios from "axios";

function WishTracking() {
    const [searchParams] = useSearchParams();
    const jobId = searchParams.get('jobId');
    const [wishInfo, setWishInfo] = useState(null);

    useEffect(() => {
        const fetchData = async () => {
            const token = localStorage.getItem('jwtToken');

            try {
                const response = await axios.get(`http://localhost:8080/api/jobs/${jobId}`, { // Make sure the URL is correct
                    headers: {
                        'Authorization': `Bearer ${token}`
                    }
                });
                setWishInfo(response.data);
                console.log(response.data)
            } catch (error) {
                console.error('Error fetching wish data:', error);
            }
        };

        if (jobId) {
            fetchData();
        }
    }, [jobId]);

    return (
        <div className="wish-tracking">
            <h2>Wish Tracking</h2>
            <div>
                <div>
                    <div>Tracking for wish ID: {jobId}</div>
                    <div>Wish description: {wishInfo?.description || 'Loading...'}</div>
                    <div>Your granter: {wishInfo?.providerId || 'Loading...'}</div>
                    <div>Your wish status: {wishInfo?.status || 'Loading...'}</div>
                </div>
            </div>
        </div>
    );
}

export default WishTracking;
