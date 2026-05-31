package com.gs.orbitshield.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.gs.orbitshield.dto.request.SatelliteRequest;
import com.gs.orbitshield.dto.response.SatelliteResponse;
import com.gs.orbitshield.dto.response.ApiEnvelope;
import com.gs.orbitshield.service.SatelliteService;
import java.util.List;

@RestController
@RequestMapping("/api/v1/satellites")
@Tag(name = "Satellites", description = "Manage satellite registrations")
public class SatelliteController {

    private final SatelliteService satelliteService;

    public SatelliteController(SatelliteService satelliteService) {
        this.satelliteService = satelliteService;
    }

    @GetMapping
    @Operation(summary = "List all satellites", description = "Retrieve all registered satellites with pagination")
    @ApiResponse(responseCode = "200", description = "Satellites retrieved successfully")
    public ResponseEntity<ApiEnvelope<List<SatelliteResponse>>> getAllSatellites(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<SatelliteResponse> response = satelliteService.getAllSatellites(pageable);
        ApiEnvelope<List<SatelliteResponse>> apiResponse = ApiEnvelope.paged(
                response.getContent(), page, size, response.getTotalElements());
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get satellite by ID", description = "Retrieve a specific satellite by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Satellite retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Satellite not found")
    })
    public ResponseEntity<ApiEnvelope<SatelliteResponse>> getSatelliteById(@PathVariable String id) {
        SatelliteResponse response = satelliteService.getSatelliteById(id);
        return ResponseEntity.ok(ApiEnvelope.success(response));
    }

    @PostMapping
    @Operation(summary = "Create a new satellite", description = "Register a new satellite in the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Satellite created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "409", description = "Satellite name already exists")
    })
    public ResponseEntity<ApiEnvelope<SatelliteResponse>> createSatellite(@Valid @RequestBody SatelliteRequest request) {
        SatelliteResponse response = satelliteService.createSatellite(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiEnvelope.success(response));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a satellite", description = "Update an existing satellite's information")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Satellite updated successfully"),
            @ApiResponse(responseCode = "404", description = "Satellite not found"),
            @ApiResponse(responseCode = "409", description = "Satellite name already exists")
    })
    public ResponseEntity<ApiEnvelope<SatelliteResponse>> updateSatellite(
            @PathVariable String id,
            @Valid @RequestBody SatelliteRequest request) {
        SatelliteResponse response = satelliteService.updateSatellite(id, request);
        return ResponseEntity.ok(ApiEnvelope.success(response));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a satellite", description = "Remove a satellite and all its associated data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Satellite deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Satellite not found")
    })
    public ResponseEntity<Void> deleteSatellite(@PathVariable String id) {
        satelliteService.deleteSatellite(id);
        return ResponseEntity.noContent().build();
    }
}



