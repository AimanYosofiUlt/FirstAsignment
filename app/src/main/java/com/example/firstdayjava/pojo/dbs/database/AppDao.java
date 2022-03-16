package com.example.firstdayjava.pojo.dbs.database;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.firstdayjava.pojo.dbs.models.Users;

import java.util.List;

@Dao
public interface AppDao {

    @Query("SELECT * FROM Users")
    public LiveData<List<Users>> getUsers();

    @Insert()
    public void insertUser(Users user);

    @Query("SELECT * FROM Users where email  =:email")
    public Users getUserByEmail(String email);

    @Delete
    public void deleteUser(Users user);

    @Update
    public void updateUser(Users user);
}
