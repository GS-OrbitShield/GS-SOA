package com.gs.orbitshield.dto.request;

import jakarta.validation.constraints.*;
import java.time.Instant;
import com.gs.orbitshield.model.Severity;

public class CollisionEventRequest {

    @NotBlank(message = "Object name is required")
    private String objectName;

    @NotNull(message = "Probability is required")
    @DecimalMin(value = "0.0", inclusive = true)
    @DecimalMax(value = "1.0", inclusive = true)
    private Double probability;

    @NotNull(message = "Closest approach is required")
    @FutureOrPresent(message = "Closest approach must be in the future or present")
    private Instant closestApproach;

    @NotNull(message = "Distance is required")
    @Min(value = 0)
    private Double distanceKm;

    private Severity severity;

    // Getters and Setters
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
}

