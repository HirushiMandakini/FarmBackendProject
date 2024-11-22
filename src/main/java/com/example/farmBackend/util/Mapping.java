package com.example.farmBackend.util;

import com.example.farmBackend.dto.impl.FieldDTO;
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
    public List<FieldDTO> convertToFieldListDTO(List<Field> fieldList){
         return modelMapper.map(fieldList, new TypeToken<List<FieldDTO>>() {}.getType());
       }
    }


