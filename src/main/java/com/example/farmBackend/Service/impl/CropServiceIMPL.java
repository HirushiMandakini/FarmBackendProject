package com.example.farmBackend.Service.impl;

import com.example.farmBackend.Service.CropService;
import com.example.farmBackend.dao.CropDAO;
import com.example.farmBackend.dao.FieldDAO;
import com.example.farmBackend.dto.impl.CropDTO;
import com.example.farmBackend.entity.impl.Crop;
import com.example.farmBackend.entity.impl.Field;
import com.example.farmBackend.exception.DataPersistException;
import com.example.farmBackend.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
@RequiredArgsConstructor
public class CropServiceIMPL implements CropService {
    private final CropDAO cropDAO;
    private final FieldDAO fieldDAO;
    private final Mapping mapping;
    @Override
    public void saveCrop(CropDTO cropDTO) {
        Field field = fieldDAO.findById(cropDTO.getFieldCode())
                .orElseThrow(() -> new DataPersistException("Invalid field code"));
        Crop crop = mapping.convertToCrop(cropDTO);
        crop.setField(field);
        Crop savedCrop = cropDAO.save(crop);
        try {
            if (savedCrop == null) {
                throw new DataPersistException("Can't save Crop");
            }
        } catch (DataPersistException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<CropDTO> getAllCrops() {
        return null;
    }

    @Override
    public void deleteCrop(String cropCode) {

    }

    @Override
    public void updateCrop(String cropCode, CropDTO cropDTO) {

    }

    @Override
    public List<CropDTO> searchCrop(String searchTerm) {
        return null;
    }
}
