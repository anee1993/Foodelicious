package com.example.justforfood.justforfood;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.jar.Attributes;

/**
 * Created by Anirudh on 4/23/2016.
 */
public class CustomAdaptor extends BaseAdapter{
   // MenuListActivity currentFrag = new MenuListActivity();
    private ArrayList<NewItem> listData;
    private LayoutInflater layoutInflater;
    private Context aContext;
    public CustomAdaptor(Context aContext, ArrayList<NewItem> listData) {
        this.aContext=aContext;
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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.fragment_menu, null);
            holder = new ViewHolder();
            holder.Name = (TextView) convertView.findViewById(R.id.Name);
            holder.Description = (TextView) convertView.findViewById(R.id.Description);
            holder.Price = (TextView) convertView.findViewById(R.id.Price);
                        convertView.setTag(holder);
            }

        else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.Name.setText(listData.get(position).getName());
        holder.Description.setText(listData.get(position).getDescription());
        holder.Price.setText(listData.get(position).getPrice());
        return convertView;
    }
    static class ViewHolder {
        TextView Name;
        TextView Description;
        TextView Price;
    }
}
