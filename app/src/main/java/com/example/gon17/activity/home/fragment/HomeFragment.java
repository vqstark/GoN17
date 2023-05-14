package com.example.gon17.activity.home.fragment;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.gon17.R;
//import com.example.gon17.activity.GoToListFoodActivity;
import com.example.gon17.activity.home.HomeActivity;
import com.example.gon17.adapter.FoodItemAdapter;
import com.example.gon17.db.FoodItemDAO;
import com.example.gon17.model.FoodCart;
import com.example.gon17.model.FoodItem;
import com.example.gon17.model.User;
import com.example.gon17.viewmodel.CartViewModel;
import com.example.gon17.views.CartActivity;
import com.example.gon17.views.DetailFoodActivity;
import com.google.android.material.snackbar.Snackbar;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements FoodItemAdapter.FoodClickedListeners {

    private TextView lblWelcome;
    private TextView lblPosDetails;

    private RecyclerView recyclerView;
    private List<FoodItem> foodItemList;
    private FoodItemAdapter adapter;

    private FoodItemDAO foodItemDAO;

    private CartViewModel viewModel;
    private List<FoodCart> foodCartList;


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

        recyclerView = view.findViewById(R.id.listFoodRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        adapter = new FoodItemAdapter(this);
        recyclerView.setAdapter(adapter);

        // Set up the food items in the RecyclerView
        foodItemDAO = new FoodItemDAO(getContext());

        // Set up the food items in the RecyclerView
        setUpList();

        foodCartList = new ArrayList<>();
        viewModel = new ViewModelProvider(this).get(CartViewModel.class);

        viewModel.getAllCartItems().observe(getViewLifecycleOwner(), new Observer<List<FoodCart>>() {
            @Override
            public void onChanged(List<FoodCart> foodCarts) {
                foodCartList.clear();
                foodCartList.addAll(foodCarts);
            }
        });

        return view;
    }

    private void setUpList() {
        foodItemList = foodItemDAO.getAllFoodItems(); // Retrieve all food items from the database using FoodItemDAO
        adapter.setFoodItemList(foodItemList);
    }
    @Override
    public void onCardClicked(FoodItem food) {
        Intent intent = new Intent(getContext(), DetailFoodActivity.class);
        intent.putExtra("foodItem", food);
        startActivity(intent);
    }

    @Override
    public void onAddToCartBtnClicked(FoodItem foodItem) {
        FoodCart foodCart = new FoodCart();
        foodCart.setFoodId(foodItem.getId());
        foodCart.setFoodName(foodItem.getFoodName());
        foodCart.setFoodDescription(foodItem.getFoodDescription());
        foodCart.setFoodPrice(foodItem.getFoodPrice());
        foodCart.setFoodImage(foodItem.getFoodImage());

        int quantity = 1;
        int id = -1;
        // Check if the food item already exists in the cart
        for (FoodCart cartItem : foodCartList) {
            if (cartItem.getFoodName().equals(foodItem.getFoodName())) {
                quantity = cartItem.getQuantity() + 1;
                id = cartItem.getId();
                break;
            }
        }

        if (id == -1) {
            // The food item is not in the cart, so insert it
            foodCart.setQuantity(quantity);
            foodCart.setTotalItemPrice(quantity * foodCart.getFoodPrice());
            viewModel.insertCartItem(foodCart);
        } else {
            // The food item is already in the cart, so update its quantity and total price
            viewModel.updateQuantity(id, quantity);
            viewModel.updatePrice(id, quantity * foodCart.getFoodPrice());
        }

        Snackbar.make(recyclerView, "Món ăn đã được thêm", Snackbar.LENGTH_SHORT).show();
    }


}