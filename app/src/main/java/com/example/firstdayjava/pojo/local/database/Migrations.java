package com.example.firstdayjava.pojo.local.database;

import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.firstdayjava.pojo.local.entities.setting.AppSetting;
import com.example.firstdayjava.pojo.local.entities.setting.ProductPageFilter;

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

    static final Migration MIGRATION_5_6 = new Migration(5, 6) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE Product (id INTEGER PRIMARY KEY AUTOINCREMENT ," +
                    "    name TEXT," +
                    "    price INTEGER," +
                    "    description TEXT," +
                    "    currencyCode TEXT," +
                    "    descriptionF TEXT," +
                    "    itemNameF TEXT ," +
                    "    categoryCode TEXT," +
                    "    subCategoryCode TEXT," +
                    "    imageUrl TEXT)");
        }
    };

    static final Migration MIGRATION_6_7 = new Migration(6, 7) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE ProductTemp (itemCode TEXT PRIMARY KEY NOT NULL," +
                    "    name TEXT," +
                    "    price INTEGER," +
                    "    description TEXT," +
                    "    currencyCode TEXT," +
                    "    descriptionF TEXT," +
                    "    itemNameF TEXT ," +
                    "    categoryCode TEXT," +
                    "    subCategoryCode TEXT," +
                    "    imageUrl TEXT)");

            database.execSQL("INSERT INTO ProductTemp " +
                    "SELECT CAST(id AS TEXT), name, price," +
                    " description, currencyCode, descriptionF, itemNameF, categoryCode, subCategoryCode, imageUrl  FROM Product");

            database.execSQL("DROP TABLE Product");
            database.execSQL("ALTER TABLE ProductTemp RENAME TO Product");
        }
    };

    static final Migration MIGRATION_7_8 = new Migration(7, 8) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            String createTableStmt = "CREATE TABLE ProductPageFilter (id INTEGER PRIMARY KEY," +
                    "    minRange INTEGER NOT NULL DEFAULT 0," +
                    "    maxRange INTEGER NOT NULL DEFAULT 0," +
                    "    OrderBy TEXT NOT NULL DEFAULT '" + ProductPageFilter.GRID_SHOW + "'," +
                    "    SortingType TEXT NOT NULL DEFAULT '" + ProductPageFilter.SORT_BY_NAME + "')";

            database.execSQL(createTableStmt);

            database.execSQL("INSERT INTO ProductPageFilter(id) VALUES(0)");
        }
    };

    static final Migration MIGRATION_8_9 = new Migration(8, 9) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE SubCategory (code TEXT PRIMARY KEY NOT NULL," +
                    "    name TEXT ," +
                    "    imageUrl TEXT )");

        }
    };

    static final Migration MIGRATION_9_10 = new Migration(9, 10) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE SubCategory ADD COLUMN categoryCode TEXT NOT NULL DEFAULT '0o'");
        }
    };

    static final Migration MIGRATION_10_11 = new Migration(10, 11) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE Cart (itemCode TEXT PRIMARY KEY NOT NULL," +
                    "    userCode TEXT," +
                    "    quantity INTEGER)");

            database.execSQL("ALTER TABLE User ADD COLUMN userCode TEXT NOT NULL DEFAULT '0'");
        }
    };

    static final Migration MIGRATION_11_12 = new Migration(11, 12) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE AppSetting (id INTEGER PRIMARY KEY DEFAULT 0," +
                    "    currentUserCode TEXT DEFAULT '" + AppSetting.NO_USER + "'," +
                    "    language TEXT NOT NULL DEFAULT '" + AppSetting.DEFAULT + "')");

            database.execSQL("INSERT INTO AppSetting DEFAULT VALUES");
        }
    };

    static final Migration MIGRATION_12_13 = new Migration(12, 13) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE Product ADD COLUMN favState INTEGER DEFAULT 0");
        }
    };
}
