package com.example.gon17.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.gon17.model.User;

public class UserDB extends DBConnection{

    public UserDB(Context context) {
        super(context);
    }

    public User selectUserByPhone(String phoneNumber){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor rs = sqLiteDatabase.rawQuery("select * from users where phoneNumber = ?",new String[]{phoneNumber});
        User user = null;
        if (rs != null && rs.moveToFirst()) {
            user = new User();
            user.setId(rs.getInt(0));
            user.setPhoneNumber((rs.getString(1)));
            user.setPassword((rs.getString(2)));
            user.setFullName((rs.getString(3)));
            user.setAge((rs.getInt(4)));
            user.setAddress((rs.getString(5)));
            user.setRole((rs.getString(6)));
            rs.close();
        }
        return user;
    }

    public int checkUser(String phoneNumber, String password){
        int status = -1;
        User user = selectUserByPhone(phoneNumber);
        if(user==null){
            return status;
        }else {
            if(password.equals(user.getPassword())){
                status = 1;
            }else{
                status = 0;
            }
        }
        return status;
    }

    public long addUser(User user){
        User u = selectUserByPhone(user.getPhoneNumber());
        if(u==null) {
            ContentValues values = new ContentValues();
            values.put("phoneNumber", user.getPhoneNumber());
            values.put("password", user.getPassword());
            values.put("fullName", user.getFullName());
            values.put("age", user.getAge());
            values.put("address", user.getAddress());
            values.put("role", "ROLE_USER");
            SQLiteDatabase sqLiteDatabase = getWritableDatabase();
            return sqLiteDatabase.insert("users", null, values);
        }
        return -1;
    }

    public int updateAddress(User user, String location){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("address",location);
        String whereClause = "phoneNumber = ?";
        String[] whereArgs = {user.getPhoneNumber()};
        return sqLiteDatabase.update("users", values, whereClause, whereArgs);
    }
}
