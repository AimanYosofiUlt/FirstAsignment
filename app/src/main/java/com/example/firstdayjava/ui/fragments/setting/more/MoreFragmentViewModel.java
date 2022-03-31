package com.example.firstdayjava.ui.fragments.setting.more;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.firstdayjava.pojo.repos.AppSettingRepo;

import javax.inject.Inject;

public class MoreFragmentViewModel extends AndroidViewModel {
    @Inject
    AppSettingRepo settingRepo;

    @Inject
    public MoreFragmentViewModel(@NonNull Application application) {
        super(application);
    }

    public void logOut() {
        settingRepo.logOut();
    }
}
