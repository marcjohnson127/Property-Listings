package com.example.propertylistings;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class ShowListings extends AppCompatActivity {

    public mySQLiteDBHandler dbHandler;
    public SQLiteDatabase sqLiteDatabase;

    public ListView dispList;
    public ListView dispArea;
    public ArrayList arrayList;
    public ArrayList arrayList2;
    public ArrayAdapter arrayAdapter;
    public ArrayAdapter arrayAdapter2;

    public static String chosenItem;

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState)  {
        super.onPostCreate(savedInstanceState);
        setContentView(R.layout.show_all_listings);
    //Declare Variables
        dispList = (ListView) findViewById(R.id.displayListings);
        dispArea = (ListView) findViewById(R.id.displayArea);

    //Initialized dbHandler
        arrayList = dbHandler.allData();
        arrayList2 = dbHandler.allData2();

    //Initialize ArrayAdapter
        arrayAdapter = new ArrayAdapter(ShowListings.this, android.R.layout.simple_list_item_1, arrayList);
        arrayAdapter2 = new ArrayAdapter(ShowListings.this, android.R.layout.simple_list_item_1, arrayList2);

    //Set ArrayAdapter to List View
        dispList.setAdapter(arrayAdapter);
        dispArea.setAdapter(arrayAdapter2);

        dispList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                chosenItem = dispList.getItemAtPosition(i).toString();
                Intent intent = new Intent(ShowListings.this, PropertyInfo.class);

                startActivity(intent);
            }
        });

    }


}
