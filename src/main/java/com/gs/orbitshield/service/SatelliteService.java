package com.gs.orbitshield.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.gs.orbitshield.dto.request.SatelliteRequest;
import com.gs.orbitshield.dto.response.SatelliteResponse;
import com.gs.orbitshield.exception.ConflictException;
import com.gs.orbitshield.exception.ResourceNotFoundException;
import com.gs.orbitshield.model.Satellite;
import com.gs.orbitshield.repository.SatelliteRepository;
import java.time.Instant;

@Service
@Transactional
public class SatelliteService {

    private final SatelliteRepository satelliteRepository;

    public SatelliteService(SatelliteRepository satelliteRepository) {
        this.satelliteRepository = satelliteRepository;
    }

    public SatelliteResponse createSatellite(SatelliteRequest request) {
        // Check if satellite name already exists
        if (satelliteRepository.findByName(request.getName()).isPresent()) {
            throw new ConflictException("Satellite with name '" + request.getName() + "' already exists.");
        }

        Satellite satellite = new Satellite();
        satellite.setName(request.getName());
        satellite.setOwnerCompany(request.getOwnerCompany());
        satellite.setNoradId(request.getNoradId());
        satellite.setOrbitType(request.getOrbitType());
        satellite.setAltitudeKm(request.getAltitudeKm());
        satellite.setInclination(request.getInclination());
        satellite.setStatus(request.getStatus());

        Satellite saved = satelliteRepository.save(satellite);
        return mapToResponse(saved);
    }

    public SatelliteResponse getSatelliteById(String id) {
        Satellite satellite = satelliteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Satellite", "id", id));
        return mapToResponse(satellite);
    }

    public Page<SatelliteResponse> getAllSatellites(Pageable pageable) {
        return satelliteRepository.findAll(pageable)
                .map(this::mapToResponse);
    }

    public SatelliteResponse updateSatellite(String id, SatelliteRequest request) {
        Satellite satellite = satelliteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Satellite", "id", id));

        // Check if new name conflicts with another satellite
        if (!satellite.getName().equals(request.getName()) &&
            satelliteRepository.findByName(request.getName()).isPresent()) {
            throw new ConflictException("Satellite with name '" + request.getName() + "' already exists.");
        }

        satellite.setName(request.getName());
        satellite.setOwnerCompany(request.getOwnerCompany());
        satellite.setNoradId(request.getNoradId());
        satellite.setOrbitType(request.getOrbitType());
        satellite.setAltitudeKm(request.getAltitudeKm());
        satellite.setInclination(request.getInclination());
        satellite.setStatus(request.getStatus());

        Satellite updated = satelliteRepository.save(satellite);
        return mapToResponse(updated);
    }

    public void deleteSatellite(String id) {
        Satellite satellite = satelliteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Satellite", "id", id));
        satelliteRepository.delete(satellite);
    }

    private SatelliteResponse mapToResponse(Satellite satellite) {
        return new SatelliteResponse(
                satellite.getId(),
                satellite.getName(),
                satellite.getOwnerCompany(),
                satellite.getNoradId(),
                satellite.getOrbitType(),
                satellite.getAltitudeKm(),
                satellite.getInclination(),
                satellite.getStatus(),
                satellite.getCreatedAt(),
                satellite.getUpdatedAt()
        );
    }
}

