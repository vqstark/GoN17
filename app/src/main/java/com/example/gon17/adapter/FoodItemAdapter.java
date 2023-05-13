package com.example.gon17.adapter;

import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.cardview.widget.CardView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gon17.R;
import com.example.gon17.model.FoodItem;

import java.util.List;

public class FoodItemAdapter extends RecyclerView.Adapter<FoodItemAdapter.FoodItemViewHolder> {

    private List<FoodItem> foodItemList;
    private FoodClickedListeners foodClickedListeners;

    public FoodItemAdapter(FoodClickedListeners foodClickedListeners) {
        this.foodClickedListeners = foodClickedListeners;
    }

    public void setFoodItemList(List<FoodItem> foodItemList) {
        this.foodItemList = foodItemList;
    }

    @NonNull
    @Override
    public FoodItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.each_food, parent, false);
        return new FoodItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodItemViewHolder holder, int position) {
        FoodItem foodItem = foodItemList.get(position);
        holder.foodNameTv.setText(foodItem.getFoodName());
        holder.foodDescriptionTv.setText(foodItem.getFoodDescription());
        holder.foodPriceTv.setText("đ " + String.valueOf(foodItem.getFoodPrice()));
        // Set image from byte array
        holder.foodImageView.setImageBitmap(BitmapFactory.decodeByteArray(foodItem.getFoodImage(), 0, foodItem.getFoodImage().length));

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                foodClickedListeners.onCardClicked(foodItem);
            }
        });

        holder.addToCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                foodClickedListeners.onAddToCartBtnClicked(foodItem);
            }
        });
    }

    @Override
    public int getItemCount() {
        return foodItemList != null ? foodItemList.size() : 0;
    }

    public class FoodItemViewHolder extends RecyclerView.ViewHolder {

        private ImageView foodImageView, addToCartBtn;
        private TextView foodNameTv, foodDescriptionTv, foodPriceTv;
        private CardView cardView;

        public FoodItemViewHolder(@NonNull View itemView) {
            super(itemView);

            cardView = itemView.findViewById(R.id.eachFoodCardView);
            addToCartBtn = itemView.findViewById(R.id.eachFoodAddToCartBtn);
            foodNameTv = itemView.findViewById(R.id.eachFoodName);
            foodImageView = itemView.findViewById(R.id.eachFoodIv);
            foodDescriptionTv = itemView.findViewById(R.id.eachFoodDescriptionTv);
            foodPriceTv = itemView.findViewById(R.id.eachFoodPriceTv);
        }
    }

    public interface FoodClickedListeners {
        void onCardClicked(FoodItem food);
        void onAddToCartBtnClicked(FoodItem foodItem);
    }
}
