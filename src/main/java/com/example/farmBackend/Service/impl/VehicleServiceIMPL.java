package com.example.farmBackend.Service.impl;

import com.example.farmBackend.Service.VehicleService;
import com.example.farmBackend.dao.VehicleDAO;
import com.example.farmBackend.dto.impl.VehicleDTO;
import com.example.farmBackend.entity.impl.Vehicle;
import com.example.farmBackend.exception.DataPersistException;
import com.example.farmBackend.util.Mapping;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
@Transactional
public class VehicleServiceIMPL implements VehicleService {
    private final VehicleDAO vehicleDAO;
    private final Mapping mapping;
    @Override
    public void saveVehicle(VehicleDTO vehicleDTO) {
        Vehicle savedVehicle = vehicleDAO.save(mapping.convertToVehicle(vehicleDTO));
        if (savedVehicle == null) {
            throw new DataPersistException("Can't save vehicle");
        }
    }

    @Override
    public List<VehicleDTO> getAllVehicles() {
        return null;
    }

    @Override
    public List<VehicleDTO> searchVehicles(String searchTerm) {
        return null;
    }

    @Override
    public void deleteVehicle(String vehicleCode) {

    }

    @Override
    public void updateVehicle(String vehicleCode, VehicleDTO vehicleDTO) {

    }
}
