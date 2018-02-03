package com.example.justforfood.justforfood.Database;

import io.realm.RealmObject;

/**
 * Created by 972791 on 5/9/2016.
 */
public class RecipieItems extends RealmObject {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;
    private String recipeName;
    private String recipeDesc;

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public String getRecipeDesc() {
        return recipeDesc;
    }

    public void setRecipeDesc(String recipeDesc) {
        this.recipeDesc = recipeDesc;
    }



}
