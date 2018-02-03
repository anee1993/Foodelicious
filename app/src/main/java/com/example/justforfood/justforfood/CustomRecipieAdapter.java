package com.example.justforfood.justforfood;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Anirudh on 5/5/2016.
 */
public class CustomRecipieAdapter extends BaseAdapter {
    private ArrayList<String> listData;
    private LayoutInflater layoutInflater;

    public CustomRecipieAdapter(Context aContext, ArrayList<String> listData) {
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
            convertView = layoutInflater.inflate(R.layout.activity_recipe_row, null);
            holder = new ViewHolder();
            holder.recipieName = (TextView) convertView.findViewById(R.id.itemname);
           // holder.recipieContent = (TextView) convertView.findViewById(R.id.reporter);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.recipieName.setText(listData.get(position));
       // holder.recipieContent.setText(listData.get(position).getContent());
        return convertView;
    }

    static class ViewHolder {
        TextView recipieName;
    }
}
