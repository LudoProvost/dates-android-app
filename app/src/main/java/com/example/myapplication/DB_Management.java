package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class DB_Management extends SQLiteOpenHelper {

    SQLiteDatabase db;

    public DB_Management(Context context) {

        super(context,"Login.db", null,1);
        db = getWritableDatabase();
    }


    @Override
    public void onCreate(SQLiteDatabase myDB) {
        myDB.execSQL("CREATE TABLE dates(" +
                "dateID INTEGER PRIMARY KEY, " +
                "dateName TEXT," +
                "dateDescription TEXT," +
                "isRelax BOOLEAN," +
                "isExpensive BOOLEAN," +
                "isOutside BOOLEAN)");

        myDB.execSQL("CREATE TABLE datesCompleted(" +
                "dateID INTEGER PRIMARY KEY, " +
                "dateName TEXT," +
                "dateDescription TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase myDB, int i, int i1) {
        myDB.execSQL("drop Table if exists users");

        }

    public Boolean insertNewDate(int dateID, String dateName, String dateDescription, boolean isRelax, boolean isExpensive, boolean isOutside) {
        ContentValues insertValues = new ContentValues();
        insertValues.put("dateID",dateID);
        insertValues.put("dateName",dateName);
        insertValues.put("dateDescription",dateDescription);
        insertValues.put("isRelax",isRelax);
        insertValues.put("isExpensive",isExpensive);
        insertValues.put("isOutside",isOutside);

        long dates = db.insert("dates", null, insertValues);

        return (dates != -1);
    }

    public List<String> getAllDateIDs() {

        List<String> list = new ArrayList<String>();
        String selectQuery = "SELECT  * FROM dates";
        Cursor cursor = db.rawQuery(selectQuery, null);


        if (cursor.moveToFirst()) {
            do {
                list.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }

    public String getDateNameFromDateID(String dateID) {

        String dateName = "";
        String selectQuery = "SELECT  * FROM dates";
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                if (cursor.getString(0).equals(dateID)) {
                    dateName = cursor.getString(1);
                    return dateName;
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        return dateName;
    }

    public String getDateDescFromDateName(String dateName) {

        String dateDescription = "";
        String selectQuery = "SELECT  * FROM dates";
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                if (cursor.getString(1).equals(dateName)) {
                    dateDescription = cursor.getString(2);
                    return dateDescription;
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        return dateDescription;
    }

    public String getDateIDFromDateName(String dateName) {

        String dateID = "";
        String selectQuery = "SELECT  * FROM dates";
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                if (cursor.getString(1).equals(dateName)) {
                    dateID = cursor.getString(0);
                    return dateID;
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        return dateID;
    }

    public boolean getIsRelaxFromDateName(String dateName) {

        String selectQuery = "SELECT  * FROM dates";
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                if (cursor.getString(1).equals(dateName)) {
                    return cursor.getInt(3) != 0 && !cursor.isNull(3);
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        return false;
    }

    public boolean getIsExpensiveFromDateName(String dateName) {

        String selectQuery = "SELECT  * FROM dates";
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                if (cursor.getString(1).equals(dateName)) {
                    return cursor.getInt(4) != 0 && !cursor.isNull(4);
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        return false;
    }

    public boolean getIsOutsideFromDateName(String dateName) {

        String selectQuery = "SELECT  * FROM dates";
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                if (cursor.getString(1).equals(dateName)) {
                    return cursor.getInt(5) != 0 && !cursor.isNull(5);
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        return false;
    }

    public Boolean updateDate(String dateName, String dateDescription, int isRelax, int isExpensive, int isOutside){

        String query = "UPDATE dates" +
                " SET dateDescription = '" + dateDescription + "', isRelax = '" + isRelax + "', isExpensive = " + isExpensive + ", isOutside = '" + isOutside +
                "' WHERE dateID = " + this.getDateIDFromDateName(dateName);
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            cursor.close();
            return true;
        }else{
            cursor.close();
            return false;
        }
    }

    public Boolean deleteDate(String dateName){

        String query = "DELETE FROM dates WHERE dateID =" + this.getDateIDFromDateName(dateName);
        Cursor cursor = db.rawQuery(query, null);
        Boolean result = cursor.getCount() <= 0;

        cursor.close();
        return result;
    }

    public Boolean checkSameDateName(String dateName){

        String selectQuery = "SELECT  * FROM dates";
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                if (cursor.getString(1).equals(dateName)) {
                    return true;
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        return false;
    }
}
