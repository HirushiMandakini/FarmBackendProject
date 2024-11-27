package com.example.farmBackend.util;

import com.example.farmBackend.dto.impl.CropDTO;
import com.example.farmBackend.dto.impl.EquipmentDTO;
import com.example.farmBackend.dto.impl.FieldDTO;
import com.example.farmBackend.dto.impl.StaffDTO;
import com.example.farmBackend.entity.impl.Crop;
import com.example.farmBackend.entity.impl.Equipment;
import com.example.farmBackend.entity.impl.Field;
import com.example.farmBackend.entity.impl.Staff;
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
    //equipment matters mapping
    public EquipmentDTO convertToEquipmentDTO(Equipment equipment){
        return modelMapper.map(equipment, EquipmentDTO.class);
    }
    public Equipment convertToEquipmentEntity(EquipmentDTO equipmentDTO) {
        return modelMapper.map(equipmentDTO, Equipment.class);
    }

    public List<EquipmentDTO> convertToEquipmentListDTO(List<Equipment> equipmentList) {
        return modelMapper.map(equipmentList, new TypeToken<List<EquipmentDTO>>() {
        }.getType());
    }
    //staff matters mapping

    public StaffDTO convertToStaffDTO(Staff staff) {
        return modelMapper.map(staff, StaffDTO.class);
    }
    public Staff convertToStaff(StaffDTO staffDTO) {
        return modelMapper.map(staffDTO, Staff.class);
    }
    public List<StaffDTO> convertToStaffListDTO(List<Staff> staff) {
        return modelMapper.map(staff, new TypeToken<List<StaffDTO>>() {}.getType());
    }
}