package com.gs.orbitshield.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.gs.orbitshield.model.Satellite;
import java.util.Optional;

@Repository
public interface SatelliteRepository extends JpaRepository<Satellite, String> {
    Optional<Satellite> findByName(String name);
}

