package com.example.firstdayjava.pojo.dbs.repos;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.firstdayjava.pojo.dbs.database.AppDao;
import com.example.firstdayjava.pojo.dbs.database.AppDatabase;
import com.example.firstdayjava.pojo.dbs.models.Users;

import java.util.List;

public class UserRepo{
    Application application;
    AppDao appDao;
     public    LiveData<List<Users>> users;

    public UserRepo(Application application) {
        this.application = application;
        appDao = AppDatabase.getDatabase(application).appDao();
        users = appDao.getUsers();
    }

    public void addUser(Users user){
        appDao.insertUser(user);
    }

    public Users getUserByEamil(String email){
        return appDao.getUserByEmail(email);
    }

    public void deleteUser(Users user){
        appDao.deleteUser(user);
    }

    public void updateUser(Users user){
        appDao.updateUser(user);
    }

}
