package com.gs.orbitshield.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.gs.orbitshield.dto.request.CollisionEventRequest;
import com.gs.orbitshield.dto.response.CollisionEventResponse;
import com.gs.orbitshield.exception.GlobalExceptionHandler;
import com.gs.orbitshield.model.Severity;
import com.gs.orbitshield.service.CollisionEventService;
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
class CollisionEventControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CollisionEventService collisionEventService;

    private ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

    @InjectMocks
    private CollisionEventController collisionEventController;

    private CollisionEventResponse response;
    private CollisionEventRequest request;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(collisionEventController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .build();

        response = new CollisionEventResponse("id1", "sat1", "SatName", "Debris", 0.01, Instant.now(), 1.0, Severity.HIGH, false, Instant.now());
        
        request = new CollisionEventRequest();
        request.setObjectName("Debris");
        request.setProbability(0.01);
        request.setClosestApproach(Instant.now().plusSeconds(3600));
        request.setDistanceKm(1.0);
    }

    @Test
    void getEventsBySatellite_ShouldReturnPagedResponse() throws Exception {
        when(collisionEventService.getCollisionEventsBySatellite(eq("sat1"), any(Pageable.class)))
                .thenReturn(new PageImpl<>(List.of(response)));

        mockMvc.perform(get("/api/v1/satellites/sat1/events"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0].objectName").value("Debris"));
    }

    @Test
    void createEvent_ShouldReturn201() throws Exception {
        when(collisionEventService.createCollisionEvent(eq("sat1"), any(CollisionEventRequest.class)))
                .thenReturn(response);

        mockMvc.perform(post("/api/v1/satellites/sat1/events")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.data.objectName").value("Debris"));
    }

    @Test
    void resolveEvent_ShouldReturn200() throws Exception {
        response.setResolved(true);
        when(collisionEventService.resolveCollisionEvent("id1")).thenReturn(response);

        mockMvc.perform(put("/api/v1/events/id1/resolve"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.resolved").value(true));
    }
}
