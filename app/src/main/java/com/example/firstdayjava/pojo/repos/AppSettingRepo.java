package com.example.firstdayjava.pojo.repos;

import androidx.lifecycle.LiveData;

import com.example.firstdayjava.pojo.local.database.AppDao;
import com.example.firstdayjava.pojo.local.entities.setting.AppSetting;

import javax.inject.Inject;

public class AppSettingRepo {
    @Inject
    AppDao appDao;

    @Inject
    AppSettingRepo() {

    }

    public AppSetting getSettingLiveData() {
        return appDao.getSetting();
    }

    public void updateLanguage(String language) {
        appDao.updateLanguage(language);
    }

    public void updateCurrentUser(String userCode) {
        appDao.updateCurrentUser(userCode);
    }

    public void initSetting() {
        appDao.initSetting();
    }

    public String getUserCode(){
        return appDao.getUserCode();
    }
}
