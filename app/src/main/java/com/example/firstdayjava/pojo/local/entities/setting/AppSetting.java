package com.example.firstdayjava.pojo.local.entities.setting;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class AppSetting {
    @ColumnInfo(defaultValue = "0")
    @PrimaryKey
    Integer id;

    @ColumnInfo(defaultValue = NO_USER)
    String currentUserCode;

    @ColumnInfo(defaultValue = DEFAULT)
    String language;

    public static final String DEFAULT = "default";
    public static final String NO_USER = "no_user";

    public AppSetting() {
    }

    @Ignore
    public AppSetting(String currentUserCode, String language) {
        this.currentUserCode = currentUserCode;
        this.language = language;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCurrentUserCode() {
        return currentUserCode;
    }

    public void setCurrentUserCode(String currentUserCode) {
        this.currentUserCode = currentUserCode;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public static String getDEFAULT() {
        return DEFAULT;
    }
}
