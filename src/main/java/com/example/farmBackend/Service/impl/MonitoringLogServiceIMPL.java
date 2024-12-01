package com.example.farmBackend.Service.impl;

import com.example.farmBackend.Service.MonitoringLogService;
import com.example.farmBackend.dao.CropDAO;
import com.example.farmBackend.dao.FieldDAO;
import com.example.farmBackend.dao.MonitoringLogDAO;
import com.example.farmBackend.dao.StaffDAO;
import com.example.farmBackend.dto.impl.MonitoringLogDTO;
import com.example.farmBackend.entity.impl.Crop;
import com.example.farmBackend.entity.impl.Field;
import com.example.farmBackend.entity.impl.MonitoringLog;
import com.example.farmBackend.entity.impl.Staff;
import com.example.farmBackend.exception.*;
import com.example.farmBackend.util.Mapping;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MonitoringLogServiceIMPL implements MonitoringLogService {
    private final CropDAO cropDAO;
    private final FieldDAO fieldDAO;
    private final StaffDAO staffDAO;
    private final MonitoringLogDAO monitoringLogDAO;
    private final Mapping mapping;

    @Override
    public void saveMonitoringLog(MonitoringLogDTO monitoringLogDTO) {
        Field field = fieldDAO.findById(monitoringLogDTO.getFieldCode())
                .orElseThrow(() -> new DataPersistException("Invalid field code"));
        Crop crop = cropDAO.findById(monitoringLogDTO.getCropCode())
                .orElseThrow(() -> new DataPersistException("Invalid crop code"));
        Staff staff = staffDAO.findById(monitoringLogDTO.getId())
                .orElseThrow(() -> new DataPersistException("Invalid staff ID"));

        MonitoringLog log = mapping.convertToMonitoringLog(monitoringLogDTO);
        log.setField(field);
        log.setCrop(crop);
        log.setStaff(staff);

        MonitoringLog savedLog = monitoringLogDAO.save(log);
        if (savedLog == null) {
            throw new DataPersistException("Can't save Monitoring Log");
        }
    }

    @Override
    public List<MonitoringLogDTO> searchMonitoringLog(String searchTerm) {
        List<MonitoringLog> logs = monitoringLogDAO.findByMonitoringLogCodeOrMonitoringLogDate(searchTerm, searchTerm);
        return mapping.convertToMonitoringLogListDTO(logs);
    }

    @Override
    public List<MonitoringLogDTO> getAllMonitoringLog() {
        List<MonitoringLog> logs = monitoringLogDAO.findAll();
        return mapping.convertToMonitoringLogListDTO(logs);
    }

    @Override
    public void deleteMonitoringLog(String log_code) {
        Optional<MonitoringLog> selectedLog = monitoringLogDAO.findById(log_code);
        if (!selectedLog.isPresent()) {
            throw new MonitoringLogNotFoundException(log_code);
        } else {
            monitoringLogDAO.deleteById(log_code);
        }
    }

    @Override
    public void updateMonitoringLog(String log_code, MonitoringLogDTO logDTO) {
        MonitoringLog existingLog = monitoringLogDAO.findById(log_code)
                .orElseThrow(() -> new MonitoringLogNotFoundException(log_code));

        if (logDTO.getLog_date() != null) {
            existingLog.setLog_date(logDTO.getLog_date());
        }
        if (logDTO.getObservation() != null) {
            existingLog.setObservation(logDTO.getObservation());
        }
        if (logDTO.getLog_image() != null) {
            existingLog.setLog_image(logDTO.getLog_image());
        }
        if (logDTO.getFieldCode() != null) {
            Field field = fieldDAO.findById(logDTO.getFieldCode())
                    .orElseThrow(() -> new FieldNotFoundException("Field not found with code: " + logDTO.getFieldCode()));
            existingLog.setField(field);
        }

        if (logDTO.getCropCode() != null) {
            Crop crop = cropDAO.findById(logDTO.getCropCode())
                    .orElseThrow(() -> new CropNotFoundException("Crop not found with code: " + logDTO.getCropCode()));
            existingLog.setCrop(crop);
        }

        if (logDTO.getId() != null) {
            Staff staff = staffDAO.findById(logDTO.getId())
                    .orElseThrow(() -> new StaffMemberNotFoundException("Staff not found with ID: " + logDTO.getId()));
            existingLog.setStaff(staff);
        }
        monitoringLogDAO.save(existingLog);
    }

}
