package com.example.gon17.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.gon17.model.Comment;
import com.example.gon17.model.ImgComment;

public class CommentDB extends DBConnection{
    public CommentDB(Context context) {
        super(context);
    }

    public long addComment(Comment c) {
        ContentValues values = new ContentValues();
        values.put("rating", c.getRating());
        values.put("content", c.getContent());
        values.put("userID", c.getUser().getId());
        values.put("foodID", c.getFood().getId());
        SQLiteDatabase db = getWritableDatabase();
        return db.insert("comments", null, values);
    }

    public long addImgComment(ImgComment i) {
        ContentValues values = new ContentValues();
        values.put("imgPath", i.getImgPath());
        values.put("commentID", i.getComment().getId());
        SQLiteDatabase db = getWritableDatabase();
        return db.insert("imgs_comment", null, values);
    }
}
