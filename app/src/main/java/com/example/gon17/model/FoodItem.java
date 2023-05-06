package com.example.gon17.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class FoodItem implements Parcelable {



    private String foodName,foodDecription;
    private int foodImage;
    private double foodPrice;

    public FoodItem(String foodName, String foodDecription, int foodImage, double foodPrice) {
        this.foodName = foodName;
        this.foodDecription = foodDecription;
        this.foodImage = foodImage;
        this.foodPrice = foodPrice;
    }

    protected FoodItem(Parcel in) {
        foodName = in.readString();
        foodDecription = in.readString();
        foodImage = in.readInt();
        foodPrice = in.readDouble();
    }

    public static final Creator<FoodItem> CREATOR = new Creator<FoodItem>() {
        @Override
        public FoodItem createFromParcel(Parcel in) {
            return new FoodItem(in);
        }

        @Override
        public FoodItem[] newArray(int size) {
            return new FoodItem[size];
        }
    };

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodDecription() {
        return foodDecription;
    }

    public void setFoodDecription(String foodDecription) {
        this.foodDecription = foodDecription;
    }

    public int getFoodImage() {
        return foodImage;
    }

    public void setFoodImage(int foodImage) {
        this.foodImage = foodImage;
    }

    public double getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(double foodPrice) {
        this.foodPrice = foodPrice;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(foodName);
        parcel.writeString(foodDecription);
        parcel.writeInt(foodImage);
        parcel.writeDouble(foodPrice);
    }
}
