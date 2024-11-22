package com.example.farmBackend.dto.impl;

import com.example.farmBackend.customObj.CropResponse;
import com.example.farmBackend.dto.SuperDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CropDTO implements SuperDTO, CropResponse {
    private String cropCode;
    private String cropCommonName;
    private String cropScientificName;
    private String category;
    private String cropSeason;
    private String cropImage;
    private String fieldCode;
}
