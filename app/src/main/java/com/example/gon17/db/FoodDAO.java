package com.example.gon17.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.gon17.model.Food;

import java.util.ArrayList;
import java.util.List;

public class FoodDAO {
    private SQLiteDatabase db;
    private OpenDBHelper dbHelper;
    private Context context;

    public FoodDAO(Context context) {
        this.context = context;
        dbHelper = new OpenDBHelper(context); //thực thi tạo database
        db = dbHelper.getWritableDatabase(); // cho phép ghi dữ liệu vào database
    }

    // thêm dữ liệu
    public int InsertFood(Food food) {
        ContentValues values = new ContentValues(); // tạo đối tượng chứa dữ liệu

        // đưa dữ liệu vào đối tượng chứa
        values.put("name", food.getFoodName());
        values.put("price",food.getPrice());
        values.put("description", food.getDesc());
        values.put("image", food.getImage());

        //thực thi insert
        long kq = db.insert("food", null, values);

        //kiểm tra kết quả
        if(kq <= 0) {
            return -1;
        }
        return 1;
    }

    // hiển thị dữ liệu
    public List<Food> getAllFoodToString() {
        List<Food> ls = new ArrayList<>();

        //tạo con trỏ đọc bảng dữ liệu sản phẩm
        Cursor c = db.rawQuery("Select * from food", null);
        c.moveToFirst(); // di chuyển con trỏ về bản ghi đầu tiên

        while(c.isAfterLast() == false) {

            int id = (c.getInt(0));
            String name = (c.getString(1));
            double price = (c.getDouble(2));
            String desc = (c.getString(3));
            byte[] image = c.getBlob(4);
            Food f = new Food(id,name, price, desc, image);
            ls.add(f);
            c.moveToNext(); // di chuyển đến bản ghi tiếp theo
        }
        c.close();
        return ls;
    }

    // sửa dữ liệu
    public int UpdateFood(Food food) {
        ContentValues values = new ContentValues(); // tạo đối tượng chứa dữ liệu

        // đưa dữ liệu vào đối tượng chứa
        values.put("name", food.getFoodName());
        values.put("price",food.getPrice());
        values.put("description", food.getDesc());
        values.put("image", food.getImage());

        //thực thi update
        long kq = db.update("food", values, "id=?", new String[]{food.getId() + ""});

        //kiểm tra kết quả
        if(kq <= 0) {
            return -1;
        }
        return 1;
    }

    // xóa dữ liệu
    public int DeleteFood(int id) {
        int kq = db.delete("food", "id=?", new String[]{id + ""});
        if(kq <= 0) {
            return -1;
        }
        return 1;
    }

}