package com.example.gon17.activity.home.fragment;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.gon17.R;
import com.example.gon17.activity.GoToListFoodActivity;
import com.example.gon17.activity.home.HomeActivity;
import com.example.gon17.model.User;

import java.io.IOException;
import java.util.List;

public class HomeFragment extends Fragment {

    private TextView lblWelcome;
    private TextView lblPosDetails;


    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        Bundle bundle = getArguments();
        User user = (User)bundle.getSerializable("user");
        String[] latlongPos = user.getAddress().split(";");
        String theAddress;
        Geocoder geocoder = new Geocoder(getContext());
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
        lblPosDetails = view.findViewById(R.id.lblPosDetails);
        lblPosDetails.setText(theAddress);

        lblWelcome = view.findViewById(R.id.lblWelcome);
        lblWelcome.setText("Welcome "+user.getFullName());


        Button btnGoToListFood = view.findViewById(R.id.btnGoToListFood);
        btnGoToListFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), GoToListFoodActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}