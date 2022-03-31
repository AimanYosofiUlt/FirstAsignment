package com.example.firstdayjava.ui.fragments.setting.profile;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.firstdayjava.pojo.local.entities.User;
import com.example.firstdayjava.pojo.repos.AppSettingRepo;
import com.example.firstdayjava.pojo.repos.UserRepo;

import javax.inject.Inject;

public class ProfileFragmentViewModel extends AndroidViewModel {
    @Inject
    AppSettingRepo settingRepo;
    MutableLiveData<User> appUserMDL;

    @Inject
    public ProfileFragmentViewModel(@NonNull Application application) {
        super(application);
        appUserMDL = new MutableLiveData<>();
    }

    public void getAppUser() {
        appUserMDL.postValue(settingRepo.getAppUser());
    }

    public void setUserProfile(String firstName, String lastName, String email) {
        settingRepo.setUserProfile(firstName,lastName,email);
        getAppUser();
    }
}
