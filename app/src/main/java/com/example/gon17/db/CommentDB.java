package com.example.gon17.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.gon17.model.Comment;
import com.example.gon17.model.CommentDTO;
import com.example.gon17.model.Food;
import com.example.gon17.model.ImgComment;
import com.example.gon17.model.Order;

import java.util.ArrayList;
import java.util.List;

public class CommentDB extends DBConnection{
    public CommentDB(Context context) {
        super(context);
    }

    public long addComment(Comment c, byte[] img1, byte[] img2, byte[] img3) {
        ContentValues values = new ContentValues();
        values.put("rating", c.getRating());
        values.put("content", c.getContent());
        values.put("userID", c.getUser().getId());
        values.put("foodID", c.getFood().getId());
        SQLiteDatabase db = getWritableDatabase();
        long status = db.insert("comments", null, values);
        if(status!=-1){
            if(img1!=null && img1.length!=0){
                ContentValues values2 = new ContentValues();
                values2.put("imgPath", img1);
                values2.put("commentID", status);
                db.insert("imgs_comment", null, values2);
            }

            if(img2!=null && img2.length!=0){
                ContentValues values3 = new ContentValues();
                values3.put("imgPath", img2);
                values3.put("commentID", status);
                db.insert("imgs_comment", null, values3);
            }

            if(img3!=null && img3.length!=0 ){
                ContentValues values4 = new ContentValues();
                values4.put("imgPath", img3);
                values4.put("commentID", status);
                db.insert("imgs_comment", null, values4);
            }
            return 1;
        }
        return -1;
    }

    public List<CommentDTO> getCommentsByFood(int foodID){
        List<CommentDTO> ls = new ArrayList<>();

        SQLiteDatabase db=getReadableDatabase();

        //tạo con trỏ đọc bảng dữ liệu sản phẩm
        String sql = "Select * from comment where foodID = ?";
        String[] agrs={""+foodID+""};
        Cursor rs=db.rawQuery(sql,agrs);
        while(rs!=null && rs.moveToNext()){
            ls.add(new CommentDTO(rs.getInt(0),rs.getInt(1),rs.getString(2),
                    rs.getInt(3),rs.getInt(4)));
        }
        rs.close();
        return ls;
    }

    public long addImgComment(ImgComment i) {
        ContentValues values = new ContentValues();
        values.put("imgPath", i.getImgPath());
        values.put("commentID", i.getComment().getId());
        SQLiteDatabase db = getWritableDatabase();
        return db.insert("imgs_comment", null, values);
    }
}
