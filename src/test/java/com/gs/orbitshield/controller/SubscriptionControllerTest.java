package com.gs.orbitshield.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.gs.orbitshield.dto.request.SubscriptionRequest;
import com.gs.orbitshield.dto.response.SubscriptionResponse;
import com.gs.orbitshield.exception.GlobalExceptionHandler;
import com.gs.orbitshield.service.SubscriptionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.Instant;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class SubscriptionControllerTest {

    private MockMvc mockMvc;

    @Mock
    private SubscriptionService subscriptionService;

    private ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

    @InjectMocks
    private SubscriptionController subscriptionController;

    private SubscriptionResponse response;
    private SubscriptionRequest request;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(subscriptionController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();

        response = new SubscriptionResponse("sub1", "sat1", "SatName", Instant.now());
        request = new SubscriptionRequest();
        request.setSatelliteId("sat1");
    }

    @Test
    void getSubscriptions_ShouldReturnList() throws Exception {
        when(subscriptionService.getSubscriptions()).thenReturn(List.of(response));

        mockMvc.perform(get("/api/v1/subscriptions"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0].satelliteName").value("SatName"));
    }

    @Test
    void createSubscription_ShouldReturn201() throws Exception {
        when(subscriptionService.createSubscription(any(SubscriptionRequest.class))).thenReturn(response);

        mockMvc.perform(post("/api/v1/subscriptions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.data.satelliteName").value("SatName"));
    }

    @Test
    void deleteSubscription_ShouldReturn204() throws Exception {
        mockMvc.perform(delete("/api/v1/subscriptions/sat1"))
                .andExpect(status().isNoContent());
    }
}
