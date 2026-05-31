package com.gs.orbitshield.dto.response;

import java.time.Instant;
import com.gs.orbitshield.model.OrbitType;
import com.gs.orbitshield.model.SatelliteStatus;

public class SatelliteResponse {

    private String id;
    private String name;
    private String ownerCompany;
    private String noradId;
    private OrbitType orbitType;
    private Double altitudeKm;
    private Double inclination;
    private SatelliteStatus status;
    private Instant createdAt;
    private Instant updatedAt;

    public SatelliteResponse() {}

    public SatelliteResponse(String id, String name, String ownerCompany, String noradId,
                           OrbitType orbitType, Double altitudeKm, Double inclination,
                           SatelliteStatus status, Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.name = name;
        this.ownerCompany = ownerCompany;
        this.noradId = noradId;
        this.orbitType = orbitType;
        this.altitudeKm = altitudeKm;
        this.inclination = inclination;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }
}

