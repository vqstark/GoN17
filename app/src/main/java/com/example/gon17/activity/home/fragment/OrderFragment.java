package com.example.gon17.activity.home.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gon17.R;
import com.example.gon17.activity.auth.RatingActivity;
import com.example.gon17.activity.order.Ordered_FoodActivity;
import com.example.gon17.adapter.RecycleViewAdapter;
import com.example.gon17.db.OrderDB;
import com.example.gon17.model.Order;
import com.example.gon17.model.User;

import java.util.List;

public class OrderFragment extends Fragment implements RecycleViewAdapter.Itemlistener{
    private RecycleViewAdapter adapter;
    private RecyclerView recyclerView;
    private OrderDB db;
    private User user;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_order, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView=view.findViewById(R.id.recycleView);
        adapter=new RecycleViewAdapter();

        db=new OrderDB(getContext());
        Bundle bundle = getArguments();
        user = (User)bundle.getSerializable("user");

        List<Order> list=db.getAll(user);
        adapter.setlist(list);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        adapter.setClickListener(this);
    }
    @Override
    public void onItemClick(View view, int pos) {
        Order order = adapter.getItem(pos);
        Intent intent = new Intent(getContext(), Ordered_FoodActivity.class);
        intent.putExtra("user", user);
        intent.putExtra("order", order);
        startActivity(intent);
    }
    @Override
    public void onResume() {
        super.onResume();
        Bundle bundle = getArguments();
        User user = (User)bundle.getSerializable("user");
        List<Order> list=db.getAll(user);
        adapter.setlist(list);
    }
}