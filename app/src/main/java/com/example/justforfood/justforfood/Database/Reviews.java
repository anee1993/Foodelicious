package com.example.justforfood.justforfood.Database;

import io.realm.RealmObject;

/**
 * Created by 972791 on 5/10/2016.
 */
public class Reviews extends RealmObject {
    private float rating;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    private String name;
    private String review;
}
