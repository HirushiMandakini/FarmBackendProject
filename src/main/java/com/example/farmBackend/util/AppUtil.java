package com.example.farmBackend.util;

import java.util.Base64;
import java.util.UUID;

public class AppUtil {
    public static String toBase64(byte[] data) {
        return Base64.getEncoder().encodeToString(data);
    }
}
