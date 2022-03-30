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
import com.example.firstdayjava.pojo.local.entities.SubCategory;
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

    @Query("Select * FROM SubCategory WHERE categoryCode = :categoryCode")
    List<SubCategory> getSubCategories(String categoryCode);


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCategory(Category category);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertSubCategory(SubCategory subCategory);

    @Query("SELECT p.* FROM Product p, ProductPageFilter f " +
            "   WHERE " +
            "       Case :subCatCode " +
            "           WHEN '' THEN categoryCode = :catCode" +
            "           ELSE  categoryCode = :catCode AND subCategoryCode = :subCatCode END" +
            "       AND price >= f.minRange AND price <= f.maxRange " +
            "   ORDER BY " +
            "       CASE :orderBy WHEN 'name' THEN p.name END," +
            "       CASE :orderBy WHEN 'price ASC' THEN p.price END ASC," +
            "       CASE :orderBy WHEN 'price DESC' THEN p.price END DESC")
    List<Product> getProducts(String catCode, String subCatCode, String orderBy);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertProduct(Product product);

    @Query("UPDATE ProductPageFilter SET minRange = 0, maxRange = 1000000")
    void initProductFilter();

    @Query("Select * FROM ProductPageFilter")
    ProductPageFilter getProductFilter();

    @Query("Select * FROM ProductPageFilter")
    LiveData<ProductPageFilter> getProductFilterLive();

    @Update
    void updateProductFilter(ProductPageFilter filter);

    @Query("UPDATE AppSetting SET language = :language")
    void updateLanguage(String language);

    @Query("UPDATE AppSetting SET currentUserCode = :userCode")
    void updateCurrentUser(String userCode);
}
