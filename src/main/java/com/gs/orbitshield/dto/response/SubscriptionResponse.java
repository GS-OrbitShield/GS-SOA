package com.gs.orbitshield.dto.response;

import java.time.Instant;

public class SubscriptionResponse {

    private String id;
    private String satelliteId;
    private String satelliteName;
    private Instant createdAt;

    public SubscriptionResponse() {}

    public SubscriptionResponse(String id, String satelliteId, String satelliteName, Instant createdAt) {
        this.id = id;
        this.satelliteId = satelliteId;
        this.satelliteName = satelliteName;
        this.createdAt = createdAt;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSatelliteId() {
        return satelliteId;
    }

    public void setSatelliteId(String satelliteId) {
        this.satelliteId = satelliteId;
    }

    public String getSatelliteName() {
        return satelliteName;
    }

    public void setSatelliteName(String satelliteName) {
        this.satelliteName = satelliteName;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}

