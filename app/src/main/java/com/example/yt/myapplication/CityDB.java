package com.example.yt.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class CityDB {
    public static final String CITY_DB_NAME ="city.db";
    private static String CITY_TABLE_NAME="ctiy";
    private SQLiteDatabase db;
    public CityDB(Context context,String path){
        db=context.openOrCreateDatabase(CITY_DB_NAME,Context.MODE_PRIVATE,null);
    }
    public List<City>getCityList(){
        List<City> list=new ArrayList<>();
        Cursor cursor=db.rawQuery("SELECT*FROM"+CITY_TABLE_NAME,null);
        while (cursor.moveToNext()){
           String province=cursor.getString(cursor.getColumnIndex("province"));
           String city=cursor.getString(cursor.getColumnIndex("city"));
           String number=cursor.getString(cursor.getColumnIndex("number"));
           String allPY=cursor.getString(cursor.getColumnIndex("allPY"));
           String allFirstPY=cursor.getString(cursor.getColumnIndex("allFirstPY"));
           String firstPY=cursor.getString(cursor.getColumnIndex("firstPY"));
           City item=new City(province,city,number,allFirstPY,allPY,firstPY);
           list.add(item);
        }
        return list;
    }
}
