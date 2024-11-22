package com.example.farmBackend.Service.impl;

import com.example.farmBackend.Service.FieldService;
import com.example.farmBackend.customObj.FieldResponse;
import com.example.farmBackend.dao.FieldDAO;
import com.example.farmBackend.dto.impl.FieldDTO;
import com.example.farmBackend.entity.impl.Field;
import com.example.farmBackend.exception.DataPersistException;
import com.example.farmBackend.util.Mapping;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class FieldServiceIMPL implements FieldService {
    private final FieldDAO fieldDAO;
    private final Mapping mapping;
    @Override
    public void saveField(FieldDTO fieldDTO) {
        Field saveField = fieldDAO.save(mapping.convertToFieldEntity(fieldDTO));
        if (saveField == null) {
            throw new DataPersistException("Field not saved");
        }
        }
    @Override
    public FieldResponse getSelectedField(String fieldCode) {
        return null;
    }

    @Override
    public void updateField(String fieldCode, FieldDTO fieldDTO) {

    }

    @Override
    public void deleteField(String fieldCode) {

    }

    @Override
    public List<FieldDTO> getAllFields() {
        return null;
    }
}
