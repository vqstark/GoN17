package com.example.gon17.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.gon17.dao.CartDAO;
import com.example.gon17.model.FoodCart;


@Database(entities = {FoodCart.class},version = 2)
public abstract class CartDatabase extends RoomDatabase {

    public abstract CartDAO cartDAO();
    private static CartDatabase instance;


    public static synchronized CartDatabase getInstance(Context context){

        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), CartDatabase.class, "FoodDatabase").fallbackToDestructiveMigration().build();
        }
        return instance;
    }

}
