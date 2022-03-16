package com.example.firstdayjava.pojo.dbs.database;

import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import java.nio.charset.StandardCharsets;

public class AppConvertor {
    @TypeConverter
    public String fromByteArrayToString(byte[] bytes) {
        return new String(bytes, StandardCharsets.UTF_8);
    }

    @TypeConverter
    public byte[] fromStringToByte(String str) {
        return str.getBytes(StandardCharsets.UTF_8);
    }
}
