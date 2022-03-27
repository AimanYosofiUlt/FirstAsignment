package com.example.firstdayjava.pojo.dbs.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.firstdayjava.pojo.dbs.models.Users;

@Database(entities = {Users.class}, version = 4, exportSchema = false)
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
        ).addMigrations(MIGRATION_1_2).
                addMigrations(MIGRATION_2_3).
                addMigrations(MIGRATION_3_4).
                allowMainThreadQueries().build();
    }

    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE Users ADD COLUMN encryptKey TEXT");
        }
    };

    static final Migration MIGRATION_2_3 = new Migration(2, 3) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {

            database.execSQL("CREATE TABLE UsersTemp (firstName TEXT," +
                    "    lastName TEXT," +
                    "    email TEXT PRIMARY KEY NOT NULL," +
                    "    phone TEXT," +
                    "    birthDate TEXT," +
                    "    location TEXT," +
                    "    password TEXT," +
                    "    encryptKey TEXT)");

            database.execSQL("INSERT INTO UsersTemp " +
                    "SELECT firstName, lastName, email," +
                    " phone, birthDate, location, password, encryptKey   FROM Users");

            database.execSQL("DROP TABLE Users");
            database.execSQL("ALTER TABLE UsersTemp RENAME TO Users");
        }
    };

    static final Migration MIGRATION_3_4 = new Migration(3, 4) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {

            database.execSQL("CREATE TABLE UsersTemp (firstName TEXT," +
                    "    lastName TEXT," +
                    "    email TEXT PRIMARY KEY NOT NULL," +
                    "    phone TEXT," +
                    "    password TEXT)");

            database.execSQL("INSERT INTO UsersTemp " +
                    "SELECT firstName, lastName, email," +
                    " phone,  password   FROM  Users");

            database.execSQL("DROP TABLE Users");
            database.execSQL("ALTER TABLE UsersTemp RENAME TO Users");
        }
    };
}
