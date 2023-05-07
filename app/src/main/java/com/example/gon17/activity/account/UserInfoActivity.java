package com.example.gon17.activity.account;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.gon17.R;
import com.example.gon17.activity.auth.LoginActivity;
import com.example.gon17.activity.home.HomeActivity;
import com.example.gon17.activity.home.fragment.AccountFragment;
import com.example.gon17.model.User;

public class UserInfoActivity extends AppCompatActivity {

    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_user_info);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Thông tin tài khoản");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.show();

        this.user = (User)getIntent().getSerializableExtra("user");

        Button btnSave = findViewById(R.id.btnSaveChanges);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Lưu thành công", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();

                // Call account fragment
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                intent.putExtra("user",this.user);
                intent.putExtra("fragment_name", "ACCOUNT_FRAGMENT");
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
