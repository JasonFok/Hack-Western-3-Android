package com.example.jason.localdealsnotifier;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


/**
 * Created by Jason on 10/15/2016.
 */

public class PromotionsAdapter extends RecyclerView.Adapter<PromotionsAdapter.MyViewHolder> {

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


    public PromotionsAdapter(List<Promotion> promotionsList) {
        this.promotionsList = promotionsList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.promotion_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Promotion movie = promotionsList.get(position);
        holder.company.setText(movie.getCompany());
        holder.message.setText(movie.getMessage());
        holder.distance.setText(movie.getDistance());
    }

    @Override
    public int getItemCount() {
        return promotionsList.size();
    }
}
