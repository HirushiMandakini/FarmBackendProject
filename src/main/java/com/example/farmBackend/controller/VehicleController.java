package com.example.farmBackend.controller;

import com.example.farmBackend.Service.VehicleService;
import com.example.farmBackend.dto.impl.VehicleDTO;
import com.example.farmBackend.exception.DataPersistException;
import com.example.farmBackend.exception.VehicleNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/vehicles")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class VehicleController {
    @Autowired
    private final VehicleService vehicleService;

    @PreAuthorize("hasAnyRole('MANAGER', 'ADMINISTRATIVE')")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> saveVehicle(@RequestBody VehicleDTO vehicleDTO) {
        try {
            vehicleService.saveVehicle(vehicleDTO);
            return new ResponseEntity<>("Vehicle saved successfully", HttpStatus.CREATED);
        } catch (DataPersistException e) {
            return new ResponseEntity<>("Failed to save vehicle: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(value = "allVehicles", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<VehicleDTO> getAllVehicles() {
        return vehicleService.getAllVehicles();
    }

    @PreAuthorize("hasAnyRole('MANAGER', 'ADMINISTRATIVE')")
    @DeleteMapping(value = "/{code}")
    public ResponseEntity<Void> deleteSelectedVehicle(@PathVariable("code") String code) {
        try {
            vehicleService.deleteVehicle(code);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (VehicleNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping()
    public ResponseEntity<List<VehicleDTO>> searchVehicles(@RequestParam("searchTerm") String searchTerm) {
        List<VehicleDTO> vehicleDTOS = vehicleService.searchVehicles(searchTerm);
        return new ResponseEntity<>(vehicleDTOS, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('MANAGER', 'ADMINISTRATIVE')")
    @PutMapping (value = "/{vehicleCode}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateSelectedVehicle(
            @PathVariable("vehicleCode") String vehicleCode,
            @RequestBody VehicleDTO vehicleDTO
    ) {
        try {
            vehicleService.updateVehicle(vehicleCode, vehicleDTO);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (VehicleNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
