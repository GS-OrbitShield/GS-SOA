package com.gs.orbitshield.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.gs.orbitshield.context.ApiKeyContext;
import com.gs.orbitshield.dto.response.CollisionEventResponse;
import com.gs.orbitshield.model.ApiKey;
import com.gs.orbitshield.model.CollisionEvent;
import com.gs.orbitshield.model.Subscription;
import com.gs.orbitshield.repository.CollisionEventRepository;
import com.gs.orbitshield.repository.SubscriptionRepository;
import java.util.Comparator;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class AlertService {

    private final SubscriptionRepository subscriptionRepository;
    private final CollisionEventRepository collisionEventRepository;

    public AlertService(SubscriptionRepository subscriptionRepository,
                       CollisionEventRepository collisionEventRepository) {
        this.subscriptionRepository = subscriptionRepository;
        this.collisionEventRepository = collisionEventRepository;
    }

    public List<CollisionEventResponse> getAlerts() {
        ApiKey currentApiKey = ApiKeyContext.get();
        if (currentApiKey == null) {
            throw new IllegalStateException("API Key context is not set");
        }

        // Get all subscriptions for the current API Key
        List<Subscription> subscriptions = subscriptionRepository.findByApiKeyId(currentApiKey.getId());

        // Get all unresolved collision events for subscribed satellites
        List<CollisionEventResponse> alerts = subscriptions.stream()
                .flatMap(subscription -> collisionEventRepository
                        .findBySatelliteIdAndResolvedFalse(subscription.getSatellite().getId())
                        .stream()
                        .map(this::mapToResponse))
                .sorted(Comparator
                        .comparing((CollisionEventResponse::getSeverity), Comparator.reverseOrder())
                        .thenComparing(CollisionEventResponse::getClosestApproach))
                .toList();

        return alerts;
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

