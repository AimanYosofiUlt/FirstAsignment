package com.example.firstdayjava.pojo.local.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.firstdayjava.pojo.local.entities.Cart;
import com.example.firstdayjava.pojo.local.entities.Category;
import com.example.firstdayjava.pojo.local.entities.Product;
import com.example.firstdayjava.pojo.local.entities.SubCategory;
import com.example.firstdayjava.pojo.local.entities.User;
import com.example.firstdayjava.pojo.local.entities.setting.AppSetting;
import com.example.firstdayjava.pojo.local.entities.setting.ProductPageFilter;
import com.example.firstdayjava.ui.views.ProductView.ProductViewData;

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

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertUserFromLogin(User user);

    @Query("Select * FROM Category")
    List<Category> getCategories();

    @Query("Select * FROM SubCategory WHERE categoryCode = :categoryCode")
    List<SubCategory> getSubCategories(String categoryCode);


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertCategory(Category category);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertSubCategory(SubCategory subCategory);

    @Query("SELECT p.*, " +
            "       (CASE WHEN (SELECT quantity FROM cart  c WHERE p.itemCode = c.itemCode)  IS NOT NULL " +
            "           THEN (SELECT quantity FROM cart  c WHERE p.itemCode = c.itemCode) " +
            "           ELSE 0 END) AS amount  " +
            "FROM Product p, ProductPageFilter f " +
            "   WHERE " +
            "       Case :subCatCode " +
            "           WHEN '' THEN categoryCode = :catCode" +
            "           ELSE  categoryCode = :catCode AND subCategoryCode = :subCatCode END" +
            "       AND price >= f.minRange AND price <= f.maxRange " +
            "   ORDER BY " +
            "       CASE :orderBy WHEN 'name' THEN p.name END," +
            "       CASE :orderBy WHEN 'price ASC' THEN p.price END ASC," +
            "       CASE :orderBy WHEN 'price DESC' THEN p.price END DESC")
    List<ProductViewData> getProductData(String catCode, String subCatCode, String orderBy);

    @Query("SELECT p.*, " +
            "       (CASE WHEN (SELECT quantity FROM cart  c WHERE p.itemCode = c.itemCode)  IS NOT NULL " +
            "           THEN (SELECT quantity FROM cart  c WHERE p.itemCode = c.itemCode) " +
            "           ELSE 0 END) AS amount  " +
            "FROM Product p, ProductPageFilter f " +
            "   WHERE itemCode != :itemCode and categoryCode = :categoryCode " +
            "   ORDER BY name")
    List<ProductViewData> getOtherProduct(String itemCode, String categoryCode);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertProduct(Product product);

    @Query("INSERT INTO ProductPageFilter " +
            "SELECT 0, 0, 0, 'name', 'GRID_SHOW' " +
            "WHERE NOT EXISTS (SELECT 1 FROM ProductPageFilter WHERE id = 0)")
    void initProductFilter();

    @Query("UPDATE ProductPageFilter SET minRange = 0, maxRange = 1000000")
    void initProductFilterRanges();

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

    @Query("SELECT * FROM AppSetting")
    AppSetting getSetting();

    @Query("INSERT INTO appsetting SELECT 0,'no_user','default' WHERE NOT EXISTS (SELECT 1 FROM AppSetting WHERE id = 0 )")
    void initSetting();

    @Query("SELECT currentUserCode FROM AppSetting")
    String getUserCode();

    @Query("SELECT u.password FROM User u,AppSetting s where u.userCode = s.currentUserCode")
    String getPassword();

    @Query("UPDATE User SET password = :newPassword WHERE userCode = (SELECT currentUserCode FROM AppSetting)")
    void changePassword(String newPassword);


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertCart(Cart cart);

    @Delete
    void deleteCart(Cart productCode);

    @Query("SELECT COUNT(itemCode) FROM cart")
    LiveData<Integer> getAmount();

    @Query("SELECT p.*, c.quantity  AS amount  " +
            " FROM Product p, Cart c " +
            " WHERE p.itemCode  = c.itemCode " +
            " ORDER BY name  ")
    LiveData<List<ProductViewData>> getCartData();

    @Query("SELECT p.*, c.quantity  AS amount  " +
            " FROM Product p, Cart c " +
            " WHERE p.itemCode  = c.itemCode and favState = 1 " +
            " ORDER BY name  ")
    LiveData<List<ProductViewData>> getFavoriteData();

    @Query("SELECT SUM(p.price * c.quantity) FROM CART c, Product p where c.itemCode = p.itemCode")
    LiveData<List<Integer>> getCartTotal();

    @Query("Update AppSetting set currentUserCode = :noUser")
    void setToNoUser(String noUser);

    @Query("SELECT u.* FROM User u, AppSetting s WHERE s.currentUserCode = u.userCode")
    User getAppUser();

    @Query("Update User SET firstName = :firstName, lastName = :lastName, email = :email " +
            " WHERE userCode = (SELECT currentUserCode FROM AppSetting)")
    void setUserProfile(String firstName, String lastName, String email);

    @Query("Update Product SET favState = :favState WHERE itemCode = :itemCode")
    void updateProductFavState(String itemCode, Integer favState);

    @Query("SELECT favState FROM Product WHERE itemCode = :itemCode")
    Integer getFavState(String itemCode);

}
