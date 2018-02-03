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

import com.example.justforfood.justforfood.Database.RecipieItems;
import com.example.justforfood.justforfood.Database.Reviews;

import java.util.ArrayList;
import java.util.HashMap;

import io.realm.Realm;
import io.realm.RealmResults;

public class ReviewActivity extends AppCompatActivity {

    private Toolbar toolbar;
    ArrayList review_details;
    ListView lv1;
    public ArrayList<ReviewDetailsPojo> results = new ArrayList<ReviewDetailsPojo>();

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(ReviewActivity.this,MainActivity.class);
        intent.putExtra("signout",LoginCheck.textchange);
        startActivity(intent);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        review_details = getListData();
        lv1 = (ListView) findViewById(R.id.custom_review_list);
        lv1.setAdapter(new CustomReviewAdapter(this, review_details));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.add_review, menu);
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
        if (id == R.id.addReview) {
            if (LoginCheck.getFLAG() == 0) {
                new AlertDialog.Builder(ReviewActivity.this)
                        .setTitle("")
                        .setMessage("Please login to add a review")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                Intent intent = new Intent(ReviewActivity.this, LoginActivity.class);
                                startActivity(intent);
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            } else {
                Intent intent = new Intent(ReviewActivity.this, AddNewReviewActivity.class);
                startActivity(intent);
            }

        }
        return super.onOptionsItemSelected(item);
    }

    private ArrayList getListData() {
        Realm displayReviews = RealmInstanceGenerator.getInstance(ReviewActivity.this);
        displayReviews.beginTransaction();
        RealmResults<Reviews> details = displayReviews.where(Reviews.class).findAll();
        for (int i = 0; i < details.size(); i++) {
            Reviews d = details.get(i);
            results.add(new ReviewDetailsPojo(d.getName(),d.getReview(),d.getRating()));
        }
        displayReviews.commitTransaction();
        return results;
    }
}
