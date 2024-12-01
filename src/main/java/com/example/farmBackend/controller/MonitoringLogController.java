
package com.example.farmBackend.controller;

import com.example.farmBackend.Service.MonitoringLogService;
import com.example.farmBackend.customObj.FieldErrorResponse;
import com.example.farmBackend.dto.impl.MonitoringLogDTO;
import com.example.farmBackend.exception.DataPersistException;
import com.example.farmBackend.exception.MonitoringLogNotFoundException;
import com.example.farmBackend.util.AppUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/monitoringLog")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://127.0.0.1:5501")
public class MonitoringLogController {
    private final MonitoringLogService monitoringLogService;

    @PreAuthorize("hasAnyRole('MANAGER', 'SCIENTIST')")

    // POST Method: Save Monitoring Log with Image Upload
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveMonitoringLog(
            @RequestParam("logCode") String logCode,
            @RequestParam("logDate") String logDate,
            @RequestParam("observation") String observation,
            @RequestParam("logImage") MultipartFile logImage,
            @RequestParam("fieldCode") String fieldCode,
            @RequestParam("cropCode") String cropCode,
            @RequestParam("staffId") String id) {

        try {
            // Convert image to Base64
            String base64Image = AppUtil.toBase64(logImage.getBytes());

            // Create Monitoring Log DTO
            MonitoringLogDTO logDTO = new MonitoringLogDTO();
            logDTO.setLog_code(logCode);
            logDTO.setLog_date(logDate);
            logDTO.setObservation(observation);
            logDTO.setLog_image(base64Image);
            logDTO.setFieldCode(fieldCode);
            logDTO.setCropCode(cropCode);
            logDTO.setId(id);

            // Save Monitoring Log
            monitoringLogService.saveMonitoringLog(logDTO);

            return new ResponseEntity<>(new FieldErrorResponse(0, "Monitoring Log saved successfully"), HttpStatus.CREATED);

        } catch (DataPersistException e) {
            return new ResponseEntity<>(new FieldErrorResponse(1, "Can't save Monitoring Log: " + e.getMessage()), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new FieldErrorResponse(1, "Internal server error: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // GET Method: Get All Monitoring Logs
    @GetMapping(value = "allLogs", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MonitoringLogDTO> getAllMonitoringLogs() {
        return monitoringLogService.getAllMonitoringLog();
    }

    // GET Method: Search Monitoring Logs by Search Term
    @GetMapping()
    public ResponseEntity<List<MonitoringLogDTO>> searchMonitoringLogs(@RequestParam("searchTerm") String searchTerm) {
        List<MonitoringLogDTO> logs = monitoringLogService.searchMonitoringLog(searchTerm);
        return new ResponseEntity<>(logs, HttpStatus.OK);
    }

    // PUT Method: Update Monitoring Log
    @PreAuthorize("hasAnyRole('MANAGER', 'SCIENTIST')")
    @PutMapping(value = "/{logCode}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> updateMonitoringLog(
            @PathVariable("logCode") String logCode,
            @RequestParam(value = "logDate", required = false) String logDate,
            @RequestParam(value = "observation", required = false) String observation,
            @RequestParam(value = "logImage", required = false) MultipartFile logImage,
            @RequestParam(value = "fieldCode", required = false) String fieldCode,
            @RequestParam(value = "cropCode", required = false) String cropCode,
            @RequestParam(value = "staffId", required = false) String id) {

        try {
            // Create Monitoring Log DTO and set updated values
            MonitoringLogDTO logDTO = new MonitoringLogDTO();
            if (logDate != null) logDTO.setLog_date(logDate);
            if (observation != null) logDTO.setObservation(observation);
            if (logImage != null && !logImage.isEmpty()) {
                logDTO.setLog_image(AppUtil.toBase64(logImage.getBytes()));
            }
            if (fieldCode != null) logDTO.setFieldCode(fieldCode);
            if (cropCode != null) logDTO.setCropCode(cropCode);
            if (id != null) logDTO.setId(id);

            // Update Monitoring Log
            monitoringLogService.updateMonitoringLog(logCode, logDTO);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // DELETE Method: Delete Monitoring Log
    @PreAuthorize("hasAnyRole('MANAGER', 'SCIENTIST')")
    @DeleteMapping(value = "/{logCode}")
    public ResponseEntity<Void> deleteMonitoringLog(@PathVariable("logCode") String logCode) {
        try {
            monitoringLogService.deleteMonitoringLog(logCode);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (MonitoringLogNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
