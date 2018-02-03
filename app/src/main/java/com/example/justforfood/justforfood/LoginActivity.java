package com.example.justforfood.justforfood;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.justforfood.justforfood.Database.UserDetails;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmResults;

public class LoginActivity extends AppCompatActivity {
    String username,userpass;
    EditText mobnumber,password;
    Button login;
    int count=0;
    TextView createAccount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mobnumber = (EditText)findViewById(R.id.mobile);
        password = (EditText)findViewById(R.id.userpassword);
        login = (Button)findViewById(R.id.login);
        createAccount=(TextView)findViewById(R.id.createAccount);
        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToRegister = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(goToRegister);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mobnumber.getText().toString().equals("") && password.getText().toString().equals("")) {
                    new AlertDialog.Builder(LoginActivity.this)
                            .setTitle("")
                            .setMessage("Please enter valid username/password")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                } else if(!mobnumber.getText().toString().equals("") && !password.getText().toString().equals("")){

                    Realm logincheck = RealmInstanceGenerator.getInstance(LoginActivity.this);
                    logincheck.beginTransaction();
                    RealmResults<UserDetails> details = logincheck.where(UserDetails.class).findAll();
                    for(UserDetails d:details){
                        count+=1;
                    username=d.getMobilenum();
                        userpass=d.getPassword();
                        if(username.equals(mobnumber.getText().toString())&&userpass.equals(password.getText().toString())){
                            String name = d.getName();
                            int flag = LoginCheck.getFLAG();
                            flag=flag+1;
                            LoginCheck.setFLAG(flag);
                            LoginCheck.setName(name);
                            Intent goToMenu = new Intent(LoginActivity.this, MainActivity.class);
                            goToMenu.putExtra("signout",LoginCheck.textchange);
                            startActivity(goToMenu);

                        }
                        else if(count==details.size()){
                            new AlertDialog.Builder(LoginActivity.this)
                                    .setTitle("")
                                    .setMessage("Username/Password does not exist!")
                                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                        }
                                    })
                                    .setIcon(android.R.drawable.ic_dialog_alert)
                                    .show();
                        }
                    }
                    logincheck.commitTransaction();


                    /*new AlertDialog.Builder(LoginActivity.this)
                            .setTitle("")
                            .setMessage("Username/password does not exist !")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();*/

                }


            }
        });
    }
}
