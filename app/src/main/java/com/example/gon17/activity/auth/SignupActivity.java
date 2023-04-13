package com.example.gon17.activity.auth;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.gon17.MainActivity;
import com.example.gon17.R;
import com.example.gon17.activity.home.HomeActivity;
import com.example.gon17.db.UserDB;
import com.example.gon17.model.User;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class SignupActivity extends AppCompatActivity {

    private EditText txtName, txtAge, txtPhone, txtPassword, txtReinput;
    private CheckBox checkBox;
    private Button btnSignup;

    private UserDB userDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        userDB = new UserDB(getApplicationContext());

        txtName = findViewById(R.id.txtName);
        txtAge = findViewById(R.id.txtAge);
        txtPhone = findViewById(R.id.txtPhone);
        txtPassword = findViewById(R.id.txtPassword);
        txtReinput = findViewById(R.id.txtReinput);
        checkBox = findViewById(R.id.checkBox);
        btnSignup = findViewById(R.id.btnSignup);
        btnSignup.setEnabled(false);

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    btnSignup.setEnabled(true);
                } else {
                    btnSignup.setEnabled(false);
                }
            }
        });

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = txtName.getText().toString().trim();
                String age = txtAge.getText().toString().trim();
                String phone = txtPhone.getText().toString().trim();
                String password = txtPassword.getText().toString().trim();
                String repass = txtReinput.getText().toString().trim();

                if(name==null || name.length()==0){
                    txtName.setError("Tên không được để trống");
                    return;
                }
                if(age==null || age.length()==0){
                    txtAge.setError("Tuổi không được để trống");
                    return;
                }
                if(phone==null || phone.length()==0){
                    txtPhone.setError("Số điện thoại không được để trống");
                    return;
                }
                if(password==null || password.length()==0){
                    txtPassword.setError("Mật khẩu không được để trống");
                    return;
                }
                if(repass==null || repass.length()==0){
                    txtReinput.setError("Không được để trống trường này");
                    return;
                }

                int num_age;
                try{
                    num_age = Integer.parseInt(age);
                }catch (NumberFormatException e){
                    txtAge.setError("Tuổi phải là số nguyên");
                    return;
                }

                if(!password.equals(repass)){
                    txtReinput.setError("Mật khẩu không khớp");
                    return;
                }

                User user = new User();
                user.setPhoneNumber(phone);
                user.setFullName(name);
                user.setPassword(password);
                user.setAge(num_age);
                user.setAddress("Ha Noi");

                if(userDB.addUser(user)!=null){
                    Toast.makeText(SignupActivity.this, "Đăng ký thành công",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(SignupActivity.this, HomeActivity.class).putExtra("user", user));
                }else{
                    Toast.makeText(SignupActivity.this, "Số điện thoại này đã được đăng kí",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
