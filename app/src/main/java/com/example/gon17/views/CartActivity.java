package com.example.gon17.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.gon17.R;
import com.example.gon17.adapter.CartAdapter;
import com.example.gon17.model.FoodCart;
import com.example.gon17.viewmodel.CartViewModel;

import java.util.List;

public class CartActivity extends AppCompatActivity implements CartAdapter.CartClickedListeners {

    private RecyclerView recyclerView;
    private CartViewModel cartViewModel;
    private TextView totalCartPriceTv, textView;
    private AppCompatButton checkoutBtn;
    private CardView cardView;
    private CartAdapter cartAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        initializeVariables();

        cartViewModel.getAllCartItems().observe(this, new Observer<List<FoodCart>>() {
            @Override
            public void onChanged(List<FoodCart> foodCarts) {
                double price = 0;
                cartAdapter.setFoodCartList(foodCarts);
                for (int i=0;i<foodCarts.size();i++){
                    price = price + foodCarts.get(i).getTotalItemPrice();
                }
                totalCartPriceTv.setText(String.valueOf(price));
            }
        });

        checkoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cartViewModel.deleteAllCartItems();
                textView.setVisibility(View.INVISIBLE);
                checkoutBtn.setVisibility(View.INVISIBLE);
                totalCartPriceTv.setVisibility(View.INVISIBLE);
                cardView.setVisibility(View.VISIBLE);
            }
        });
    }

    private void initializeVariables() {

        cartAdapter = new CartAdapter(this);
        textView = findViewById(R.id.textView2);
        cardView = findViewById(R.id.cartActivityCardView);
        totalCartPriceTv = findViewById(R.id.cartActivityTotalPriceTv);
        checkoutBtn = findViewById(R.id.cartActivityCheckoutBtn);
        cartViewModel = new ViewModelProvider(this).get(CartViewModel.class);
        recyclerView = findViewById(R.id.cartRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(cartAdapter);

    }

    @Override
    public void onDeleteClicked(FoodCart foodCart) {
        cartViewModel.deleteCartItem(foodCart);
    }

    @Override
    public void onPlusClicked(FoodCart foodCart) {
        int quantity = foodCart.getQuantity() + 1;
        cartViewModel.updateQuantity(foodCart.getId() , quantity);
        cartViewModel.updatePrice(foodCart.getId() , quantity*foodCart.getFoodPrice());
        cartAdapter.notifyDataSetChanged();
    }

    @Override
    public void onMinusClicked(FoodCart foodCart) {
        int quantity = foodCart.getQuantity() - 1;
        if (quantity != 0){
            cartViewModel.updateQuantity(foodCart.getId() , quantity);
            cartViewModel.updatePrice(foodCart.getId() , quantity*foodCart.getFoodPrice());
            cartAdapter.notifyDataSetChanged();
        }else{
            cartViewModel.deleteCartItem(foodCart);
        }
    }
}