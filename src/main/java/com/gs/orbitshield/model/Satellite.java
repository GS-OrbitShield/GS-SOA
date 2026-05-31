package com.gs.orbitshield.model;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "satellite", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class Satellite {

    @Id
    private String id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(name = "owner_company", nullable = false)
    private String ownerCompany;

    @Column(name = "norad_id")
    private String noradId;

    @Enumerated(EnumType.STRING)
    @Column(name = "orbit_type", nullable = false)
    private OrbitType orbitType;

    @Column(name = "altitude_km")
    private Double altitudeKm;

    @Column(name = "inclination")
    private Double inclination;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SatelliteStatus status;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;

    @OneToMany(mappedBy = "satellite", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CollisionEvent> collisionEvents = new HashSet<>();

    @OneToMany(mappedBy = "satellite", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Subscription> subscriptions = new HashSet<>();

    public Satellite() {
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

    public Set<CollisionEvent> getCollisionEvents() {
        return collisionEvents;
    }

    public void setCollisionEvents(Set<CollisionEvent> collisionEvents) {
        this.collisionEvents = collisionEvents;
    }

    public Set<Subscription> getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(Set<Subscription> subscriptions) {
        this.subscriptions = subscriptions;
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = Instant.now();
    }
}

