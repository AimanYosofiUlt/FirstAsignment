package com.example.firstdayjava.ui.fragments.main;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.firstdayjava.pojo.dbs.models.Users;
import com.example.firstdayjava.pojo.repos.UserRepo;

import java.util.List;

public class MainFragmentViewModel extends AndroidViewModel {
    public MainFragmentViewModel(@NonNull Application application) {
        super(application);

    }
}
