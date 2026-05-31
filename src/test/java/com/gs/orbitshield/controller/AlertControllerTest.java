package com.gs.orbitshield.controller;

import com.gs.orbitshield.dto.response.CollisionEventResponse;
import com.gs.orbitshield.exception.GlobalExceptionHandler;
import com.gs.orbitshield.model.Severity;
import com.gs.orbitshield.service.AlertService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.Instant;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class AlertControllerTest {

    private MockMvc mockMvc;

    @Mock
    private AlertService alertService;

    @InjectMocks
    private AlertController alertController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(alertController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
    }

    @Test
    void getAlerts_ShouldReturnList() throws Exception {
        CollisionEventResponse alert = new CollisionEventResponse("id1", "sat1", "SatName", "Debris", 0.01, Instant.now(), 1.0, Severity.HIGH, false, Instant.now());
        when(alertService.getAlerts()).thenReturn(List.of(alert));

        mockMvc.perform(get("/api/v1/alerts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0].objectName").value("Debris"));
    }
}
