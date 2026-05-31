package com.gs.orbitshield.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.gs.orbitshield.model.CollisionEvent;
import java.util.List;

@Repository
public interface CollisionEventRepository extends JpaRepository<CollisionEvent, String> {
    Page<CollisionEvent> findBySatelliteId(String satelliteId, Pageable pageable);
    List<CollisionEvent> findBySatelliteIdAndResolvedFalse(String satelliteId);
}

