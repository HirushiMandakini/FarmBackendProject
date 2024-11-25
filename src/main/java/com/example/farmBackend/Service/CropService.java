package com.example.farmBackend.Service;

import com.example.farmBackend.dto.impl.CropDTO;

import java.util.List;

public interface CropService {
    void saveCrop(CropDTO cropDTO);
    List<CropDTO> getAllCrops();
    void deleteCrop(String cropCode);
    void updateCrop(String cropCode, CropDTO cropDTO);
    List<CropDTO> searchCrop(String searchTerm);

}
