package com.gs.orbitshield.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.gs.orbitshield.dto.request.CollisionEventRequest;
import com.gs.orbitshield.dto.response.CollisionEventResponse;
import com.gs.orbitshield.dto.response.ApiEnvelope;
import com.gs.orbitshield.service.CollisionEventService;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "Collision Events", description = "Manage collision events")
public class CollisionEventController {

    private final CollisionEventService collisionEventService;

    public CollisionEventController(CollisionEventService collisionEventService) {
        this.collisionEventService = collisionEventService;
    }

    @GetMapping("/satellites/{satelliteId}/events")
    @Operation(summary = "List collision events for a satellite", description = "Retrieve all collision events for a specific satellite")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Events retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Satellite not found")
    })
    public ResponseEntity<ApiEnvelope<List<CollisionEventResponse>>> getEventsBySatellite(
            @PathVariable String satelliteId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<CollisionEventResponse> response = collisionEventService.getCollisionEventsBySatellite(satelliteId, pageable);
        ApiEnvelope<List<CollisionEventResponse>> apiResponse = ApiEnvelope.paged(
                response.getContent(), page, size, response.getTotalElements());
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/events/{id}")
    @Operation(summary = "Get collision event by ID", description = "Retrieve a specific collision event")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Event retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Event not found")
    })
    public ResponseEntity<ApiEnvelope<CollisionEventResponse>> getEventById(@PathVariable String id) {
        CollisionEventResponse response = collisionEventService.getCollisionEventById(id);
        return ResponseEntity.ok(ApiEnvelope.success(response));
    }

    @PostMapping("/satellites/{satelliteId}/events")
    @Operation(summary = "Create a collision event", description = "Register a new collision event for a satellite")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Event created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "Satellite not found")
    })
    public ResponseEntity<ApiEnvelope<CollisionEventResponse>> createEvent(
            @PathVariable String satelliteId,
            @Valid @RequestBody CollisionEventRequest request) {
        CollisionEventResponse response = collisionEventService.createCollisionEvent(satelliteId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiEnvelope.success(response));
    }

    @PutMapping("/events/{id}/resolve")
    @Operation(summary = "Resolve a collision event", description = "Mark a collision event as resolved")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Event resolved successfully"),
            @ApiResponse(responseCode = "404", description = "Event not found")
    })
    public ResponseEntity<ApiEnvelope<CollisionEventResponse>> resolveEvent(@PathVariable String id) {
        CollisionEventResponse response = collisionEventService.resolveCollisionEvent(id);
        return ResponseEntity.ok(ApiEnvelope.success(response));
    }

    @DeleteMapping("/events/{id}")
    @Operation(summary = "Delete a collision event", description = "Remove a collision event")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Event deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Event not found")
    })
    public ResponseEntity<Void> deleteEvent(@PathVariable String id) {
        collisionEventService.deleteCollisionEvent(id);
        return ResponseEntity.noContent().build();
    }
}



