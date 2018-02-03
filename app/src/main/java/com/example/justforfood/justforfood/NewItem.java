package com.example.justforfood.justforfood;

import android.graphics.Color;

/**
 * Created by Anirudh on 4/23/2016.
 */
public class NewItem {
    private String Name;
    private String description;
    private String price;
    private int color;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
