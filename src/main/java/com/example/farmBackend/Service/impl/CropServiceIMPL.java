package com.example.farmBackend.Service.impl;

import com.example.farmBackend.Service.CropService;
import com.example.farmBackend.dao.CropDAO;
import com.example.farmBackend.dao.FieldDAO;
import com.example.farmBackend.dto.impl.CropDTO;
import com.example.farmBackend.entity.impl.Crop;
import com.example.farmBackend.entity.impl.Field;
import com.example.farmBackend.exception.CropNotFoundException;
import com.example.farmBackend.exception.DataPersistException;
import com.example.farmBackend.util.Mapping;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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
        List<Crop> getAllcrops = cropDAO.findAll();
        return mapping.convertToCropListDTO(getAllcrops);
    }

    @Override
    public void deleteCrop(String cropCode) {
        Optional<Crop> selectedCrop = cropDAO.findById(cropCode);
        if(!selectedCrop.isPresent()){
            throw new CropNotFoundException(cropCode);
        } else {
            cropDAO.deleteById(cropCode);
        }
    }

    @Override
    public void updateCrop(String cropCode, CropDTO cropDTO) {
        Crop existingCrop = cropDAO.findById(cropCode)
                .orElseThrow(() -> new CropNotFoundException(cropCode));

        if (cropDTO.getCropCommonName() != null) {
            existingCrop.setCropCommonName(cropDTO.getCropCommonName());
        }
        if (cropDTO.getCropScientificName() != null) {
            existingCrop.setCropScientificName(cropDTO.getCropScientificName());
        }
        if (cropDTO.getCategory() != null) {
            existingCrop.setCategory(cropDTO.getCategory());
        }
        if (cropDTO.getCropSeason() != null) {
            existingCrop.setCropSeason(cropDTO.getCropSeason());
        }
        if (cropDTO.getCropImage() != null) {
            existingCrop.setCropImage(cropDTO.getCropImage());
        }
        cropDAO.save(existingCrop);
    }


    @Override
    public List<CropDTO> searchCrop(String searchTerm) {
        List<Crop> crops = cropDAO.findByCropCodeOrCropCommonName(searchTerm, searchTerm);
        return mapping.convertToCropListDTO(crops);
    }
}
