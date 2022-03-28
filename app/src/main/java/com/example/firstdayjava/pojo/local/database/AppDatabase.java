package com.example.firstdayjava.pojo.local.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.firstdayjava.pojo.local.entities.Category;
import com.example.firstdayjava.pojo.local.entities.User;

@Database(entities = {User.class, Category.class}, version = 5, exportSchema = false)
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
                allowMainThreadQueries().build();
    }


}
