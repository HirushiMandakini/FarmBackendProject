package com.example.farmBackend.customObj;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class EquipmentErrorResponse implements EquipmentResponse {
    private int errorCode;
    private String errorMessage;
}
