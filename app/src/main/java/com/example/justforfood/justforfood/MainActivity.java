package com.example.justforfood.justforfood;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        LoginCheck.setFLAG(0);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView signin = (TextView)findViewById(R.id.login);
        TextView menu_items = (TextView)findViewById(R.id.menu);
        TextView recipe = (TextView)findViewById(R.id.recipe);
        TextView reviews = (TextView)findViewById(R.id.reviews);
        menu_items.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent menuIntent = new Intent(MainActivity.this,MenuListActivity.class);
                startActivity(menuIntent);
            }
        });

        recipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent recipeIntent = new Intent(MainActivity.this,RecipeActivity.class);
                startActivity(recipeIntent);
            }
        });
        reviews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this,ReviewActivity.class);
                startActivity(intent);
            }
        });
        signin.setOnClickListener(new View.OnClickListener() {
             @Override
            public void onClick(View v) {
                if(signin.getText().toString().equals("Sign In")){
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
                else if(signin.getText().toString().equals("Sign Out")){
                    signin.setText("Sign In");
                    LoginCheck.setFLAG(0);
                }
                }
        });
        if(LoginCheck.getFLAG()==1) {
            Intent intent = getIntent();
            String textval = intent.getStringExtra("signout");
            signin.setText(textval);
        }
    }
}

