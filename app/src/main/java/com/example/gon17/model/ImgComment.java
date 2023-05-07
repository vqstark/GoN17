package com.example.gon17.model;

import java.io.Serializable;

public class ImgComment implements Serializable {
    private int id;
    private String imgPath;
    private Comment comment;

    public ImgComment() {
    }

    public ImgComment(int id, String imgPath, Comment comment) {
        this.id = id;
        this.imgPath = imgPath;
        this.comment = comment;
    }

    public ImgComment(String imgPath, Comment comment) {
        this.imgPath = imgPath;
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }
}
