package com.example.farmBackend.Service;

import com.example.farmBackend.dto.impl.MonitoringLogDTO;

import java.util.List;

public interface MonitoringLogService {
    void saveMonitoringLog(MonitoringLogDTO monitoringLogDTO);
    List<MonitoringLogDTO> searchMonitoringLog(String searchTerm);
    List<MonitoringLogDTO> getAllMonitoringLog();
    void deleteMonitoringLog(String log_code);
    void updateMonitoringLog(String log_code, MonitoringLogDTO monitoringLogDTO);
}