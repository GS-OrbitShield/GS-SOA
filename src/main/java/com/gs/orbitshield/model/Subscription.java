package com.gs.orbitshield.model;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(
    name = "subscription",
    uniqueConstraints = @UniqueConstraint(columnNames = {"satellite_id", "api_key_id"})
)
public class Subscription {

    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name = "satellite_id", nullable = false)
    private Satellite satellite;

    @Column(name = "api_key_id", nullable = false)
    private String apiKeyId;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    public Subscription() {
        this.id = UUID.randomUUID().toString();
        this.createdAt = Instant.now();
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Satellite getSatellite() {
        return satellite;
    }

    public void setSatellite(Satellite satellite) {
        this.satellite = satellite;
    }

    public String getApiKeyId() {
        return apiKeyId;
    }

    public void setApiKeyId(String apiKeyId) {
        this.apiKeyId = apiKeyId;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}

