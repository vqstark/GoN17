package com.example.gon17.activity.home;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gon17.R;
import com.example.gon17.model.User;

import java.io.IOException;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private TextView lblWelcome;
    private TextView lblPosDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportActionBar().hide();

        // Get location of user
        User user = (User)getIntent().getSerializableExtra("user");
        String[] latlongPos = user.getAddress().split(";");
        String theAddress;
        Geocoder geocoder = new Geocoder(HomeActivity.this);
        try {
            List<Address> addresses = geocoder.getFromLocation(Double.parseDouble(latlongPos[0]),
                                                                Double.parseDouble(latlongPos[1]), 1);
            StringBuilder sb = new StringBuilder();
            if (addresses.size() > 0) {
                Address address = addresses.get(0);
                int n = address.getMaxAddressLineIndex();
                for (int i=0; i<=n; i++) {
                    if (i!=0)
                        sb.append(", ");
                    sb.append(address.getAddressLine(i));
                }
                theAddress = sb.toString();
            } else {
                theAddress = "Thanh Xuân Nam, Hà Nội";
            }
        } catch (IOException e) {
            theAddress = "Thanh Xuân Nam, Hà Nội";
        }
        lblPosDetails = findViewById(R.id.lblPosDetails);
        lblPosDetails.setText(theAddress);

        lblWelcome = findViewById(R.id.lblWelcome);
        lblWelcome.setText("Welcome "+user.getFullName());
    }
}
