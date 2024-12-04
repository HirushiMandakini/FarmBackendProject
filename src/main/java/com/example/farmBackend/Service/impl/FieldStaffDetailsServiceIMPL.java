package com.example.farmBackend.Service.impl;

import com.example.farmBackend.Service.FieldStaffDetailsService;
import com.example.farmBackend.dao.FieldDAO;
import com.example.farmBackend.dao.FieldStaffDetailsDAO;
import com.example.farmBackend.dao.StaffDAO;
import com.example.farmBackend.dto.impl.FieldStaffDetailsDTO;
import com.example.farmBackend.entity.impl.Field;
import com.example.farmBackend.entity.impl.FieldStaffDetails;
import com.example.farmBackend.entity.impl.Staff;
import com.example.farmBackend.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class FieldStaffDetailsServiceIMPL implements FieldStaffDetailsService {
    private final FieldStaffDetailsDAO fieldStaffDetailsDAO;
    private final FieldDAO fieldDAO;
    private final StaffDAO staffDAO;
    private final Mapping mapping;
    @Override
    public void saveAssignment(FieldStaffDetailsDTO fieldStaffDetailsDTO) {
        FieldStaffDetails fieldStaffDetails = new FieldStaffDetails();
        Field field = fieldDAO.findById(fieldStaffDetailsDTO.getFieldCode())
                .orElseThrow(() -> new RuntimeException("Field not found"));

        Staff staff = staffDAO.findById(fieldStaffDetailsDTO.getStaffId())
                .orElseThrow(() -> new RuntimeException("Staff not found"));
        fieldStaffDetails.setField(field);
        fieldStaffDetails.setStaff(staff);
        fieldStaffDetails.setAssignedRole(fieldStaffDetailsDTO.getAssignedRole());
        fieldStaffDetails.setAssignmentDate(fieldStaffDetailsDTO.getAssignmentDate());

         fieldStaffDetailsDAO.save(fieldStaffDetails);
    }

    @Override
    public List<FieldStaffDetailsDTO> getAllFieldStaffDetails() {
        List<FieldStaffDetails> logs = fieldStaffDetailsDAO.findAll();
        return mapping.convertToFieldStaffAssignmentDTOList(logs);
    }
}
