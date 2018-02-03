package com.example.justforfood.justforfood;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.justforfood.justforfood.Database.UserDetails;

import io.realm.Realm;

public class RegisterActivity extends AppCompatActivity {
    Button signup;
    EditText name,mobile,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        signup = (Button)findViewById(R.id.signup);
        name = (EditText)findViewById(R.id.name);
        mobile = (EditText)findViewById(R.id.mobnumber);
        password = (EditText)findViewById(R.id.userpassword);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*SharedPreferences pref = getApplicationContext().getSharedPreferences("UserDetails", MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("name", name.getText().toString());
                editor.putString("mobile", mobile.getText().toString());
                editor.putString("password", password.getText().toString());
                editor.commit();
                Intent goToSigninPage = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(goToSigninPage);*/
                if (name.getText().toString().equals("") || mobile.getText().toString().equals("") || password.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "All given fields are manditory", Toast.LENGTH_LONG).show();
                } else {
                    Realm userDetails = RealmInstanceGenerator.getInstance(RegisterActivity.this);
                    userDetails.beginTransaction();
                    UserDetails users = userDetails.createObject(UserDetails.class);
                    users.setName(name.getText().toString());
                    users.setMobilenum(mobile.getText().toString());
                    users.setPassword(password.getText().toString());
                    userDetails.commitTransaction();
                    Toast.makeText(RegisterActivity.this, "Registration Successful !", Toast.LENGTH_LONG).show();
                    Intent goToSigninPage = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(goToSigninPage);
                }
            }
        });

    }



}
