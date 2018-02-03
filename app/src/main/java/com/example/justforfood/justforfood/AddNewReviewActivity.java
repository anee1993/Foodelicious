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
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.justforfood.justforfood.Database.Reviews;

import io.realm.Realm;

public class AddNewReviewActivity extends AppCompatActivity {

    EditText reviewComment;
    private static final int NOTIFY_ME_ID=1;
    RatingBar ratingValue;
    Button saveReview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_review);
        reviewComment = (EditText)findViewById(R.id.userReview);
        ratingValue = (RatingBar)findViewById(R.id.userRating);
        saveReview = (Button)findViewById(R.id.saveReview);
        saveReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Context context = null;
                Intent intent = new Intent(AddNewRecipe.this, RecipeActivity.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(context, (int) System.currentTimeMillis(), intent, 0);

                newItems=new RecipieItemsPojo();
                RecipieHashMap.addRecipes(recipeName.getText().toString(), recipeDescription.getText().toString());
                newItems.setTitle(recipeName.getText().toString());
                newItems.setContent(recipeDescription.getText().toString());
                *//*Notification n  = new Notification.Builder(context)
                        .setContentTitle("New mail from " + "test@gmail.com")
                        .setContentText("Subject")
                        .setContentIntent(pendingIntent)
                        .setAutoCancel(true).build();*//*


                NotificationManager notificationManager = (NotificationManager)
                        getSystemService(NOTIFICATION_SERVICE);
                notificationManager.notify(NOTIFY_ME_ID, n);
                Toast.makeText(getApplicationContext(),"Recipe added!",Toast.LENGTH_LONG).show();
                Intent goToRecipePage = new Intent(AddNewRecipe.this,RecipeActivity.class);
                goToRecipePage.putExtra("newobject",newItems);
                startActivity(goToRecipePage);
                boo=1;*/
                if(reviewComment.getText().toString().equals("")&&ratingValue.getRating()==0){
                    new AlertDialog.Builder(AddNewReviewActivity.this)
                            .setTitle("")
                            .setMessage("Please enter a valid review!")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                }
                else {
                    Realm addReview = RealmInstanceGenerator.getInstance(AddNewReviewActivity.this);
                    addReview.beginTransaction();
                    Reviews newReview = addReview.createObject(Reviews.class);
                    newReview.setName(LoginCheck.getName());
                    newReview.setReview(reviewComment.getText().toString());
                    newReview.setRating(ratingValue.getRating());
                    addReview.commitTransaction();
                    NotificationManager notificationManager =
                            (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

                    Notification n = new Notification.Builder(AddNewReviewActivity.this)
                            .setContentTitle("New Review has been added sucessfully")
                            .setContentText("Review")
                            .setSmallIcon(R.mipmap.app_launcher)
                            .setAutoCancel(true)
                            .build();

                    notificationManager.notify(0, n);
                    Toast.makeText(getApplicationContext(), "Thanks for your review!", Toast.LENGTH_LONG).show();
                    Intent goToRecipePage = new Intent(AddNewReviewActivity.this, ReviewActivity.class);
                    startActivity(goToRecipePage);
                }

            }
        });
    }
}
