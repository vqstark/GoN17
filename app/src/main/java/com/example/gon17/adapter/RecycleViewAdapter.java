package com.example.gon17.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gon17.R;
import com.example.gon17.activity.auth.RatingActivity;
import com.example.gon17.model.Order;

import java.util.ArrayList;
import java.util.List;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.HomeViewHolder>{

    private List<Order> list;
    private Itemlistener itemlistener;

    public void setClickListener(Itemlistener itemlistener){
        this.itemlistener=itemlistener;
    }

    public RecycleViewAdapter() {
        list = new ArrayList<>();
    }

    public void setlist(List<Order> list){
        this.list=list;
        notifyDataSetChanged();
    }
    public Order getItem(int pos){
        return list.get(pos);
    }
    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order, parent, false);
        return new HomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {
        Order order = list.get(position);
        if(order == null)
            return;
//        holder.img.setImageResource();
        holder.total.setText(String.valueOf(order.getTotal()));
        holder.status.setText(order.getStatus());
        holder.date.setText(order.getDate());

        holder.btdanhgia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), RatingActivity.class);
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(list != null)
            return list.size();
        return 0;
    }

    public class HomeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView total, status, date;
        private Button btdanhgia;
        private ImageView img; // ảnh món đầu tiên
        public HomeViewHolder(@NonNull View view) {
            super(view);
            total = view.findViewById(R.id.tvTotal);
            status = view.findViewById(R.id.tvStatus);
            date = view.findViewById(R.id.tvDate);
            btdanhgia = view.findViewById(R.id.btDanhgia);

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(itemlistener != null){
                itemlistener.onItemClick(view,getAdapterPosition());// => view don hang
            }
        }
    }
    public interface Itemlistener{
        void onItemClick(View view, int pos);
    }
}
