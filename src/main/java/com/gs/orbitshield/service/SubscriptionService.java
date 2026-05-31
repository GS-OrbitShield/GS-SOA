package com.gs.orbitshield.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
import java.util.List;

@Service
@Transactional
public class SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final SatelliteRepository satelliteRepository;

    public SubscriptionService(SubscriptionRepository subscriptionRepository,
                             SatelliteRepository satelliteRepository) {
        this.subscriptionRepository = subscriptionRepository;
        this.satelliteRepository = satelliteRepository;
    }

    public SubscriptionResponse createSubscription(SubscriptionRequest request) {
        ApiKey currentApiKey = ApiKeyContext.get();
        if (currentApiKey == null) {
            throw new IllegalStateException("API Key context is not set");
        }

        // Verify satellite exists
        Satellite satellite = satelliteRepository.findById(request.getSatelliteId())
                .orElseThrow(() -> new ResourceNotFoundException("Satellite", "id", request.getSatelliteId()));

        // Check if already subscribed
        if (subscriptionRepository.findBySatelliteIdAndApiKeyId(satellite.getId(), currentApiKey.getId()).isPresent()) {
            throw new ConflictException("API Key is already subscribed to satellite '" + satellite.getName() + "'.");
        }

        Subscription subscription = new Subscription();
        subscription.setSatellite(satellite);
        subscription.setApiKeyId(currentApiKey.getId());

        Subscription saved = subscriptionRepository.save(subscription);
        return mapToResponse(saved);
    }

    public List<SubscriptionResponse> getSubscriptions() {
        ApiKey currentApiKey = ApiKeyContext.get();
        if (currentApiKey == null) {
            throw new IllegalStateException("API Key context is not set");
        }

        return subscriptionRepository.findByApiKeyId(currentApiKey.getId())
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public void deleteSubscription(String satelliteId) {
        ApiKey currentApiKey = ApiKeyContext.get();
        if (currentApiKey == null) {
            throw new IllegalStateException("API Key context is not set");
        }

        Subscription subscription = subscriptionRepository.findBySatelliteIdAndApiKeyId(satelliteId, currentApiKey.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Subscription", "satellite_id", satelliteId));

        subscriptionRepository.delete(subscription);
    }

    private SubscriptionResponse mapToResponse(Subscription subscription) {
        return new SubscriptionResponse(
                subscription.getId(),
                subscription.getSatellite().getId(),
                subscription.getSatellite().getName(),
                subscription.getCreatedAt()
        );
    }
}

