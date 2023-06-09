package com.example.gon17.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gon17.R;
import com.example.gon17.activity.auth.RatingActivity;
import com.example.gon17.model.Comment;
import com.example.gon17.model.Food;
import com.example.gon17.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cmt_Detail_FoodAdapter extends RecyclerView.Adapter<Cmt_Detail_FoodAdapter.HomeViewHolder>{

    private List<Comment> commentList;
    private User user;
    private Food food;
    private Context context;

    public Cmt_Detail_FoodAdapter(Context context, User user,Food food) {
        this.context = context;
        this.user = user;
        this.food = food;
        commentList = new ArrayList<>();
    }
    public void setlist(List<Comment> commentList){
        this.commentList = commentList;
        notifyDataSetChanged();
    }

    public Comment getItem(int pos){
        return commentList.get(pos);
    }
    @NonNull
    @Override
    public Cmt_Detail_FoodAdapter.HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cmt_detail_food, parent, false);
        return new Cmt_Detail_FoodAdapter.HomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Cmt_Detail_FoodAdapter.HomeViewHolder holder, int position) {
        Comment comment = commentList.get(position);
        if (comment == null)
            return;
        holder.username.setText(comment.getUser().getFullName());
        holder.rating.setRating(comment.getRating());
        holder.content.setText(comment.getContent());
        byte[] hinh = comment.getImg();
        Bitmap bitmap = BitmapFactory.decodeByteArray(hinh, 0, hinh.length);
        holder.img.setImageBitmap(bitmap);
    }

    @Override
    public int getItemCount() {
        if(commentList!= null)
            return commentList.size();
        return 0;
    }

    public class HomeViewHolder extends RecyclerView.ViewHolder{
        private TextView username, content;
        private RatingBar rating;
        private ImageView img;

        public HomeViewHolder(@NonNull View view) {
            super(view);
            username= view.findViewById(R.id.tvUserName);
            content = view.findViewById(R.id.tvContent);
            rating = view.findViewById(R.id.rating);
            img = view.findViewById(R.id.img1);
        }
    }
}
