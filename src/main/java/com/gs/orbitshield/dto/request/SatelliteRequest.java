package com.gs.orbitshield.dto.request;

import jakarta.validation.constraints.*;
import com.gs.orbitshield.model.OrbitType;
import com.gs.orbitshield.model.SatelliteStatus;

public class SatelliteRequest {

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Owner company is required")
    private String ownerCompany;

    private String noradId;

    @NotNull(message = "Orbit type is required")
    private OrbitType orbitType;

    private Double altitudeKm;

    private Double inclination;

    @NotNull(message = "Status is required")
    private SatelliteStatus status;

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwnerCompany() {
        return ownerCompany;
    }

    public void setOwnerCompany(String ownerCompany) {
        this.ownerCompany = ownerCompany;
    }

    public String getNoradId() {
        return noradId;
    }

    public void setNoradId(String noradId) {
        this.noradId = noradId;
    }

    public OrbitType getOrbitType() {
        return orbitType;
    }

    public void setOrbitType(OrbitType orbitType) {
        this.orbitType = orbitType;
    }

    public Double getAltitudeKm() {
        return altitudeKm;
    }

    public void setAltitudeKm(Double altitudeKm) {
        this.altitudeKm = altitudeKm;
    }

    public Double getInclination() {
        return inclination;
    }

    public void setInclination(Double inclination) {
        this.inclination = inclination;
    }

    public SatelliteStatus getStatus() {
        return status;
    }

    public void setStatus(SatelliteStatus status) {
        this.status = status;
    }
}

