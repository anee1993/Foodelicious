package com.example.justforfood.justforfood;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by 972791 on 5/10/2016.
 */
public class CustomReviewAdapter extends BaseAdapter {
    private ArrayList<ReviewDetailsPojo> listData;
    private LayoutInflater layoutInflater;

    public CustomReviewAdapter(Context aContext, ArrayList<ReviewDetailsPojo> listData) {
        this.listData = listData;
        layoutInflater = LayoutInflater.from(aContext);
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.activity_recipe_review_row, null);
            holder = new ViewHolder();
            holder.review = (TextView) convertView.findViewById(R.id.review);
            holder.reviewerName=(TextView)convertView.findViewById(R.id.reviewerName);
            holder.rating=(RatingBar)convertView.findViewById(R.id.ratingBar);
            // holder.recipieContent = (TextView) convertView.findViewById(R.id.reporter);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.review.setText(listData.get(position).getReview());
        holder.reviewerName.setText(listData.get(position).getName());
        holder.rating.setRating(listData.get(position).getRating());
        // holder.recipieContent.setText(listData.get(position).getContent());
        return convertView;
    }

    static class ViewHolder {
        TextView review;
        TextView reviewerName;
        RatingBar rating;
    }
}
