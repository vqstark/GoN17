package com.example.gon17.activity.auth;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gon17.R;
import com.example.gon17.activity.home.HomeActivity;
import com.example.gon17.db.UserDB;

public class LoginActivity extends AppCompatActivity {

    private EditText txtUsername, txtPassword;

    private Button btnLogin;

    private UserDB userDB;

    private TextView lblSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        txtUsername = findViewById(R.id.txtUsername);
        txtPassword = findViewById(R.id.txtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        lblSignup = findViewById(R.id.lblSignup);

        userDB = new UserDB(getApplicationContext());

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = txtUsername.getText().toString().trim();
                String password = txtPassword.getText().toString().trim();
                int status = userDB.checkUser(username,password);
                if(status==-1){
                    Toast.makeText(LoginActivity.this, "Số điện thoại này chưa được đăng kí",Toast.LENGTH_LONG).show();
                }else if(status==0){
                    Toast.makeText(LoginActivity.this, "Sai mật khẩu",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(LoginActivity.this, "Đăng nhập thành công",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this, HomeActivity.class).putExtra("user", userDB.selectUserByPhone(username)));
                }
            }
        });

        lblSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });
    }
}
