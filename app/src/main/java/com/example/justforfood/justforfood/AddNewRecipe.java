package com.example.justforfood.justforfood;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.justforfood.justforfood.Database.RecipieItems;

import io.realm.Realm;

public class AddNewRecipe extends AppCompatActivity {
    EditText recipeName;
    EditText recipeDescription;
    Button save;
    public static int boo = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_recipe);
        recipeName = (EditText)findViewById(R.id.name);
        recipeDescription = (EditText)findViewById(R.id.description);
        save = (Button)findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(recipeName.getText().toString().equals("")&&recipeDescription.getText().toString().equals("")){
                    new AlertDialog.Builder(AddNewRecipe.this)
                            .setTitle("")
                            .setMessage("Please enter a valid recipe!")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                }
                else {
                    Realm addRecipe = RealmInstanceGenerator.getInstance(AddNewRecipe.this);
                    addRecipe.beginTransaction();
                    RecipieItems newitems = addRecipe.createObject(RecipieItems.class);
                    newitems.setName(LoginCheck.getName());
                    newitems.setRecipeName(recipeName.getText().toString());
                    newitems.setRecipeDesc(recipeDescription.getText().toString());
                    addRecipe.commitTransaction();
                    Toast.makeText(getApplicationContext(), "Recipe added!", Toast.LENGTH_LONG).show();
                    NotificationManager notificationManager =
                            (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

                    Notification n = new Notification.Builder(AddNewRecipe.this)
                            .setContentTitle("New Recipe has been added sucessfully")
                            .setContentText("Recipe")
                            .setSmallIcon(R.mipmap.app_launcher)
                            .setAutoCancel(true)
                            .build();

                    notificationManager.notify(0, n);
                    Intent goToRecipePage = new Intent(AddNewRecipe.this, RecipeActivity.class);
                    startActivity(goToRecipePage);
                }

            }
        });
    }
}
