package com.example.gon17.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gon17.R;
import com.example.gon17.model.FoodCart;
import com.example.gon17.model.FoodItem;
import com.example.gon17.viewmodel.CartViewModel;

import java.util.ArrayList;
import java.util.List;

public class DetailFoodActivity extends AppCompatActivity {

    private ImageView foodImageView;
    private TextView foodNameTV, foodDescriptionTV, foodPriceTV;
    private AppCompatButton addToCartBtn;
    private FoodItem food;
    private CartViewModel viewModel;
    private List<FoodCart> foodCartList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_food);

        food = getIntent().getParcelableExtra("foodItem");
        initializeVariables();

        viewModel.getAllCartItems().observe(this, new Observer<List<FoodCart>>() {
            @Override
            public void onChanged(List<FoodCart> foodCarts) {
                foodCartList.addAll(foodCarts);
            }
        });

        if(food != null){
            setDataToWidgets();
        }


        addToCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertToRoom();
            }
        });
    }

    private void insertToRoom(){
        FoodCart foodCart = new FoodCart();
        foodCart.setFoodName(food.getFoodName());
        foodCart.setFoodDecription(food.getFoodDecription());
        foodCart.setFoodPrice(food.getFoodPrice());
        foodCart.setFoodImage(food.getFoodImage());


        final int[] quantity = {1};
        final int[] id = new int[1];
        if(!foodCartList.isEmpty()){
            for (int i = 0; i < foodCartList.size(); i++) {
                if (foodCart.getFoodName().equals(foodCartList.get(i).getFoodName())) {
                    quantity[0] = foodCartList.get(i).getQuantity();
                    quantity[0]++;
                    id[0] = foodCartList.get(i).getId();
                }
            }
        }

        if (quantity[0] == 1) {
            foodCart.setQuantity(quantity[0]);
            foodCart.setTotalItemPrice(quantity[0] * foodCart.getFoodPrice());
            viewModel.insertCartItem(foodCart);
        } else {
            viewModel.updateQuantity(id[0], quantity[0]);
            viewModel.updatePrice(id[0], quantity[0] * foodCart.getFoodPrice());
        }
        
        startActivity(new Intent(DetailFoodActivity.this, CartActivity.class));
    }

    private void setDataToWidgets() {
        foodNameTV.setText(food.getFoodName());
        foodDescriptionTV.setText(food.getFoodDecription());
        foodPriceTV.setText("đ" + String.valueOf(food.getFoodPrice()));
        foodImageView.setImageResource(food.getFoodImage());
    }

    private void initializeVariables() {

        foodCartList = new ArrayList<>();
        foodImageView = findViewById(R.id.detailActivityFoodIV);
        foodNameTV = findViewById(R.id.detailActivityFoodNameTv);
        foodDescriptionTV = findViewById(R.id.detailActivityFoodDescriptionTv);
        foodPriceTV = findViewById(R.id.detailActivityFoodPriceTv);
        addToCartBtn = findViewById(R.id.detailActivityAddToCartBtn);

        viewModel = new ViewModelProvider(this).get(CartViewModel.class);

    }
}