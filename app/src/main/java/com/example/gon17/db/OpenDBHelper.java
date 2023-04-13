package com.example.gon17.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.io.IOException;

public class OpenDBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_PATH = "./database/";
    private static final String DATABASE_NAME = "gon17.db";
    private static final int DATABASE_VERSION = 1;

    private static final String SQL_CREATE_RESTAURANTS_TABLE =
            "create table if not exists restaurants (id integer primary key autoincrement," +
                    "name text not null," +
                    "phoneNumber text not null," +
                    "address text not null)";
    private static final String SQL_CREATE_USERS_TABLE =
            "create table if not exists users (id integer primary key autoincrement," +
                    "phoneNumber text not null unique," +
                    "password text not null," +
                    "fullName text not null," +
                    "age integer not null," +
                    "address text not null," +
                    "role text not null)";

    private static final String SQL_CREATE_FOOD_TABLE =
            "create table if not exists food (id integer primary key autoincrement," +
                    "name text not null," +
                    "price double not null," +
                    "description text not null)";

    private static final String SQL_CREATE_ORDERS_TABLE =
            "create table if not exists orders (id integer primary key autoincrement," +
                    "date text not null," +
                    "status text not null," +
                    "isPaid bit not null," +
                    "note text," +
                    "total double not null)";

    private static final String SQL_CREATE_ORDERED_FOOD_TABLE =
            "create table if not exists ordered_food (id integer primary key autoincrement," +
                    "quantity integer not null," +
                    "orderID integer," +
                    "foodID integer," +
                    "foreign key(orderID) references orders(id)," +
                    "foreign key(foodID) references food(id))";

    private static final String SQL_CREATE_COMMENTS_TABLE =
            "create table if not exists comments (id integer primary key autoincrement," +
                    "rating integer not null," +
                    "content text not null," +
                    "userID integer," +
                    "foodID integer," +
                    "foreign key(userID) references users(id)," +
                    "foreign key(foodID) references food(id))";

    private static final String SQL_CREATE_IMGS_COMMENT_TABLE =
            "create table if not exists imgs_comment (id integer primary key autoincrement," +
                    "imgPath text not null," +
                    "commentID integer," +
                    "foreign key(commentID) references comments(id))";

    private static final String SQL_CREATE_IMGS_FOOD_TABLE =
            "create table if not exists imgs_food (id integer primary key autoincrement," +
                    "imgPath text not null," +
                    "foodID integer," +
                    "foreign key(foodID) references food(id))";

    private static final String SQL_CREATE_IMGS_RESTAURANT_TABLE =
            "create table if not exists imgs_restaurant (id integer primary key autoincrement," +
                    "imgPath text not null," +
                    "restaurantID integer," +
                    "foreign key(restaurantID) references restaurants(id))";

    private static final String SQL_INSERT_RESTAURANTS_TABLE =
            "insert into restaurants(name, phoneNumber, address) values " +
                    "('Cơm tấm Ole', '0986666888', 'Ngõ 18, phố Triều Khúc, Thanh Xuân Nam, Hà Nội')";

    private static final String SQL_INSERT_USERS_TABLE =
            "insert into users(phoneNumber, password, fullName, age, address, role) values" +
                    "('admin', 'admin', 'ADMIN', 18, 'Hà Nội', 'ROLE_ADMIN')";

    public OpenDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_RESTAURANTS_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_USERS_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_FOOD_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_ORDERS_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_ORDERED_FOOD_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_COMMENTS_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_IMGS_COMMENT_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_IMGS_FOOD_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_IMGS_RESTAURANT_TABLE);

        sqLiteDatabase.execSQL(SQL_INSERT_RESTAURANTS_TABLE);
        sqLiteDatabase.execSQL(SQL_INSERT_USERS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
