package com.gs.orbitshield.dto.request;

import jakarta.validation.constraints.NotBlank;

public class SubscriptionRequest {

    @NotBlank(message = "Satellite ID is required")
    private String satelliteId;

    // Getters and Setters
    public String getSatelliteId() {
        return satelliteId;
    }

    public void setSatelliteId(String satelliteId) {
        this.satelliteId = satelliteId;
    }
}

