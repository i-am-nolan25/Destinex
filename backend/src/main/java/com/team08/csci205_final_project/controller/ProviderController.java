package com.team08.csci205_final_project.controller;

import com.team08.csci205_final_project.model.DTO.Provider.ProviderRegister;
import com.team08.csci205_final_project.model.Job.Job;
import com.team08.csci205_final_project.model.Provider.Provider;
import com.team08.csci205_final_project.service.ProviderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

/**
 * Controller class for managing provider-related operations in the Spring application.
 * It provides endpoints for provider registration, information retrieval, location updates,
 * job handling, and searching for nearby providers.
 *
 * Utilizes {@link ProviderService} for business logic associated with provider operations.
 *
 */
@RestController
@RequestMapping("/api/providers")
@SecurityRequirement(name = "bearerAuth")
public class ProviderController {

    @Autowired
    private ProviderService providerService;

    /** Create a new provider */
    @Operation(summary = "Register becoming a provider")
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Provider> addProvider(@Valid @RequestBody ProviderRegister providerRegister) {
        Provider provider = providerService.providerRegister(providerRegister);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(provider.getProviderId())
                .toUri();
        return ResponseEntity.created(location).body(provider);
    }

    /** Get provider information based on userId */
    @GetMapping
    public ResponseEntity<Provider> getProviderById(@PathVariable String id) {
        Optional<Provider> provider = providerService.findProviderById(id);
        return provider.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

//    /** Update provider's information */
//    @PutMapping("/{id}")
//    public ResponseEntity<Provider> updateProvider(@PathVariable String id, @RequestBody Provider provider)  {
//        return providerService.findProviderById(id)
//                .map(existingProvider -> {
//                    existingProvider.setJobHistory(provider.getJobHistory());
//                    existingProvider.setCurrentLocation(provider.getCurrentLocation());
//
//                    //TODO Need to update to follow new format
//                    Provider updatedProvider = providerService.providerRegister().get();
//                    return new ResponseEntity<>(updatedProvider, HttpStatus.OK);
//                })
//                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
//    }

    /** Delete a provider */
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteProvider(@PathVariable String id) {
        providerService.deleteProvider(id);
        return ResponseEntity.noContent().build();
    }

    /** Update provider's location */
    @PatchMapping("/{id}/location")
    public ResponseEntity<HttpStatus> updateLocation(@PathVariable String id, @RequestBody Point location) {
        providerService.updateProviderLocation(id, location.getX(), location.getY());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /** Find nearby providers */
    @GetMapping("/nearby")
    public ResponseEntity<List<Provider>> findNearbyProviders(@RequestParam double latitude,
                                                              @RequestParam double longitude,
                                                              @RequestParam double distance) {
        List<Provider> providers = providerService.findNearbyProviders(latitude, longitude, distance);
        return new ResponseEntity<>(providers, HttpStatus.OK);
    }

    /** Accept a job for the provider */
    @PostMapping("/{id}/accept")
    public ResponseEntity<HttpStatus> acceptJob(@PathVariable String id) {
        if (providerService.acceptJob(id))
            return ResponseEntity.ok().build();
        else
            return ResponseEntity.badRequest().build();
    }

    /** Provider complete a job */
    @PostMapping("/{providerEmail}/complete")
    public ResponseEntity<HttpStatus> completeJob(@PathVariable String providerEmail) {
        if (providerService.completeJob(providerEmail))
            return ResponseEntity.ok().build();
        else
            return ResponseEntity.badRequest().build();
    }

    /** Show provider's currently assigned job */
    @GetMapping("/{email}/my-assigned-job")
    public ResponseEntity<Job> findAssignedJob(@PathVariable("email") String providerEmail) {
        Optional<Job> assignedJob = providerService.findAssignedJob(providerEmail);

        return assignedJob
                .map(job -> new ResponseEntity<>(job, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
