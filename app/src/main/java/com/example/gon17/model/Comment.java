package com.example.gon17.model;

import java.io.Serializable;

public class Comment implements Serializable {
    private int id;
    private int rating;
    private String content;
    private User user;
    private Food food;

    public Comment() {
    }

    public Comment(int id, int rating, String content, User user, Food food) {
        this.id = id;
        this.rating = rating;
        this.content = content;
        this.user = user;
        this.food = food;
    }

    public Comment(int rating, String content, User user, Food food) {
        this.rating = rating;
        this.content = content;
        this.user = user;
        this.food = food;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }
}
