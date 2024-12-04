package com.example.farmBackend.Service;

import com.example.farmBackend.dto.impl.FieldStaffDetailsDTO;

import java.util.List;

public interface FieldStaffDetailsService {
    void saveAssignment(FieldStaffDetailsDTO fieldStaffDetailsDTO);
    List<FieldStaffDetailsDTO> getAllFieldStaffDetails();
}
