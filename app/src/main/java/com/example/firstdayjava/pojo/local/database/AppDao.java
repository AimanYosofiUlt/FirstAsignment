package com.example.firstdayjava.pojo.local.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.firstdayjava.pojo.local.entities.Category;
import com.example.firstdayjava.pojo.local.entities.User;

import java.util.List;

@Dao
public interface AppDao {

    @Query("SELECT * FROM User")
    LiveData<List<User>> getUsers();

    @Insert()
    void insertUser(User user);

    @Delete
    void deleteUser(User user);

    @Update
    void updateUser(User user);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUserFromLogin(User user);

    @Query("Select * FROM Category")
    List<Category> getCategories();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCategory(Category category);
}
