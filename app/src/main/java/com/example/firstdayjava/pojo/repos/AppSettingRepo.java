package com.example.firstdayjava.pojo.repos;

import com.example.firstdayjava.pojo.local.database.AppDao;

import javax.inject.Inject;

public class AppSettingRepo {
    @Inject
    AppDao appDao;

    @Inject
    AppSettingRepo() {

    }

    public void updateLanguage(String language) {
        appDao.updateLanguage(language);
    }

    public void updateCurrentUser(String userCode) {
        appDao.updateCurrentUser(userCode);
    }
}
