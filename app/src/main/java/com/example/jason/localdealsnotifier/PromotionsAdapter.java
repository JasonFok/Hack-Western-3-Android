package com.example.jason.localdealsnotifier;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Context;
import android.util.Log;
import android.widget.TextView;

//import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;


import java.util.List;


/**
 * Created by Jason on 10/15/2016.
 */

public class PromotionsAdapter extends FirebaseRecyclerAdapter<Promotion, PromotionsAdapter.MyViewHolder> {

    private List<Promotion> promotionsList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView company, message, distance;

        public MyViewHolder(View view) {
            super(view);
            company = (TextView) view.findViewById(R.id.company);
            message = (TextView) view.findViewById(R.id.message);
            distance = (TextView) view.findViewById(R.id.distance);
        }
    }

    public PromotionsAdapter(Class<Promotion> modelClass, int modelLayout, Class<MyViewHolder> viewHolderClass, DatabaseReference ref, Context context) {
        super(modelClass, modelLayout, viewHolderClass, ref);
//        this.context = context;
    }

//
//    public PromotionsAdapter(List<Promotion> promotionsList) {
//        this.promotionsList = promotionsList;
//    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.promotion_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Promotion promo = promotionsList.get(position);
        holder.company.setText(promo.getCompany());
        holder.message.setText(promo.getMessage());
        holder.distance.setText(promo.getDistance());
    }

    @Override
    protected void populateViewHolder(MyViewHolder viewHolder, Promotion model, int position) {
        viewHolder.company.setText(model.getCompany());
//        Glide.with(context).load(model.getRecipeImageUrl()).into(viewHolder.recipeImage);
    }

    @Override
    public int getItemCount() {
//        return promotionsList.size();
        return 0;
    }
}
