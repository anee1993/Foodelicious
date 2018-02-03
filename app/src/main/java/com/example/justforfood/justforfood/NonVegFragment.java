package com.example.justforfood.justforfood;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class NonVegFragment extends ListFragment implements AdapterView.OnItemLongClickListener{
    ListView lv1;
    Button addtocart;
    public NonVegFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.custom_list_menu, container, false);
        addtocart=(Button)rootView.findViewById(R.id.cart);
        addtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int flag = LoginCheck.getFLAG();
                if(flag==0){
                    new AlertDialog.Builder(getView().getContext())
                            .setTitle("You have not signed in")
                            .setMessage("Please sign in to continue")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                    Intent intent = new Intent(getActivity(),LoginActivity.class);
                                    startActivity(intent);
                                }
                            })
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                }
                else if(flag==1){
                    Toast.makeText(getActivity(),"Item Added to cart",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getActivity(),MainActivity.class);
                    intent.putExtra("signout",LoginCheck.textchange);
                    startActivity(intent);
                }
            }
        });
        return rootView;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ArrayList image_details = getListData();
        lv1 = (ListView)getView().findViewById(android.R.id.list);
        lv1.setAdapter(new CustomAdaptor(getActivity(), image_details));
        lv1.setOnItemLongClickListener(this);

    }


    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        Object o = lv1.getItemAtPosition(position);
        NewItem newsData = (NewItem) o;
        Toast.makeText(getActivity(), "Item "+newsData.getName()+" has been added to cart", Toast.LENGTH_LONG).show();
        return false;
    }
    private ArrayList getListData() {
        ArrayList<NewItem> results = new ArrayList<NewItem>();
        NewItem newsData1 = new NewItem();
        newsData1.setName("Chicken Lollipop");

        newsData1.setDescription("5 pieces per plate");
        newsData1.setPrice("Rs 80");
        results.add(newsData1);
        NewItem newsData2 = new NewItem();
        newsData2.setName("Chicken fried rice");

        newsData2.setDescription("A full bowl with Raitha");
        newsData2.setPrice("Rs 110");
        results.add(newsData2);
        NewItem newsData3 = new NewItem();
        newsData3.setName("Fish Fry gravy");

        newsData3.setDescription("Gravy for two");
        newsData3.setPrice("Rs 90");
        results.add(newsData3);
        NewItem newsData4 = new NewItem();
        newsData4.setName("Chicken tikka gravy");

        newsData4.setDescription("Gravy for two");
        newsData4.setPrice("Rs 100");
        results.add(newsData4);

        // Add some more dummy data for testing
        return results;

    }
}