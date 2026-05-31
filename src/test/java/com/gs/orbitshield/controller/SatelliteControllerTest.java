package com.gs.orbitshield.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.gs.orbitshield.dto.request.SatelliteRequest;
import com.gs.orbitshield.dto.response.SatelliteResponse;
import com.gs.orbitshield.exception.GlobalExceptionHandler;
import com.gs.orbitshield.exception.ResourceNotFoundException;
import com.gs.orbitshield.model.OrbitType;
import com.gs.orbitshield.model.SatelliteStatus;
import com.gs.orbitshield.service.SatelliteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.Instant;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class SatelliteControllerTest {

    private MockMvc mockMvc;

    @Mock
    private SatelliteService satelliteService;

    private ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

    @InjectMocks
    private SatelliteController satelliteController;

    private SatelliteResponse response;
    private SatelliteRequest request;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(satelliteController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .build();

        response = new SatelliteResponse("id1", "Sat1", "Corp1", "123", OrbitType.LEO, 500.0, 45.0, SatelliteStatus.ACTIVE, Instant.now(), Instant.now());
        
        request = new SatelliteRequest();
        request.setName("Sat1");
        request.setOwnerCompany("Corp1");
        request.setOrbitType(OrbitType.LEO);
        request.setStatus(SatelliteStatus.ACTIVE);
    }

    @Test
    void getAllSatellites_ShouldReturnPagedResponse() throws Exception {
        when(satelliteService.getAllSatellites(any(Pageable.class))).thenReturn(new PageImpl<>(List.of(response)));

        mockMvc.perform(get("/api/v1/satellites"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("success"))
                .andExpect(jsonPath("$.data[0].name").value("Sat1"));
    }

    @Test
    void getSatelliteById_WhenExists_ShouldReturnResponse() throws Exception {
        when(satelliteService.getSatelliteById("id1")).thenReturn(response);

        mockMvc.perform(get("/api/v1/satellites/id1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.name").value("Sat1"));
    }

    @Test
    void getSatelliteById_WhenNotFound_ShouldReturn404() throws Exception {
        when(satelliteService.getSatelliteById("id1")).thenThrow(new ResourceNotFoundException("Satellite", "id", "id1"));

        mockMvc.perform(get("/api/v1/satellites/id1"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error.code").value("SATELLITE_NOT_FOUND"));
    }

    @Test
    void createSatellite_ShouldReturn201() throws Exception {
        when(satelliteService.createSatellite(any(SatelliteRequest.class))).thenReturn(response);

        mockMvc.perform(post("/api/v1/satellites")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.data.name").value("Sat1"));
    }

    @Test
    void updateSatellite_ShouldReturn200() throws Exception {
        when(satelliteService.updateSatellite(eq("id1"), any(SatelliteRequest.class))).thenReturn(response);

        mockMvc.perform(put("/api/v1/satellites/id1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.name").value("Sat1"));
    }

    @Test
    void deleteSatellite_ShouldReturn204() throws Exception {
        mockMvc.perform(delete("/api/v1/satellites/id1"))
                .andExpect(status().isNoContent());
    }
}
