package com.example.gon17.activity.home.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.gon17.MainActivity;
import com.example.gon17.R;
import com.example.gon17.activity.account.ChangePasswordActivity;
import com.example.gon17.activity.account.UserInfoActivity;
import com.example.gon17.activity.auth.LoginActivity;
import com.example.gon17.activity.utils.LocateActivity;
import com.example.gon17.model.User;

public class AccountFragment extends Fragment {

    private TextView lblName;

    private LinearLayout linearUserInfo, linearPayment, linearLocation, linearChangePassword, linearLogout;
    public AccountFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_account, container, false);
        lblName = view.findViewById(R.id.lblName);

        linearUserInfo = view.findViewById(R.id.linearUserInfo);
        linearPayment = view.findViewById(R.id.linearPayment);
        linearLocation = view.findViewById(R.id.linearLocation);
        linearChangePassword = view.findViewById(R.id.linearChangePassword);
        linearLogout = view.findViewById(R.id.linearLogout);

        Bundle bundle = getArguments();
        User user = (User)bundle.getSerializable("user");
        lblName.setText(user.getFullName());

        linearUserInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), UserInfoActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });
        linearPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        linearLocation.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), LocateActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        }));
        linearChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ChangePasswordActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });
        linearLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), LoginActivity.class));
            }
        });
        return view;
    }
}