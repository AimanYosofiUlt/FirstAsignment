package com.example.firstdayjava.pojo.dbs.database;

import androidx.room.TypeConverter;

public class AppConvertor {
    @TypeConverter
    public String fromStringToByte(byte[] array) {
        return "";
    }

    @TypeConverter
    public byte[] fromByteToString(String str) {
        return new byte[]{};
    }
}
