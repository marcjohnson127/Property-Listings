package com.example.propertylistings;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class mySQLiteDBHandler extends SQLiteOpenHelper {

//Initialize Database
    public static final String MARKET = "Market";
    public static final String prop_list = "property_list";

    mySQLiteDBHandler(Context context) {super(context,MARKET, null, 1);}

    @Override
    public void onCreate(SQLiteDatabase db)  {
    //Create Table
        String createTable = "CREATE TABLE " + prop_list + "(id INTEGER PRIMARY KEY, street TEXT, city TEXT, area TEXT, room INTEGER, bath FLOAT, sqft INTEGER, price INTEGER, hoa INTEGER, tax INTEGER, type TEXT, fav BOOLEAN DEFAULT 0)";
        db.execSQL(createTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)  {
    //Drop older table if exists
        db.execSQL("DROP TABLE IF EXISTS " + prop_list);
        onCreate(db);

    }

//Function to Add New Listing
    public boolean addData(String street, String city, String area, int room, float bath, int sqft, int price, int hoa, int tax, String type)  {
    //Get WriteAble Database
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
    //Create ContentValues
        ContentValues contentValues = new ContentValues();
        contentValues.put("street", street);
        contentValues.put("city", city);
        contentValues.put("area", area);
        contentValues.put("room", room);
        contentValues.put("bath", bath);
        contentValues.put("sqft", sqft);
        contentValues.put("price", price);
        contentValues.put("hoa", hoa);
        contentValues.put("tax", tax);
        contentValues.put("type", type);
    //Add Values into Database
        sqLiteDatabase.insert(prop_list, null, contentValues);
        return true;

    }

//Array Lists to display data on Property Info page
    public ArrayList allData()  {
    //Get ReadAble Database
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        ArrayList<String> arrayList = new ArrayList<String>();
    //Create Cursor to Select All Values
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + prop_list, null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()) {
            arrayList.add(cursor.getString(cursor.getColumnIndex("street")));
            cursor.moveToNext();
        }
        return arrayList;

    }

    public ArrayList allData2()  {
    //Get ReadAble Database
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        ArrayList<String> arrayList = new ArrayList<String>();
    //Create Cursor to Select All Values
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + prop_list, null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()) {
            arrayList.add(cursor.getString(cursor.getColumnIndex("area")));
            cursor.moveToNext();
        }
        return arrayList;

    }

    public Cursor getList(String addr) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + prop_list + " WHERE street = '" + addr + "'", null);
        return cursor;

    }

    public void deleteEntry(String addr)  {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String dltEntry = "DELETE FROM " + prop_list + " WHERE street = '" + addr + "'";
        sqLiteDatabase.execSQL(dltEntry);

    }

    public void markFav(String addr)  {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String markFav = "UPDATE " + MARKET + " SET fav = true WHERE street = '" + addr + "'";
        sqLiteDatabase.execSQL(markFav);

    }

    public void unmarkFav(String addr)  {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String unmarkFav = "UPDATE " + MARKET + " SET fav = false WHERE street = '" + addr + "'";
        sqLiteDatabase.execSQL(unmarkFav);

    }


}
