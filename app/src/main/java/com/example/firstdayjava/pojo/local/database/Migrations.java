package com.example.firstdayjava.pojo.local.database;

import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

public class Migrations {
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

    static final Migration MIGRATION_4_5 = new Migration(4, 5) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE Users RENAME TO User");

            database.execSQL("CREATE TABLE Category (categoryName TEXT," +
                    "    imageUrl TEXT," +
                    "    categoryCode TEXT PRIMARY KEY NOT NULL)");
        }
    };
}
