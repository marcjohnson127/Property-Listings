package com.example.propertylistings;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

import java.text.DecimalFormat;

public class PropertyInfo extends AppCompatActivity {

    public mySQLiteDBHandler dbHandler;
    public SQLiteDatabase sqLiteDatabase;

    public TextView txtStreet;
    public TextView txtCity;
    public TextView txtArea;
    public TextView txtType;
    public TextView txtPrice;
    public TextView txtSQFT;
    public TextView txtRoom;
    public TextView txtBath;
    public TextView txtHOA;
    public TextView txtTax;

    public Button getMortgage;
    public TextView monTotal;
    public TextView downPay;

    public Button btnDlt;
    public Button btnLoc;

    public CheckBox ckFav;

    public static DecimalFormat d2curr = new DecimalFormat("###,###.##");

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState)  {
        super.onPostCreate(savedInstanceState);
        setContentView(R.layout.property_info);
    //Declare Variables
        txtStreet = (TextView) findViewById(R.id.txtStreet);
        txtCity = (TextView) findViewById(R.id.txtCity);
        txtArea = (TextView) findViewById(R.id.txtArea);
        txtType = (TextView) findViewById(R.id.txtType);
        txtPrice = (TextView) findViewById(R.id.txtPrice);
        txtSQFT = (TextView) findViewById(R.id.txtSQFT);
        txtRoom = (TextView) findViewById(R.id.txtRoom);
        txtBath = (TextView) findViewById(R.id.txtBath);
        txtHOA = (TextView) findViewById(R.id.txtHOA);
        txtTax = (TextView) findViewById(R.id.txtTax);

        btnDlt = (Button) findViewById(R.id.btnDelete);
        btnLoc = (Button) findViewById(R.id.showLoc);

        getMortgage = (Button) findViewById(R.id.getMortgage);
        monTotal = (TextView) findViewById(R.id.monTotal);
        downPay = (TextView) findViewById(R.id.downPay);

        ckFav = (CheckBox) findViewById(R.id.ckFav);

        dbHandler = new mySQLiteDBHandler(this);

        popData();

        getMortgage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String addr = ShowListings.chosenItem;
                double mPer = 0;
                double tPrice = 0;
                int mHOA = 0;
                double yTax = 0;
                int lLength = 0;
                double dPay = 0;

                Cursor cursor = dbHandler.getList(addr);
                if (cursor.getCount()==0)  {
                    Toast.makeText(getApplicationContext(), "NO DATA", Toast.LENGTH_SHORT).show();
                }
                else {
                    while (cursor.moveToNext()) {
                        tPrice = Integer.parseInt(cursor.getString(cursor.getColumnIndex("price")));
                        mHOA = Integer.parseInt(cursor.getString(cursor.getColumnIndex("hoa")));
                        yTax = Integer.parseInt(cursor.getString(cursor.getColumnIndex("tax")));
                    }
                }

                yTax /= 12;
                mPer = 2.875;  //2.875% interest rate
                mPer /= 100;
                mPer /=12;
                lLength = 30;  //in years
                lLength *=12;
                dPay = tPrice * 0.03;  //3% down payment
                tPrice = tPrice - dPay;

                double mPay = tPrice*(mPer*Math.pow((1+mPer),lLength))/((Math.pow((1+mPer),lLength))-1);  //Mortgage Formula

                mPay = mPay + yTax + mHOA + 175;  //Monthly Payment + $175 additional for padding

                Toast.makeText(getApplicationContext(), "Monthly Payment is:  $" + d2curr.format(mPay), Toast.LENGTH_SHORT).show();
                monTotal.setText("Monthly Payment:  $" + d2curr.format(mPay));
                downPay.setText("3% down payment:  $" + d2curr.format(dPay));

            }
        });

        btnDlt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PropertyInfo.this, MainActivity.class);

                String addr = ShowListings.chosenItem;
                dbHandler.deleteEntry(addr);

                Toast.makeText(getApplicationContext(), "Entry for " + addr + " deleted!",Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });

        //btnLoc will go here

    }

    public void popData() {
        String addr = ShowListings.chosenItem;
        Cursor cursor = dbHandler.getList(addr);
        if (cursor.getCount()==0) {
            Toast.makeText(getApplicationContext(), "NO DATA", Toast.LENGTH_SHORT).show();
            Toast.makeText(getApplicationContext(), addr, Toast.LENGTH_SHORT).show();
        }
        else {
            while (cursor.moveToNext()) {
                txtStreet.setText(cursor.getString(cursor.getColumnIndex("street")));
                txtCity.setText(cursor.getString(cursor.getColumnIndex("city")));
                txtArea.setText(cursor.getString(cursor.getColumnIndex("area")));
                txtPrice.setText(cursor.getString(cursor.getColumnIndex("price")));
                txtSQFT.setText(cursor.getString(cursor.getColumnIndex("sqft")));
                txtRoom.setText(cursor.getString(cursor.getColumnIndex("room")));
                txtBath.setText(cursor.getString(cursor.getColumnIndex("bath")));
                txtHOA.setText(cursor.getString(cursor.getColumnIndex("hoa")));
                txtTax.setText(cursor.getString(cursor.getColumnIndex("tax")));
                txtType.setText(cursor.getString(cursor.getColumnIndex("type")));

                if (cursor.getString(cursor.getColumnIndex("fav")) == true)  {
                    ckFav.setText("unmark as favorite");
                }
                else {
                    ckFav.setText("mark as favorite");
                }
            }
        }

    }


}
