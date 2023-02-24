package com.example.propertylistings;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
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

    }
}