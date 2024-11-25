package com.example.farmBackend.Service.impl;

import com.example.farmBackend.Service.FieldService;
import com.example.farmBackend.customObj.FieldErrorResponse;
import com.example.farmBackend.customObj.FieldResponse;
import com.example.farmBackend.dao.FieldDAO;
import com.example.farmBackend.dto.impl.FieldDTO;
import com.example.farmBackend.entity.impl.Field;
import com.example.farmBackend.exception.DataPersistException;
import com.example.farmBackend.exception.FieldNotFoundException;
import com.example.farmBackend.util.Mapping;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        if (fieldDAO.existsById(fieldCode)) {
            Field fields = fieldDAO.getReferenceById(fieldCode);
            return mapping.convertToFieldDTO(fields);
        } else {
            return new FieldErrorResponse(0, "Field not found");
        }

    }


    @Override
    public void updateField(String fieldCode, FieldDTO fieldDTO) {
        Optional<Field> tmpField = fieldDAO.findById(fieldCode);
        if (!tmpField.isPresent()) {
            throw new FieldNotFoundException("Field not found");
        } else {
            Field existingField = tmpField.get();

            if (fieldDTO.getFieldName() != null) {
                existingField.setFieldName(fieldDTO.getFieldName());
            }
            if (fieldDTO.getFieldLocation() != null) {
                existingField.setFieldLocation(fieldDTO.getFieldLocation());
            }
            if (fieldDTO.getExtentSize() != null) {
                existingField.setExtentSize(fieldDTO.getExtentSize());
            }
            if (fieldDTO.getFieldImage1() != null) {
                existingField.setFieldImage1(fieldDTO.getFieldImage1());
            }
            if (fieldDTO.getFieldImage2() != null) {
                existingField.setFieldImage2(fieldDTO.getFieldImage2());
            }
            fieldDAO.save(existingField);
        }
    }

    @Override
    public void deleteField(String fieldCode) {
        Optional<Field> selectedField = fieldDAO.findById(fieldCode);
        if(!selectedField.isPresent()){
            throw new FieldNotFoundException(fieldCode);
        } else {
            fieldDAO.deleteById(fieldCode);
        }
    }

    @Override
    public List<FieldDTO> getAllFields() {
        List<Field> getAllFields = fieldDAO.findAll();
        return mapping.convertToFieldListDTO(getAllFields);
    }
}
