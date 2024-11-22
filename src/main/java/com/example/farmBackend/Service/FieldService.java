package com.example.farmBackend.Service;

import com.example.farmBackend.customObj.FieldResponse;
import com.example.farmBackend.dto.impl.FieldDTO;

import java.util.List;

public interface FieldService {
    void saveField(FieldDTO fieldDTO);
    FieldResponse getSelectedField(String fieldCode);
    void updateField(String fieldCode, FieldDTO fieldDTO);
    void deleteField(String fieldCode);
    List<FieldDTO> getAllFields();
}
