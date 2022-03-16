package com.example.firstdayjava.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.firstdayjava.pojo.dbs.models.Users;
import com.example.firstdayjava.pojo.dbs.repos.UserRepo;

public class EditFragmentViewModel extends AndroidViewModel {
    UserRepo userRepo;
    public EditFragmentViewModel(@NonNull Application application) {
        super(application);
        userRepo = new UserRepo(application);
    }

    public void addUser(Users user){
        userRepo.addUser(user);
    }

}
