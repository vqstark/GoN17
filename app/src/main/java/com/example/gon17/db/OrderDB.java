package com.example.gon17.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.gon17.model.Order;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OrderDB extends DBConnection{
    public OrderDB(Context context) {
        super(context);
    }
    public List<Order> getAll() {
        List<Order> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor rs = sqLiteDatabase.query("orders",
                null, null, null,
                null, null, null);
        while ((rs != null) && (rs.moveToNext())) {
            int id= rs.getInt(0);
            String date = rs.getString(1);
            String status = rs.getString(2);
            int isPaid = rs.getInt(3);
            String note = rs.getString(4);
            double total = rs.getDouble(5);
            list.add(new Order(id, date ,status, isPaid, note, total));
        }
        return list;
    }
}
