package com.example.farmBackend.Service.impl;

import com.example.farmBackend.Service.EquipmentService;
import com.example.farmBackend.customObj.FieldErrorResponse;
import com.example.farmBackend.dao.EquipmentDAO;
import com.example.farmBackend.dao.FieldDAO;
import com.example.farmBackend.dao.StaffDAO;
import com.example.farmBackend.dto.impl.EquipmentDTO;
import com.example.farmBackend.entity.impl.Equipment;
import com.example.farmBackend.entity.impl.Field;
import com.example.farmBackend.entity.impl.Staff;
import com.example.farmBackend.exception.DataPersistException;
import com.example.farmBackend.exception.EquipmentNotFoundException;
import com.example.farmBackend.exception.FieldNotFoundException;
import com.example.farmBackend.exception.StaffMemberNotFoundException;
import com.example.farmBackend.util.Mapping;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class EquipmentServiceIMPL implements EquipmentService {
    private final EquipmentDAO equipmentDAO;
    private final FieldDAO fieldDAO;
    private final StaffDAO staffDAO;
    private final Mapping mapping;
    @Override
    public void saveEquipment(EquipmentDTO equipmentDTO) {
        Field field = fieldDAO.findById(equipmentDTO.getFieldCode())
                .orElseThrow(() -> new IllegalArgumentException("Field not found"));
        Staff staff = staffDAO.findById((equipmentDTO.getId()))
                .orElseThrow(() -> new IllegalArgumentException("Invalid staff ID"));

        Equipment equipment = mapping.convertToEquipmentEntity(equipmentDTO);
        equipment.setField(field);
        equipment.setStaff(staff);
        Equipment saveEquipment = equipmentDAO.save(equipment);
        try {
            if (saveEquipment == null) {
                throw new DataPersistException("Equipment not saved");
            }
        }catch (DataPersistException e){
            e.printStackTrace();
        }
    }
    @Override
    public List<EquipmentDTO> searchEquipment(String searchTerm) {
        List<Equipment> equipments = equipmentDAO.findByEquipmentIdOrEquipmentName(searchTerm, searchTerm);
        return mapping.convertToEquipmentListDTO(equipments);
    }


    @Override
    public void updateEquipment(String equipmentId, EquipmentDTO equipmentDTO) {
        Equipment existingEquipment = equipmentDAO.findById(equipmentId)
                .orElseThrow(() -> new EquipmentNotFoundException("Equipment not found with ID: " + equipmentId));

        if (equipmentDTO.getEquipmentName() != null) {
            existingEquipment.setEquipmentName(equipmentDTO.getEquipmentName());
        }
        if (equipmentDTO.getEquipmentType() != null) {
            existingEquipment.setEquipmentType(equipmentDTO.getEquipmentType());
        }
        if (equipmentDTO.getEquipmentStatus() != null) {
            existingEquipment.setEquipmentStatus(equipmentDTO.getEquipmentStatus());
        }
        if (equipmentDTO.getFieldCode() != null) {
            Field field = fieldDAO.findById(equipmentDTO.getFieldCode())
                    .orElseThrow(() -> new FieldNotFoundException("Field not found with code: " + equipmentDTO.getFieldCode()));
            existingEquipment.setField(field);
        }
        if (equipmentDTO.getId() != null) {
            Staff staff = staffDAO.findById(equipmentDTO.getId())
                    .orElseThrow(() -> new StaffMemberNotFoundException("Staff not found with ID: " + equipmentDTO.getId()));
            existingEquipment.setStaff(staff);
        }

        equipmentDAO.save(existingEquipment);
    }

    @Override
    public void deleteEquipment(String equipmentId) {
        Optional<Equipment> selectedEquipment = equipmentDAO.findById(equipmentId);
        if (!selectedEquipment.isPresent()) {
            throw new EquipmentNotFoundException(equipmentId);
        } else {
            equipmentDAO.deleteById(equipmentId);
        }
    }

    @Override
    public List<EquipmentDTO> getAllEquipment() {
        List<Equipment> getAllEquipment = equipmentDAO.findAll();
        return mapping.convertToEquipmentListDTO(getAllEquipment);
    }
}
