package com.example.farmBackend.dto.impl;

import com.example.farmBackend.dto.SuperDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class VehicleDTO implements SuperDTO {
    private String vehicleCode;
    private String licensePlateNumber;
    private String vehicleCategory;
    private String fuelType;
    private String status;
    private String remarks;
    private List<StaffDTO> staff = new ArrayList<>();
}
