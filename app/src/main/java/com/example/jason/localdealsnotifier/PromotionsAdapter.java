package com.example.jason.localdealsnotifier;

import android.content.Context;
import android.content.Intent;
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
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView company, message, distance;
        private Context c;

        public MyViewHolder(View view, Context context) {
            super(view);

            c = context;
            company = (TextView) view.findViewById(R.id.company);
            message = (TextView) view.findViewById(R.id.message);
            distance = (TextView) view.findViewById(R.id.distance);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(c, PromoDetailsActivity.class);
            intent.putExtra(PromoDetailsActivity.COMPANY_DATA, company.getText().toString());
            intent.putExtra(PromoDetailsActivity.MESSAGE_DATA, message.getText().toString());
            intent.putExtra(PromoDetailsActivity.DISTANCE_DATA, distance.getText().toString());
            context.startActivity(intent);
        }
    }


    public PromotionsAdapter(List<Promotion> promotionsList, Context context) {
        this.promotionsList = promotionsList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.promotion_list_row, parent, false);

        return new MyViewHolder(itemView, context);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Promotion movie = promotionsList.get(position);
        holder.company.setText(movie.getCompany());
        holder.message.setText(movie.getMessage());
        holder.distance.setText(String.valueOf(Math.floor(movie.getDistance() * 1000) / 1000) + "km");
    }

    @Override
    public int getItemCount() {
        return promotionsList.size();
    }



}
