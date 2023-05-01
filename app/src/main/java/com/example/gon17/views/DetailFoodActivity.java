package com.example.gon17.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gon17.R;
import com.example.gon17.model.FoodItem;

public class DetailFoodActivity extends AppCompatActivity {

    private ImageView foodImageView;
    private TextView foodNameTV, foodDescriptionTV, foodPriceTV;
    private AppCompatButton addToCartBtn;
    private FoodItem food;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_food);

        food = getIntent().getParcelableExtra("foodItem");
        initializeVariables();
        if(food != null){
            setDataToWidgets();
        }


    }

    private void setDataToWidgets() {
        foodNameTV.setText(food.getFoodName());
        foodDescriptionTV.setText(food.getFoodDecription());
        foodPriceTV.setText(String.valueOf(food.getFoodPrice()));
        foodImageView.setImageResource(food.getFoodImage());
    }

    private void initializeVariables() {


        foodImageView = findViewById(R.id.detailActivityFoodIV);
        foodNameTV = findViewById(R.id.detailActivityFoodNameTv);
        foodDescriptionTV = findViewById(R.id.detailActivityFoodDescriptionTv);
        foodPriceTV = findViewById(R.id.detailActivityFoodPriceTv);
        addToCartBtn = findViewById(R.id.detailActivityAddToCartBtn);

    }
}