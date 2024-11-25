package com.example.farmBackend.util;

import com.example.farmBackend.dto.impl.CropDTO;
import com.example.farmBackend.dto.impl.FieldDTO;
import com.example.farmBackend.entity.impl.Crop;
import com.example.farmBackend.entity.impl.Field;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class Mapping {
    @Autowired
    private ModelMapper modelMapper;

    //fields matters mapping
    public FieldDTO convertToFieldDTO(Field field) {

        return modelMapper.map(field, FieldDTO.class);
    }

    public Field convertToFieldEntity(FieldDTO fieldDTO) {

        return modelMapper.map(fieldDTO, Field.class);

    }

    public List<FieldDTO> convertToFieldListDTO(List<Field> fieldList) {
        return modelMapper.map(fieldList, new TypeToken<List<FieldDTO>>() {
        }.getType());
    }

    //crop matters mapping
    public CropDTO convertToCropDTO(Crop crop) {
        return modelMapper.map(crop, CropDTO.class);
    }

    public Crop convertToCrop(CropDTO cropDTO) {
        return modelMapper.map(cropDTO, Crop.class);
    }

    public List<CropDTO> convertToCropListDTO(List<Crop> crops) {
        return modelMapper.map(crops, new TypeToken<List<CropDTO>>() {
        }.getType());
    }

}