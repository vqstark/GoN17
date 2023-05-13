package com.example.gon17.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.gon17.model.Food;
import com.example.gon17.model.Order;
import com.example.gon17.model.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderDB extends DBConnection{
    public OrderDB(Context context) {
        super(context);
    }
    public List<Order> getAll(User user){
        List<Order> list=new ArrayList<>();
        String sql="select t.id,t.date,t.status,t.isPaid,t.note,t.total " +
                "from orders t " +
                "where (t.userID=?)";

        SQLiteDatabase st=getReadableDatabase();
        String[] agrs={""+user.getId()+""};
        Cursor rs=st.rawQuery(sql,agrs);
        while(rs!=null && rs.moveToNext()){
            list.add(new Order(rs.getInt(0),rs.getString(1),rs.getString(2),
                                rs.getInt(3),rs.getString(4),rs.getDouble(5), user));
        }
        rs.close();
        return list;
    }

    public Map<Food, Integer> getFoodByOrderID(int orderID){
        System.out.println("=====> orderID: "+orderID);
        Map<Food, Integer> foodList = new HashMap<>();
        String sql="select f.*, o.quantity " +
                "from food f " +
                "join ordered_food o " +
                "on o.foodID = f.id " +
                "where o.orderID = ?";
        SQLiteDatabase st=getReadableDatabase();
        String[] agrs={""+orderID+""};
        Cursor rs=st.rawQuery(sql,agrs);
        while(rs!=null && rs.moveToNext()){
            foodList.put(new Food(rs.getInt(0),rs.getString(1),rs.getDouble(2),
                    rs.getString(3),rs.getBlob(4)), rs.getInt(5));
        }
        rs.close();
        return foodList;
    }
}
