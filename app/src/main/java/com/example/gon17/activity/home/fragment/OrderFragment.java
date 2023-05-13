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

//    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;
//
//    public OrderFragment() {
//        // Required empty public constructor
//    }
//
//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment OrderFragment.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static OrderFragment newInstance(String param1, String param2) {
//        OrderFragment fragment = new OrderFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
//    }

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
        System.out.println("====================================>" + list.size());
        adapter.setlist(list);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        adapter.setClickListener(this);
    }
    @Override
    public void onItemClick(View view, int pos) {
        Order order = adapter.getItem(pos);
        Toast.makeText(getContext(), "OKKKKKKKKKKKKKK", Toast.LENGTH_SHORT).show();
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