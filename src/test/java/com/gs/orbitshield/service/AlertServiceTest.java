package com.gs.orbitshield.service;

import com.gs.orbitshield.context.ApiKeyContext;
import com.gs.orbitshield.dto.response.CollisionEventResponse;
import com.gs.orbitshield.model.ApiKey;
import com.gs.orbitshield.model.CollisionEvent;
import com.gs.orbitshield.model.Satellite;
import com.gs.orbitshield.model.Severity;
import com.gs.orbitshield.model.Subscription;
import com.gs.orbitshield.repository.CollisionEventRepository;
import com.gs.orbitshield.repository.SubscriptionRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AlertServiceTest {

    @Mock
    private SubscriptionRepository subscriptionRepository;

    @Mock
    private CollisionEventRepository collisionEventRepository;

    @InjectMocks
    private AlertService alertService;

    private ApiKey apiKey;
    private Satellite satellite;
    private Subscription subscription;
    private CollisionEvent event;

    @BeforeEach
    void setUp() {
        apiKey = new ApiKey();
        apiKey.setId("api-key-id");
        ApiKeyContext.set(apiKey);

        satellite = new Satellite();
        satellite.setId("sat-id");
        satellite.setName("TestSat");

        subscription = new Subscription();
        subscription.setSatellite(satellite);
        subscription.setApiKeyId(apiKey.getId());

        event = new CollisionEvent();
        event.setId("event-id");
        event.setSatellite(satellite);
        event.setObjectName("Debris");
        event.setProbability(0.01);
        event.setClosestApproach(Instant.now().plusSeconds(3600));
        event.setDistanceKm(1.0);
        event.setSeverity(Severity.HIGH);
        event.setResolved(false);
    }

    @AfterEach
    void tearDown() {
        ApiKeyContext.clear();
    }

    @Test
    void getAlerts_ShouldReturnSortedAlerts() {
        when(subscriptionRepository.findByApiKeyId(apiKey.getId())).thenReturn(List.of(subscription));
        when(collisionEventRepository.findBySatelliteIdAndResolvedFalse(satellite.getId())).thenReturn(List.of(event));

        List<CollisionEventResponse> alerts = alertService.getAlerts();

        assertFalse(alerts.isEmpty());
        assertEquals(1, alerts.size());
        assertEquals("Debris", alerts.get(0).getObjectName());
    }

    @Test
    void getAlerts_WhenNoApiKeyContext_ShouldThrowException() {
        ApiKeyContext.clear();
        assertThrows(IllegalStateException.class, () -> alertService.getAlerts());
    }
}
