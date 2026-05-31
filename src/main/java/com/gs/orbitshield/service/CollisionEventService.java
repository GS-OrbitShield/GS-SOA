package com.gs.orbitshield.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.gs.orbitshield.dto.request.CollisionEventRequest;
import com.gs.orbitshield.dto.response.CollisionEventResponse;
import com.gs.orbitshield.exception.ResourceNotFoundException;
import com.gs.orbitshield.model.CollisionEvent;
import com.gs.orbitshield.model.Satellite;
import com.gs.orbitshield.model.Severity;
import com.gs.orbitshield.repository.CollisionEventRepository;
import com.gs.orbitshield.repository.SatelliteRepository;

@Service
@Transactional
public class CollisionEventService {

    private final CollisionEventRepository collisionEventRepository;
    private final SatelliteRepository satelliteRepository;

    public CollisionEventService(CollisionEventRepository collisionEventRepository,
                               SatelliteRepository satelliteRepository) {
        this.collisionEventRepository = collisionEventRepository;
        this.satelliteRepository = satelliteRepository;
    }

    public CollisionEventResponse createCollisionEvent(String satelliteId, CollisionEventRequest request) {
        Satellite satellite = satelliteRepository.findById(satelliteId)
                .orElseThrow(() -> new ResourceNotFoundException("Satellite", "id", satelliteId));

        CollisionEvent event = new CollisionEvent();
        event.setSatellite(satellite);
        event.setObjectName(request.getObjectName());
        event.setProbability(request.getProbability());
        event.setClosestApproach(request.getClosestApproach());
        event.setDistanceKm(request.getDistanceKm());

        // Auto-calculate severity if not provided
        if (request.getSeverity() != null) {
            event.setSeverity(request.getSeverity());
        } else {
            event.setSeverity(calculateSeverity(request.getProbability()));
        }

        CollisionEvent saved = collisionEventRepository.save(event);
        return mapToResponse(saved);
    }

    public CollisionEventResponse getCollisionEventById(String id) {
        CollisionEvent event = collisionEventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("CollisionEvent", "id", id));
        return mapToResponse(event);
    }

    public Page<CollisionEventResponse> getCollisionEventsBySatellite(String satelliteId, Pageable pageable) {
        // Verify satellite exists
        satelliteRepository.findById(satelliteId)
                .orElseThrow(() -> new ResourceNotFoundException("Satellite", "id", satelliteId));

        return collisionEventRepository.findBySatelliteId(satelliteId, pageable)
                .map(this::mapToResponse);
    }

    public CollisionEventResponse resolveCollisionEvent(String id) {
        CollisionEvent event = collisionEventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("CollisionEvent", "id", id));

        event.setResolved(true);
        CollisionEvent updated = collisionEventRepository.save(event);
        return mapToResponse(updated);
    }

    public void deleteCollisionEvent(String id) {
        CollisionEvent event = collisionEventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("CollisionEvent", "id", id));
        collisionEventRepository.delete(event);
    }

    private Severity calculateSeverity(Double probability) {
        if (probability >= 0.1) {
            return Severity.CRITICAL;
        } else if (probability >= 0.01) {
            return Severity.HIGH;
        } else if (probability >= 0.001) {
            return Severity.MEDIUM;
        } else {
            return Severity.LOW;
        }
    }

    private CollisionEventResponse mapToResponse(CollisionEvent event) {
        return new CollisionEventResponse(
                event.getId(),
                event.getSatellite().getId(),
                event.getSatellite().getName(),
                event.getObjectName(),
                event.getProbability(),
                event.getClosestApproach(),
                event.getDistanceKm(),
                event.getSeverity(),
                event.getResolved(),
                event.getCreatedAt()
        );
    }
}

