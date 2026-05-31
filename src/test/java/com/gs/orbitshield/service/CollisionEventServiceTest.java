package com.gs.orbitshield.service;

import com.gs.orbitshield.dto.request.CollisionEventRequest;
import com.gs.orbitshield.dto.response.CollisionEventResponse;
import com.gs.orbitshield.exception.ResourceNotFoundException;
import com.gs.orbitshield.model.CollisionEvent;
import com.gs.orbitshield.model.Satellite;
import com.gs.orbitshield.model.Severity;
import com.gs.orbitshield.repository.CollisionEventRepository;
import com.gs.orbitshield.repository.SatelliteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CollisionEventServiceTest {

    @Mock
    private CollisionEventRepository collisionEventRepository;

    @Mock
    private SatelliteRepository satelliteRepository;

    @InjectMocks
    private CollisionEventService collisionEventService;

    private Satellite satellite;
    private CollisionEventRequest request;
    private CollisionEvent event;

    @BeforeEach
    void setUp() {
        satellite = new Satellite();
        satellite.setName("TestSat");

        request = new CollisionEventRequest();
        request.setObjectName("Debris");
        request.setProbability(0.05);
        request.setClosestApproach(Instant.now().plusSeconds(3600));
        request.setDistanceKm(0.5);

        event = new CollisionEvent();
        event.setSatellite(satellite);
        event.setObjectName("Debris");
        event.setProbability(0.05);
        event.setClosestApproach(request.getClosestApproach());
        event.setDistanceKm(0.5);
        event.setSeverity(Severity.HIGH);
    }

    @Test
    void createCollisionEvent_WhenSatelliteExists_ShouldReturnResponse() {
        when(satelliteRepository.findById(anyString())).thenReturn(Optional.of(satellite));
        when(collisionEventRepository.save(any(CollisionEvent.class))).thenReturn(event);

        CollisionEventResponse response = collisionEventService.createCollisionEvent("sat-id", request);

        assertNotNull(response);
        assertEquals(Severity.HIGH, response.getSeverity());
        verify(collisionEventRepository).save(any(CollisionEvent.class));
    }

    @Test
    void createCollisionEvent_WhenSatelliteNotFound_ShouldThrowException() {
        when(satelliteRepository.findById(anyString())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, 
            () -> collisionEventService.createCollisionEvent("invalid-id", request));
    }

    @Test
    void getCollisionEventById_WhenExists_ShouldReturnResponse() {
        when(collisionEventRepository.findById(anyString())).thenReturn(Optional.of(event));

        CollisionEventResponse response = collisionEventService.getCollisionEventById("event-id");

        assertNotNull(response);
        assertEquals(event.getObjectName(), response.getObjectName());
    }

    @Test
    void resolveCollisionEvent_ShouldSetResolvedTrue() {
        when(collisionEventRepository.findById(anyString())).thenReturn(Optional.of(event));
        when(collisionEventRepository.save(any(CollisionEvent.class))).thenAnswer(invocation -> invocation.getArgument(0));

        CollisionEventResponse response = collisionEventService.resolveCollisionEvent("event-id");

        assertTrue(response.getResolved());
        verify(collisionEventRepository).save(event);
    }

    @Test
    void calculateSeverity_ShouldWorkCorrectively() {
        // This is private but tested through createCollisionEvent or we can use reflection if needed.
        // Let's test via createCollisionEvent with different probabilities.
        
        when(satelliteRepository.findById(anyString())).thenReturn(Optional.of(satellite));
        when(collisionEventRepository.save(any(CollisionEvent.class))).thenAnswer(invocation -> invocation.getArgument(0));

        request.setProbability(0.15); // Critical
        assertEquals(Severity.CRITICAL, collisionEventService.createCollisionEvent("sat-id", request).getSeverity());

        request.setProbability(0.05); // High
        assertEquals(Severity.HIGH, collisionEventService.createCollisionEvent("sat-id", request).getSeverity());

        request.setProbability(0.005); // Medium
        assertEquals(Severity.MEDIUM, collisionEventService.createCollisionEvent("sat-id", request).getSeverity());

        request.setProbability(0.0005); // Low
        assertEquals(Severity.LOW, collisionEventService.createCollisionEvent("sat-id", request).getSeverity());
    }
}
