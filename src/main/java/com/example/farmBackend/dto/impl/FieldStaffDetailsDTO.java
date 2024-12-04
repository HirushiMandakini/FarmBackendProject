package com.example.farmBackend.dto.impl;

import com.example.farmBackend.dto.SuperDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FieldStaffDetailsDTO implements SuperDTO {
    private Long id;
    private String fieldCode;
    private String staffId;
    private String assignedRole;
    private String assignmentDate;
}
