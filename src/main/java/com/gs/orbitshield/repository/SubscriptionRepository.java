package com.gs.orbitshield.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.gs.orbitshield.model.Subscription;
import java.util.List;
import java.util.Optional;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, String> {
    List<Subscription> findByApiKeyId(String apiKeyId);
    Optional<Subscription> findBySatelliteIdAndApiKeyId(String satelliteId, String apiKeyId);
}

