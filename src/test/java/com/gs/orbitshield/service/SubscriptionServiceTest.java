package com.gs.orbitshield.service;

import com.gs.orbitshield.context.ApiKeyContext;
import com.gs.orbitshield.dto.request.SubscriptionRequest;
import com.gs.orbitshield.dto.response.SubscriptionResponse;
import com.gs.orbitshield.exception.ConflictException;
import com.gs.orbitshield.exception.ResourceNotFoundException;
import com.gs.orbitshield.model.ApiKey;
import com.gs.orbitshield.model.Satellite;
import com.gs.orbitshield.model.Subscription;
import com.gs.orbitshield.repository.SatelliteRepository;
import com.gs.orbitshield.repository.SubscriptionRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SubscriptionServiceTest {

    @Mock
    private SubscriptionRepository subscriptionRepository;

    @Mock
    private SatelliteRepository satelliteRepository;

    @InjectMocks
    private SubscriptionService subscriptionService;

    private ApiKey apiKey;
    private Satellite satellite;
    private Subscription subscription;

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
    }

    @AfterEach
    void tearDown() {
        ApiKeyContext.clear();
    }

    @Test
    void createSubscription_WhenValid_ShouldReturnResponse() {
        SubscriptionRequest request = new SubscriptionRequest();
        request.setSatelliteId("sat-id");

        when(satelliteRepository.findById("sat-id")).thenReturn(Optional.of(satellite));
        when(subscriptionRepository.findBySatelliteIdAndApiKeyId("sat-id", apiKey.getId())).thenReturn(Optional.empty());
        when(subscriptionRepository.save(any(Subscription.class))).thenReturn(subscription);

        SubscriptionResponse response = subscriptionService.createSubscription(request);

        assertNotNull(response);
        assertEquals(satellite.getName(), response.getSatelliteName());
        verify(subscriptionRepository).save(any(Subscription.class));
    }

    @Test
    void createSubscription_WhenAlreadySubscribed_ShouldThrowConflictException() {
        SubscriptionRequest request = new SubscriptionRequest();
        request.setSatelliteId("sat-id");

        when(satelliteRepository.findById("sat-id")).thenReturn(Optional.of(satellite));
        when(subscriptionRepository.findBySatelliteIdAndApiKeyId("sat-id", apiKey.getId())).thenReturn(Optional.of(subscription));

        assertThrows(ConflictException.class, () -> subscriptionService.createSubscription(request));
    }

    @Test
    void getSubscriptions_ShouldReturnList() {
        when(subscriptionRepository.findByApiKeyId(apiKey.getId())).thenReturn(List.of(subscription));

        List<SubscriptionResponse> results = subscriptionService.getSubscriptions();

        assertFalse(results.isEmpty());
        assertEquals(1, results.size());
    }

    @Test
    void deleteSubscription_WhenExists_ShouldDelete() {
        when(subscriptionRepository.findBySatelliteIdAndApiKeyId("sat-id", apiKey.getId())).thenReturn(Optional.of(subscription));

        subscriptionService.deleteSubscription("sat-id");

        verify(subscriptionRepository).delete(subscription);
    }
}
