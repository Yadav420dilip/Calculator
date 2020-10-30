package com.example.calculator;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "calculation_history";
    final String Statement = "Statement";
    final String Result_stmt = "Result_stmt";
    private static final String Table_name = "History";



    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }


    // Creating TablesS
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE IF NOT EXISTS " + Table_name + " ("+ " _id" +
                " INTEGER PRIMARY KEY, " + Statement + " Text," +Result_stmt + " TEXT "+")";

        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " +"History" );

        onCreate(db);

    }


    void add_result(String firstvalue, String secondvalue) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("Statement", firstvalue);
        values.put("Result_stmt", secondvalue);


        db.insert(Table_name, null, values);

        db.close();
    }


    public Cursor getAllContacts() {

        String selectQuery = "SELECT  * FROM " + Table_name;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);



        // return contact list
        return cursor;
    }
    public void delete_contact()
    {
        String selectQuery = "DELETE  FROM " + Table_name;

        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(selectQuery);
    }




}
