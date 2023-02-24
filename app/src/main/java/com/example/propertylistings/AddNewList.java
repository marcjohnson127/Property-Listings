package com.example.propertylistings;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AddNewList extends AppCompatActivity {

    public SQLiteDatabase sqLiteDatabase;
    public mySQLiteDBHandler dbHandler;

    public EditText edtStreet;
    public EditText edtCity;
    public EditText edtArea;
    public EditText edtType;
    public EditText edtRoom;
    public EditText edtBath;
    public EditText edtPrice;
    public EditText edtSQFT;
    public EditText edtHOA;
    public EditText edtTax;

    public Button btnSubmit;

    public String street;
    public String city;
    public String area;
    public String type;
    public int room;
    public float bath;
    public int price;
    public int sqft;
    public int hoa;
    public int tax;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_new_list);


    }


}
