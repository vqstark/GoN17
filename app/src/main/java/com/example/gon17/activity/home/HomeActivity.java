package com.example.gon17.activity.home;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.gon17.R;
import com.example.gon17.activity.home.fragment.AccountFragment;
import com.example.gon17.activity.home.fragment.CartFragment;
import com.example.gon17.activity.home.fragment.HomeFragment;
import com.example.gon17.activity.home.fragment.OrderFragment;
import com.example.gon17.model.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class HomeActivity extends AppCompatActivity {
    private HomeFragment homeFragment;
    private CartFragment cartFragment;
    private OrderFragment orderFragment;
    private AccountFragment accountFragment;

    private FrameLayout frameFrag;
    private BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        frameFrag = findViewById(R.id.frameFrag);
        bottomNav = findViewById(R.id.bottomNav);

        homeFragment = new HomeFragment();
        cartFragment = new CartFragment();
        orderFragment = new OrderFragment();
        accountFragment = new AccountFragment();

        Bundle bundle = new Bundle();
        bundle.putSerializable("user", (User)getIntent().getSerializableExtra("user"));
        homeFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.frameFrag, homeFragment).commit();

        bottomNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.itemHome:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frameFrag, homeFragment).commit();
                        return true;
                    case R.id.itemCart:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frameFrag, cartFragment).commit();
                        return true;
                    case R.id.itemOrder:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frameFrag, orderFragment).commit();
                        return true;
                    case R.id.itemUser:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frameFrag, accountFragment).commit();
                        return true;

                }
                return false;
            }
        });
    }
}
