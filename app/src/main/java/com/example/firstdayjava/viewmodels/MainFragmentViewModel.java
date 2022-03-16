package com.example.firstdayjava.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.firstdayjava.pojo.dbs.models.Users;
import com.example.firstdayjava.pojo.dbs.repos.UserRepo;

import java.util.List;

public class MainFragmentViewModel extends AndroidViewModel {
    UserRepo userRepo;
    public LiveData<List<Users>> users;
    public MainFragmentViewModel(@NonNull Application application) {
        super(application);
        userRepo = new UserRepo(application);
        users = userRepo.users;
    }

    public void deleteUser(Users user){
        userRepo.deleteUser(user);
    }

    public void updateUser(Users user){
        userRepo.updateUser(user);
    }

}
