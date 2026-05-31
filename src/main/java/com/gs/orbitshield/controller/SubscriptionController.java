package com.gs.orbitshield.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.gs.orbitshield.dto.request.SubscriptionRequest;
import com.gs.orbitshield.dto.response.SubscriptionResponse;
import com.gs.orbitshield.dto.response.ApiEnvelope;
import com.gs.orbitshield.service.SubscriptionService;
import java.util.List;

@RestController
@RequestMapping("/api/v1/subscriptions")
@Tag(name = "Subscriptions", description = "Manage satellite subscriptions")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @GetMapping
    @Operation(summary = "List subscriptions", description = "Retrieve all satellites followed by the current API Key")
    @ApiResponse(responseCode = "200", description = "Subscriptions retrieved successfully")
    public ResponseEntity<ApiEnvelope<List<SubscriptionResponse>>> getSubscriptions() {
        List<SubscriptionResponse> response = subscriptionService.getSubscriptions();
        return ResponseEntity.ok(ApiEnvelope.success(response));
    }

    @PostMapping
    @Operation(summary = "Create a subscription", description = "Follow a satellite with the current API Key")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Subscription created successfully"),
            @ApiResponse(responseCode = "404", description = "Satellite not found"),
            @ApiResponse(responseCode = "409", description = "Already subscribed to this satellite")
    })
    public ResponseEntity<ApiEnvelope<SubscriptionResponse>> createSubscription(@Valid @RequestBody SubscriptionRequest request) {
        SubscriptionResponse response = subscriptionService.createSubscription(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiEnvelope.success(response));
    }

    @DeleteMapping("/{satelliteId}")
    @Operation(summary = "Delete a subscription", description = "Unfollow a satellite")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Subscription deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Subscription not found")
    })
    public ResponseEntity<Void> deleteSubscription(@PathVariable String satelliteId) {
        subscriptionService.deleteSubscription(satelliteId);
        return ResponseEntity.noContent().build();
    }
}



