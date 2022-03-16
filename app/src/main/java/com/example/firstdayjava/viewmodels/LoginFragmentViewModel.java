package com.example.firstdayjava.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.firstdayjava.pojo.dbs.models.Users;
import com.example.firstdayjava.pojo.dbs.repos.UserRepo;

import java.util.List;

public class LoginFragmentViewModel extends AndroidViewModel {
    UserRepo userRepo;
  public   MutableLiveData<Users> user;

    public LoginFragmentViewModel(@NonNull Application application) {
        super(application);
        userRepo = new UserRepo(application);
        user = new MutableLiveData<>();
    }

    public void checkByEmail(String email) {
        user.postValue(userRepo.getUserByEamil(email));
    }
}
