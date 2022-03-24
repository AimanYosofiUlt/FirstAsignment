package com.example.firstdayjava.pojo.dbs.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.firstdayjava.pojo.dbs.models.Users;

import java.util.List;

@Dao
public interface AppDao {

    @Query("SELECT * FROM Users")
    LiveData<List<Users>> getUsers();

    @Insert()
    void insertUser(Users user);

    @Delete
    void deleteUser(Users user);

    @Update
    void updateUser(Users user);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUserFromLogin(Users user);
}
