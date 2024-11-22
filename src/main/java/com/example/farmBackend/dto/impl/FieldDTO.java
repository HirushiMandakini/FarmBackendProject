package com.example.farmBackend.dto.impl;

import com.example.farmBackend.customObj.FieldResponse;
import com.example.farmBackend.dto.SuperDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FieldDTO implements FieldResponse, SuperDTO, Serializable {
    private String fieldCode;
    private String fieldName;
    private String fieldLocation;
    private Double extentSize;
    private String fieldImage1;
    private String fieldImage2;
    private List<CropDTO> crops=new ArrayList<>();
    private List<StaffDTO> staffList = new ArrayList<>();

}
