package com.example.gon17.activity.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gon17.R;
import com.example.gon17.model.User;

public class HomeActivity extends AppCompatActivity {

    private TextView lblWelcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        User user = (User)getIntent().getSerializableExtra("user");

        lblWelcome = findViewById(R.id.lblWelcome);
        lblWelcome.setText("Welcome "+user.getFullName());
    }
}
