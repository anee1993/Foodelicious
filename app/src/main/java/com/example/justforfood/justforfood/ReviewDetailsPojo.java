package com.example.justforfood.justforfood;

/**
 * Created by 972791 on 5/10/2016.
 */
public class ReviewDetailsPojo {
    private String name,review;
    private float rating;
    public ReviewDetailsPojo(String name,String review,float rating){
        this.name=name;
        this.review=review;
        this.rating=rating;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



}
