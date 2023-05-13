package com.example.gon17.activity.home.fragment;

import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gon17.R;
import com.example.gon17.adapter.CartAdapter;
import com.example.gon17.model.FoodCart;
import com.example.gon17.viewmodel.CartViewModel;

import java.util.List;


public class CartFragment extends Fragment implements CartAdapter.CartClickedListeners {

    private RecyclerView recyclerView;
    private CartViewModel cartViewModel;
    private TextView totalCartPriceTv, textView;
    private CartAdapter cartAdapter;

    private AppCompatButton checkoutBtn;
    private CardView cardView;

    public CartFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        initializeVariables(view);

        cartViewModel.getAllCartItems().observe(getViewLifecycleOwner(), new Observer<List<FoodCart>>() {
            @Override
            public void onChanged(List<FoodCart> foodCarts) {
                double price = 0;
                cartAdapter.setFoodCartList(foodCarts);
                for (int i = 0; i < foodCarts.size(); i++) {
                    price = price + foodCarts.get(i).getTotalItemPrice();
                }
                totalCartPriceTv.setText("đ " + String.valueOf(price));
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

        return view;
    }
    private void initializeVariables(View view) {


        cartAdapter = new CartAdapter(this);
        textView = view.findViewById(R.id.textView2);
        cardView = view.findViewById(R.id.cartActivityCardView);
        totalCartPriceTv = view.findViewById(R.id.cartActivityTotalPriceTv);
        checkoutBtn = view.findViewById(R.id.cartActivityCheckoutBtn);

        cartViewModel = new ViewModelProvider(requireActivity()).get(CartViewModel.class);
        recyclerView = view.findViewById(R.id.cartRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(cartAdapter);
    }

    @Override
    public void onDeleteClicked(FoodCart foodCart) {
        cartViewModel.deleteCartItem(foodCart);
    }

    @Override
    public void onPlusClicked(FoodCart foodCart) {
        int quantity = foodCart.getQuantity() + 1;
        cartViewModel.updateQuantity(foodCart.getId(), quantity);
        cartViewModel.updatePrice(foodCart.getId(), quantity * foodCart.getFoodPrice());
        cartAdapter.notifyDataSetChanged();
    }

    @Override
    public void onMinusClicked(FoodCart foodCart) {
        int quantity = foodCart.getQuantity() - 1;
        if (quantity != 0) {
            cartViewModel.updateQuantity(foodCart.getId(), quantity);
            cartViewModel.updatePrice(foodCart.getId(), quantity * foodCart.getFoodPrice());
            cartAdapter.notifyDataSetChanged();
        } else {
            cartViewModel.deleteCartItem(foodCart);
        }
    }
}