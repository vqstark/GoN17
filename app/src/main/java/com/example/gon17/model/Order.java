package com.example.gon17.model;

import java.io.Serializable;
import java.util.List;

public class Order implements Serializable {
    private int id;
    private String date, status;
    private int isPaid;
    private String note;
    private double total;

    public Order() {
    }

    public Order(int id, String date, String status, int isPaid, String note, double total) {
        this.id = id;
        this.date = date;
        this.status = status;
        this.isPaid = isPaid;
        this.note = note;
        this.total = total;
    }

    public Order(String date, String status, int isPaid, String note, double total) {
        this.date = date;
        this.status = status;
        this.isPaid = isPaid;
        this.note = note;
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int isPaid() {
        return isPaid;
    }

    public void setPaid(int paid) {
        isPaid = paid;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
