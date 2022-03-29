package com.example.firstdayjava.pojo.local.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.firstdayjava.pojo.local.entities.Category;
import com.example.firstdayjava.pojo.local.entities.Product;
import com.example.firstdayjava.pojo.local.entities.User;
import com.example.firstdayjava.pojo.local.entities.setting.ProductPageFilter;

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

    @Query("SELECT * FROM Product WHERE categoryCode = :catCode order by name")
    List<Product> getProductsOrderByName(String catCode);

    @Query("SELECT * FROM Product WHERE categoryCode = :catCode order by price ASC")
    List<Product> getProductsOrderByPriceLower(String catCode);

    @Query("SELECT * FROM Product WHERE categoryCode = :catCode order by price DESC")
    List<Product> getProductsOrderByPriceHigher(String catCode);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertProduct(Product product);

    @Query("UPDATE ProductPageFilter SET minRange = 0, maxRange = 1000")
    void initProductFilter();

    @Query("Select * FROM ProductPageFilter")
    ProductPageFilter getProductFilter();

    @Query("Select * FROM ProductPageFilter")
    LiveData<ProductPageFilter> getProductFilterLive();

    @Update
    void updateProductFilter(ProductPageFilter filter);
}
