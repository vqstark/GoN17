package com.example.gon17.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.gon17.model.FoodItem;

import java.util.ArrayList;
import java.util.List;

public class FoodItemDAO extends DBConnection {
    private SQLiteDatabase db;

    public FoodItemDAO(Context context) {
        super(context);
        db = getReadableDatabase();
    }

    // Retrieve all food items
    public List<FoodItem> getAllFoodItems() {
        List<FoodItem> foodItemList = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT * FROM food", null);
        if (cursor.moveToFirst()) {
            int nameIndex = cursor.getColumnIndex("name");
            int descriptionIndex = cursor.getColumnIndex("description");
            int priceIndex = cursor.getColumnIndex("price");
            int imageIndex = cursor.getColumnIndex("image");

            do {
                String foodName = cursor.getString(nameIndex);
                String foodDescription = cursor.getString(descriptionIndex);
                double foodPrice = cursor.getDouble(priceIndex);
                byte[] foodImage = cursor.getBlob(imageIndex);

                FoodItem foodItem = new FoodItem(foodName, foodDescription, foodImage, foodPrice);
                foodItemList.add(foodItem);
            } while (cursor.moveToNext());
        }
        cursor.close();

        return foodItemList;
    }
}
