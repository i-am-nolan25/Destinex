package com.team08.csci205_final_project.config;

/**
 * This class represents the configuration for application logic
 * used in the csci205_final_project. It includes settings for
 * response wait time and geographical radius.
 */
public class ApplicationLogicConfig {

    /**
     * Wait time in seconds for a response from provider
     */
    private double waitTimeForResponse = 60;

    /**
     * Radius in kilometers for geographical calculations in the application
     */
    private double radiusInKm = 30;

    public ApplicationLogicConfig(double WAIT_TIME, double radiusInKm) {
        this.waitTimeForResponse = WAIT_TIME;
        this.radiusInKm = radiusInKm;
    }

    public double getWaitTime() {
        return waitTimeForResponse;
    }

    public void setWaitTime(double WAIT_TIME) {
        this.waitTimeForResponse = WAIT_TIME;
    }

    public double getRadiusInKm() {
        return radiusInKm;
    }

    public void setRadiusInKm(double radiusInKm) {
        this.radiusInKm = radiusInKm;
    }
}