package com.example.justforfood.justforfood;

import android.widget.ImageView;

import java.io.Serializable;

/**
 * Created by Anirudh on 5/5/2016.
 */
public class RecipieItemsPojo implements Serializable {
    private String content;
    private String title;
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }




}
