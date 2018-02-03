package com.example.justforfood.justforfood;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.justforfood.justforfood.Database.RecipieItems;
import com.example.justforfood.justforfood.Database.UserDetails;

import java.util.ArrayList;
import java.util.HashMap;

import io.realm.Realm;
import io.realm.RealmResults;

public class RecipeActivity extends AppCompatActivity {
    private Toolbar toolbar;
    AlertDialog dialog;
    ArrayList image_details;
    ListView lv1;
    public ArrayList<String> results = new ArrayList<String>();

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(RecipeActivity.this,MainActivity.class);
        intent.putExtra("signout",LoginCheck.textchange);
        startActivity(intent);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipie_list);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        image_details = getListData();
        lv1 = (ListView) findViewById(R.id.custom_recipe_ist);
        lv1.setAdapter(new CustomRecipieAdapter(this, image_details));
        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                //Object o = lv1.getItemAtPosition(position);
                String name = (String)image_details.get(position);
                HashMap recipes = RecipieHashMap.getRecipes();
                dialog = new AlertDialog.Builder(RecipeActivity.this)
                        .setTitle("Recipie Ingredients")
                        .setMessage((CharSequence) recipes.get(name))
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.add_recipe, menu);
        /*MenuItem item = menu.findItem(R.id.action_cart);
        MenuItemCompat.setActionView(item, R.layout.action_badge_layout);
        RelativeLayout notifCount = (RelativeLayout) MenuItemCompat.getActionView(item);

        TextView tv = (TextView) notifCount.findViewById(R.id.actionbar_notifcation_textview);
        tv.setText("12");*/
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.addRecipe) {
            if (LoginCheck.getFLAG() == 0) {
                new AlertDialog.Builder(RecipeActivity.this)
                        .setTitle("")
                        .setMessage("Please login to add a recipe")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                Intent intent = new Intent(RecipeActivity.this, LoginActivity.class);
                                startActivity(intent);
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            } else {
                Intent intent = new Intent(RecipeActivity.this, AddNewRecipe.class);
                startActivity(intent);


            }

        }
        return super.onOptionsItemSelected(item);
    }

    private ArrayList getListData() {

        Realm displayRecipes = RealmInstanceGenerator.getInstance(RecipeActivity.this);
        displayRecipes.beginTransaction();
        RealmResults<RecipieItems> details = displayRecipes.where(RecipieItems.class).findAll();
        for (int i = 0; i < details.size(); i++) {
            RecipieItems d = details.get(i);
            //recipes[i] = new RecipieItemsPojo();
            RecipieHashMap.addRecipes(d.getRecipeName(), d.getRecipeDesc());
           // recipes[i].setTitle(d.getRecipeName());
            // recipes[i].setContent(d.getRecipeDesc());
            results.add(d.getRecipeName());
        }
        displayRecipes.commitTransaction();
        results.add("Palak Paneer");
        RecipieHashMap.addRecipes("Palak Paneer", "1/2cup panner,palak,Red Chilly paste, Ginger Garlic paste,1 cup milk");
        results.add("Malai kofta");
        RecipieHashMap.addRecipes("Malai kofta", "1/2cup Malai,Red Chilly paste, Ginger Garlic paste,2 cups of milk");
        results.add("Veg Burger");
        RecipieHashMap.addRecipes("Veg Burger", "1 cup cheese,Large buns, Ginger Garlic paste,Aloo Tikka");
        return results;
    }
}

