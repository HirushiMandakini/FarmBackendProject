package com.example.farmBackend.Service.impl;

import com.example.farmBackend.Service.EquipmentService;
import com.example.farmBackend.dto.impl.EquipmentDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Transactional
@RequiredArgsConstructor
public class EquipmentServiceIMPL implements EquipmentService {
    @Override
    public void saveEquipment(EquipmentDTO equipmentDTO) {

    }

    @Override
    public EquipmentDTO getSelectedEquipment(String equipmentId) {
        return null;
    }

    @Override
    public void updateEquipment(String equipmentId, EquipmentDTO equipmentDTO) {

    }

    @Override
    public void deleteEquipment(String equipmentId) {

    }

    @Override
    public List<EquipmentDTO> getAllEquipment() {
        return null;
    }
}
