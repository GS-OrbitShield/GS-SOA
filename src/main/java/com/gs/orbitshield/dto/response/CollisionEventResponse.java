package com.gs.orbitshield.dto.response;

import java.time.Instant;
import com.gs.orbitshield.model.Severity;

public class CollisionEventResponse {

    private String id;
    private String satelliteId;
    private String satelliteName;
    private String objectName;
    private Double probability;
    private Instant closestApproach;
    private Double distanceKm;
    private Severity severity;
    private Boolean resolved;
    private Instant createdAt;

    public CollisionEventResponse() {}

    public CollisionEventResponse(String id, String satelliteId, String satelliteName,
                                 String objectName, Double probability, Instant closestApproach,
                                 Double distanceKm, Severity severity, Boolean resolved, Instant createdAt) {
        this.id = id;
        this.satelliteId = satelliteId;
        this.satelliteName = satelliteName;
        this.objectName = objectName;
        this.probability = probability;
        this.closestApproach = closestApproach;
        this.distanceKm = distanceKm;
        this.severity = severity;
        this.resolved = resolved;
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

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public Double getProbability() {
        return probability;
    }

    public void setProbability(Double probability) {
        this.probability = probability;
    }

    public Instant getClosestApproach() {
        return closestApproach;
    }

    public void setClosestApproach(Instant closestApproach) {
        this.closestApproach = closestApproach;
    }

    public Double getDistanceKm() {
        return distanceKm;
    }

    public void setDistanceKm(Double distanceKm) {
        this.distanceKm = distanceKm;
    }

    public Severity getSeverity() {
        return severity;
    }

    public void setSeverity(Severity severity) {
        this.severity = severity;
    }

    public Boolean getResolved() {
        return resolved;
    }

    public void setResolved(Boolean resolved) {
        this.resolved = resolved;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}

