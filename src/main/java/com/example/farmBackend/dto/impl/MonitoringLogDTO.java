package com.example.farmBackend.dto.impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MonitoringLogDTO {
    private String log_code;
    private String log_date;
    private String Observation;
    private String log_image;
    private String fieldCode;
    private String cropCode;
    private String id;
}
