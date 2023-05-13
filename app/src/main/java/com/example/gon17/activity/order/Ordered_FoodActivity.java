package com.example.gon17.activity.order;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.example.gon17.R;
import com.example.gon17.activity.home.fragment.Ordered_FoodFragment;
import com.example.gon17.model.Order;
import com.example.gon17.model.User;

public class Ordered_FoodActivity extends AppCompatActivity {
    private Ordered_FoodFragment ordered_foodFragment;
    private FrameLayout frameFrag;
    private User user;
    private Order order;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ordered_food);

        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("user");
        order = (Order) intent.getSerializableExtra("order");
        frameFrag = findViewById(R.id.frameFrag);
        ordered_foodFragment = new Ordered_FoodFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("user", user);
        bundle.putSerializable("order", order);
        ordered_foodFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.frameFrag, ordered_foodFragment).commit();
    }
}