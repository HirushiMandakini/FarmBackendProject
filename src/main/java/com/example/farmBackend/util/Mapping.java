package com.example.farmBackend.util;

import com.example.farmBackend.dto.impl.*;
import com.example.farmBackend.entity.impl.*;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

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
    // vehicle matters mapping
    public VehicleDTO convertToVehicleDTO(Vehicle vehicle) {
        return modelMapper.map(vehicle, VehicleDTO.class);
    }
    public Vehicle convertToVehicle(VehicleDTO vehicleDTO) {
        return modelMapper.map(vehicleDTO, Vehicle.class);
    }
    public List<VehicleDTO> convertToVehicleListDTO(List<Vehicle> vehicles) {
        return modelMapper.map(vehicles, new TypeToken<List<VehicleDTO>>() {}.getType());
    }
    //monitoringLog matters mapping
    public MonitoringLogDTO convertToMonitoringLogDTO(MonitoringLog monitoringLog) {
        return modelMapper.map(monitoringLog, MonitoringLogDTO.class);
    }

    public MonitoringLog convertToMonitoringLog(MonitoringLogDTO monitoringLogDTO) {
        return modelMapper.map(monitoringLogDTO, MonitoringLog.class);
    }

    public List<MonitoringLogDTO> convertToMonitoringLogListDTO(List<MonitoringLog> monitoringLogs) {
        return monitoringLogs.stream().map(log -> {
            MonitoringLogDTO monitoringLogDTO = modelMapper.map(log, MonitoringLogDTO.class);
            monitoringLogDTO.setFieldCode(log.getField().getFieldCode());
            monitoringLogDTO.setCropCode(log.getCrop().getCropCode());
            monitoringLogDTO.setId(log.getStaff().getId());
            return monitoringLogDTO;
        }).collect(Collectors.toList());
    }
    //field staff assignment
    public List<FieldStaffDetailsDTO> convertToFieldStaffAssignmentDTOList(List<FieldStaffDetails> fieldStaffDetails) {
        return fieldStaffDetails.stream()
                .map(assignment -> {
                    FieldStaffDetailsDTO fieldStaffAssignmentDTO = modelMapper.map(assignment, FieldStaffDetailsDTO.class);
                    fieldStaffAssignmentDTO.setFieldCode(assignment.getField().getFieldCode());
                    fieldStaffAssignmentDTO.setStaffId(assignment.getStaff().getId().toString());
                    return fieldStaffAssignmentDTO;
                })
                .collect(Collectors.toList());
    }
}