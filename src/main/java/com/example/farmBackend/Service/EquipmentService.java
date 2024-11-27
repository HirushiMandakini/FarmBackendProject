package com.example.farmBackend.Service;

import com.example.farmBackend.dto.impl.EquipmentDTO;

import java.util.List;

public interface EquipmentService {
    void saveEquipment(EquipmentDTO equipmentDTO);
    EquipmentDTO getSelectedEquipment(String equipmentId);
    void updateEquipment(String equipmentId, EquipmentDTO equipmentDTO);
    void deleteEquipment(String equipmentId);
    List<EquipmentDTO> getAllEquipment();
}
