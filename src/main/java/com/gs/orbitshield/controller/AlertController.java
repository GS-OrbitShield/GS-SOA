package com.gs.orbitshield.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.gs.orbitshield.dto.response.CollisionEventResponse;
import com.gs.orbitshield.dto.response.ApiEnvelope;
import com.gs.orbitshield.service.AlertService;
import java.util.List;

@RestController
@RequestMapping("/api/v1/alerts")
@Tag(name = "Alerts", description = "Retrieve active collision alerts")
public class AlertController {

    private final AlertService alertService;

    public AlertController(AlertService alertService) {
        this.alertService = alertService;
    }

    @GetMapping
    @Operation(summary = "Get active alerts", description = "Retrieve all active collision alerts for satellites followed by the current API Key")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Alerts retrieved successfully")
    public ResponseEntity<ApiEnvelope<List<CollisionEventResponse>>> getAlerts() {
        List<CollisionEventResponse> alerts = alertService.getAlerts();
        return ResponseEntity.ok(ApiEnvelope.success(alerts));
    }
}



