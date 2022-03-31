package com.example.firstdayjava.pojo.local.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.firstdayjava.pojo.local.entities.Cart;
import com.example.firstdayjava.pojo.local.entities.Category;
import com.example.firstdayjava.pojo.local.entities.Product;
import com.example.firstdayjava.pojo.local.entities.SubCategory;
import com.example.firstdayjava.pojo.local.entities.User;
import com.example.firstdayjava.pojo.local.entities.setting.AppSetting;
import com.example.firstdayjava.pojo.local.entities.setting.ProductPageFilter;

@Database(entities = {User.class, Category.class, Product.class, ProductPageFilter.class, SubCategory.class, Cart.class, AppSetting.class}, version = 13, exportSchema = false)
@TypeConverters({AppDBConvertor.class})
public abstract class AppDatabase extends RoomDatabase {

    public abstract AppDao appDao();

    public static AppDatabase INSTENC = null;

    public static AppDatabase getDatabase(Context context) {
        AppDatabase temp = INSTENC;

        if (temp != null) {
            return temp;
        }
        return Room.databaseBuilder(
                context,
                AppDatabase.class,
                "firstDayDB"
        ).addMigrations(Migrations.MIGRATION_1_2).
                addMigrations(Migrations.MIGRATION_2_3).
                addMigrations(Migrations.MIGRATION_3_4).
                addMigrations(Migrations.MIGRATION_4_5).
                addMigrations(Migrations.MIGRATION_5_6).
                addMigrations(Migrations.MIGRATION_6_7).
                addMigrations(Migrations.MIGRATION_7_8).
                addMigrations(Migrations.MIGRATION_8_9).
                addMigrations(Migrations.MIGRATION_9_10).
                addMigrations(Migrations.MIGRATION_10_11).
                addMigrations(Migrations.MIGRATION_11_12).
                allowMainThreadQueries().build();
    }


}
