package com.example.farmBackend.controller;

import com.example.farmBackend.Service.FieldStaffDetailsService;
import com.example.farmBackend.dto.impl.FieldStaffDetailsDTO;
import com.example.farmBackend.entity.impl.FieldStaffDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/assignment")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class FieldStaffDetailsController {
    private final FieldStaffDetailsService fieldStaffDetailsService;

    @PreAuthorize("hasAnyRole('MANAGER')")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> saveAssignment(@RequestBody FieldStaffDetailsDTO fieldStaffDetailsDTO) {
        fieldStaffDetailsService.saveAssignment(fieldStaffDetailsDTO);
        return ResponseEntity.ok("Assignment saved successfully");
    }

    @GetMapping(value = "allassignments", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<FieldStaffDetailsDTO> getAllAssignments() {
        return fieldStaffDetailsService.getAllFieldStaffDetails();
    }


}
