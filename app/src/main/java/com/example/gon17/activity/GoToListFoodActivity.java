package com.example.gon17.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.gon17.R;
import com.example.gon17.adapter.FoodItemAdapter;
import com.example.gon17.model.FoodItem;

import java.util.ArrayList;
import java.util.List;

public class GoToListFoodActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<FoodItem> foodItemList;
    private FoodItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_go_to_list_food);

        initializeVariables();
        setUpList();

        adapter.setFoodItemList(foodItemList);
        recyclerView.setAdapter(adapter);

    }

    private void setUpList(){
        foodItemList.add(new FoodItem("Fried Chicken 01","Ngon 01",R.drawable.fried_chicken_01,80000));
        foodItemList.add(new FoodItem("Fried Chicken 02","Ngon 02",R.drawable.fried_chicken_02,60000));
        foodItemList.add(new FoodItem("Fried Chicken 03","Ngon 03",R.drawable.fried_chicken_03,50000));
        foodItemList.add(new FoodItem("Fried Chicken 04","Ngon 04",R.drawable.fried_chicken_04,90000));
        foodItemList.add(new FoodItem("Fried Chicken 05","Ngon 05",R.drawable.fried_chicken_05,100000));

    }

    private void initializeVariables(){


        foodItemList = new ArrayList<>();
        recyclerView = findViewById(R.id.listFoodRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));


        adapter = new FoodItemAdapter();
    }
}