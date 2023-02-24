package com.example.propertylistings;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.ShowableListMenu;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    public mySQLiteDBHandler dbHandler;
    public SQLiteDatabase sqLiteDatabase;

    public Button go2List;
    public Button addNewList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Declare Variables
        go2List = (Button) findViewById(R.id.go2Listings);
        addNewList = (Button) findViewById(R.id.addNewListing);

        dbHandler = new mySQLiteDBHandler(MainActivity.this);

    //addNewList Button Handler
        addNewList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddNewList.class);
                startActivity(intent);
            }
        });

        go2List.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ShowListings.class);
                startActivity(intent);
            }
        });

    }
}