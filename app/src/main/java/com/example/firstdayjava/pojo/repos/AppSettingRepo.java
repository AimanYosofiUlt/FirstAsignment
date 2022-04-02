package com.example.firstdayjava.pojo.repos;

import com.example.firstdayjava.pojo.local.database.AppDao;
import com.example.firstdayjava.pojo.local.entities.User;
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

    public void logOut() {
        appDao.setToNoUser(AppSetting.NO_USER);
    }

    public User getAppUser() {
        return appDao.getAppUser();
    }

    public void setUserProfile(String firstName, String lastName, String email) {
        appDao.setUserProfile(firstName, lastName, email);
    }

    public boolean isPasswordCorrect(String currentPass) {
        return appDao.getPassword().equals(currentPass);
    }


    public void changePassword(String newPassword) {
        appDao.changePassword(newPassword);
    }
}
