package com.example.justforfood.justforfood;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Anirudh on 5/5/2016.
 */
public class RecipieHashMap {
    static HashMap hm = new HashMap();
    public static void addRecipes(String recipeName,String recipeContent) {
    hm.put(recipeName,recipeContent);
    }
    public static HashMap getRecipes(){
        return hm;
    }
}