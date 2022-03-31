package com.example.firstdayjava.ui.activities.splashscreen;

import androidx.lifecycle.MutableLiveData;

import com.example.firstdayjava.pojo.local.entities.setting.AppSetting;
import com.example.firstdayjava.pojo.repos.AppSettingRepo;

import javax.inject.Inject;

public class SplashActivityViewModel {
    MutableLiveData<AppSetting> settingMDL;
    MutableLiveData<Object> initDoneMDL;

    @Inject
    AppSettingRepo settingRepo;

    @Inject
    SplashActivityViewModel() {
        settingMDL = new MutableLiveData<>();
        initDoneMDL= new MutableLiveData<>();
    }

    public void initSetting(){
        settingRepo.initSetting();
        initDoneMDL.postValue(null);
    }

    public void getLanguage() {
        AppSetting setting = settingRepo.getSettingLiveData();
        settingMDL.postValue(setting);
    }
}
