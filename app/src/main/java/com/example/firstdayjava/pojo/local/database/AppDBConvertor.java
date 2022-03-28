package com.example.firstdayjava.pojo.local.database;

import androidx.room.TypeConverter;

public class AppDBConvertor {
    @TypeConverter
    public String fromStringToByte(byte[] array) {
        return "";
    }

    @TypeConverter
    public byte[] fromByteToString(String str) {
        return new byte[]{};
    }
}
