package com.example.gon17.activity.home.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gon17.R;
import com.example.gon17.activity.auth.RatingActivity;
import com.example.gon17.adapter.Ordered_FoodAdapter;
import com.example.gon17.adapter.RecycleViewAdapter;
import com.example.gon17.db.OrderDB;
import com.example.gon17.model.Food;
import com.example.gon17.model.Order;
import com.example.gon17.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Ordered_FoodFragment extends Fragment implements Ordered_FoodAdapter.Itemlistener{
    private Ordered_FoodAdapter adapter;
    private RecyclerView recyclerView;
    private OrderDB db;
    private User user;
    private Order order;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_ordered_food, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView=view.findViewById(R.id.recycleView);

        db=new OrderDB(getContext());
        Bundle bundle = getArguments();
        user = (User)bundle.getSerializable("user");
        order = (Order) bundle.getSerializable("order");
        adapter=new Ordered_FoodAdapter(getContext(), user);

        Map<Food, Integer> list=db.getFoodByOrderID(order.getId());
        List<Food> foodList = new ArrayList<>();
        List<Integer> quantity = new ArrayList<>();
        for(Map.Entry<Food,Integer> entry: list.entrySet()){
            foodList.add(entry.getKey());
            quantity.add(entry.getValue());
        }

        System.out.println("====================================>" + list.size());
        adapter.setlist(foodList, quantity);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        adapter.setClickListener(this);
    }
    @Override
    public void onItemClick(View view, int pos) {
        Food food = adapter.getItem(pos);
        Toast.makeText(getContext(), "===========> thanh cong", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getActivity(), RatingActivity.class);
        intent.putExtra("food", food);
        intent.putExtra("user", user);
        startActivity(intent);
    }
    @Override
    public void onResume() {
        super.onResume();

        Map<Food, Integer> list=db.getFoodByOrderID(order.getId());
        List<Food> foodList = new ArrayList<>();
        List<Integer> quantity = new ArrayList<>();
        for(Map.Entry<Food,Integer> entry: list.entrySet()){
            foodList.add(entry.getKey());
            quantity.add(entry.getValue());
        }
        adapter.setlist(foodList, quantity);
    }
}
