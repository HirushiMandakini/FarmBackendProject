package com.example.farmBackend.util;

import java.util.UUID;

public class AppUtil {
    public static String generateUserID(){
        return "USER-"+ UUID.randomUUID();
    }
}
