package com.example.farmBackend.controller;

import com.example.farmBackend.Service.StaffService;
import com.example.farmBackend.dto.impl.StaffDTO;
import com.example.farmBackend.entity.impl.Staff;
import com.example.farmBackend.entity.impl.Vehicle;
import com.example.farmBackend.exception.CropNotFoundException;
import com.example.farmBackend.exception.DataPersistException;
import com.example.farmBackend.exception.StaffMemberNotFoundException;
import com.example.farmBackend.exception.VehicleNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.yaml.snakeyaml.nodes.NodeId.mapping;

@RestController
@RequestMapping(value = "api/v1/staff")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class StaffController {
    private final StaffService staffService;
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveStaffMember(@RequestBody StaffDTO staff) {
        if (staff == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else {
            try {
                staffService.saveStaff(staff);
                return new ResponseEntity<>(HttpStatus.CREATED);
            }catch (DataPersistException e){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }catch (Exception e){
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }




    @GetMapping(value = "allstaff", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<StaffDTO> getAllStaffMember() {
        return staffService.getAllStaffs();
    }

//    @PreAuthorize("hasAnyRole('MANAGER', 'ADMINISTRATIVE')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteSelectedMember(@PathVariable("id") String id) {
        try {
            staffService.deleteStaff(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (CropNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    @PreAuthorize("hasAnyRole('MANAGER', 'ADMINISTRATIVE')")
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateSelectedMember(
            @PathVariable("id") String id,
            @RequestBody StaffDTO staffDTO
    ) {
        try {
            staffService.updateStaff(id, staffDTO);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (VehicleNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping()
    public ResponseEntity<List<StaffDTO>> searchStaffMember(@RequestParam("searchTerm") String searchTerm) {
        List<StaffDTO> staffDTOS = staffService.searchStaff(searchTerm);
        return new ResponseEntity<>(staffDTOS, HttpStatus.OK);
    }

//    @PreAuthorize("hasAnyRole('MANAGER', 'ADMINISTRATIVE')")
    @PutMapping(value = "/{id}/return-vehicle")
    public ResponseEntity<Void> returnVehicle(@PathVariable("id") String staffId) {
        try {
            staffService.returnVehicle(staffId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (StaffMemberNotFoundException | VehicleNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
