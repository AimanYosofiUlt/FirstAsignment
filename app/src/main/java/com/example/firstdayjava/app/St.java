package com.example.firstdayjava.app;

import androidx.room.TypeConverter;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;

public class St {
    public static String fromByteArrayToString(byte[] bytes) {
        return new String(bytes, StandardCharsets.UTF_8);
    }

    public static byte[] fromStringToByteArray(String str) {
        return str.getBytes(StandardCharsets.UTF_8);
    }

    public static byte[] getRandomKey(){
        SecureRandom random = new SecureRandom();
        byte[] key = new byte[16];
        random.nextBytes(key);
        return key;
    }
}
