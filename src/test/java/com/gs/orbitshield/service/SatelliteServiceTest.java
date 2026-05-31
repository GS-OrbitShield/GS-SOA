package com.gs.orbitshield.service;

import com.gs.orbitshield.dto.request.SatelliteRequest;
import com.gs.orbitshield.dto.response.SatelliteResponse;
import com.gs.orbitshield.exception.ConflictException;
import com.gs.orbitshield.exception.ResourceNotFoundException;
import com.gs.orbitshield.model.OrbitType;
import com.gs.orbitshield.model.Satellite;
import com.gs.orbitshield.model.SatelliteStatus;
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

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SatelliteServiceTest {

    @Mock
    private SatelliteRepository satelliteRepository;

    @InjectMocks
    private SatelliteService satelliteService;

    private SatelliteRequest request;
    private Satellite satellite;

    @BeforeEach
    void setUp() {
        request = new SatelliteRequest();
        request.setName("TestSat");
        request.setOwnerCompany("TestCorp");
        request.setNoradId("12345");
        request.setOrbitType(OrbitType.LEO);
        request.setStatus(SatelliteStatus.ACTIVE);
        request.setAltitudeKm(500.0);
        request.setInclination(45.0);

        satellite = new Satellite();
        satellite.setName("TestSat");
        satellite.setOwnerCompany("TestCorp");
        satellite.setNoradId("12345");
        satellite.setOrbitType(OrbitType.LEO);
        satellite.setStatus(SatelliteStatus.ACTIVE);
        satellite.setAltitudeKm(500.0);
        satellite.setInclination(45.0);
    }

    @Test
    void createSatellite_WhenValidRequest_ShouldReturnResponse() {
        when(satelliteRepository.findByName(anyString())).thenReturn(Optional.empty());
        when(satelliteRepository.save(any(Satellite.class))).thenReturn(satellite);

        SatelliteResponse response = satelliteService.createSatellite(request);

        assertNotNull(response);
        assertEquals(request.getName(), response.getName());
        verify(satelliteRepository).save(any(Satellite.class));
    }

    @Test
    void createSatellite_WhenNameExists_ShouldThrowConflictException() {
        when(satelliteRepository.findByName(anyString())).thenReturn(Optional.of(satellite));

        assertThrows(ConflictException.class, () -> satelliteService.createSatellite(request));
        verify(satelliteRepository, never()).save(any());
    }

    @Test
    void getSatelliteById_WhenExists_ShouldReturnResponse() {
        when(satelliteRepository.findById(anyString())).thenReturn(Optional.of(satellite));

        SatelliteResponse response = satelliteService.getSatelliteById("test-id");

        assertNotNull(response);
        assertEquals(satellite.getName(), response.getName());
    }

    @Test
    void getSatelliteById_WhenNotExists_ShouldThrowResourceNotFoundException() {
        when(satelliteRepository.findById(anyString())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> satelliteService.getSatelliteById("test-id"));
    }

    @Test
    void getAllSatellites_ShouldReturnPage() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Satellite> page = new PageImpl<>(List.of(satellite));
        when(satelliteRepository.findAll(pageable)).thenReturn(page);

        Page<SatelliteResponse> result = satelliteService.getAllSatellites(pageable);

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
    }

    @Test
    void updateSatellite_WhenValid_ShouldReturnUpdatedResponse() {
        String id = "test-id";
        when(satelliteRepository.findById(id)).thenReturn(Optional.of(satellite));
        when(satelliteRepository.save(any(Satellite.class))).thenReturn(satellite);

        SatelliteResponse response = satelliteService.updateSatellite(id, request);

        assertNotNull(response);
        verify(satelliteRepository).save(any(Satellite.class));
    }

    @Test
    void deleteSatellite_WhenExists_ShouldCallDelete() {
        String id = "test-id";
        when(satelliteRepository.findById(id)).thenReturn(Optional.of(satellite));

        satelliteService.deleteSatellite(id);

        verify(satelliteRepository).delete(satellite);
    }
}
