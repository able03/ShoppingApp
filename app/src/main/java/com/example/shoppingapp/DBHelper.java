package com.example.shoppingapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper
{
    private final static String DB_NAME = "customer.db";
    private final static int DB_VER = 1;
    private final Context context;
    private final String TABLE_NAME = "customer";
    private final String COLUMN_ID = "id";
    private final String COLUMN_NAME = "username";
    private final String COLUMN_PASSWORD = "password";
    private final String CREATE_DB_QUERY = "CREATE TABLE " + TABLE_NAME + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_NAME + " VARCHAR(50), " + COLUMN_PASSWORD + " VARCHAR(100))";
    private final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME + "";

    public DBHelper(@Nullable Context context)
    {
        super(context, DB_NAME, null, DB_VER);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(CREATE_DB_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }

    public void addData(String name, String password)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_PASSWORD, password);

        long rowAffected = db.insert(TABLE_NAME, null, values);

        if(rowAffected != -1)
        {
            Toast.makeText(context, "Add success", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(context, "Add failed", Toast.LENGTH_SHORT).show();
        }
    }

    public Cursor readData(String name)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        
        String[] columns = {COLUMN_NAME};
        String selection = COLUMN_NAME + " = ?";
        String[] selectionArgs = {name};

        return db.query(TABLE_NAME, columns, selection, selectionArgs, null, null, null);
    }

    public Cursor readData(String name, String password)
    {
        SQLiteDatabase db = this.getReadableDatabase();

        String[] columns = {COLUMN_NAME, COLUMN_PASSWORD};
        String selection = COLUMN_NAME + " = ? and " + COLUMN_PASSWORD + " = ?";
        String[] selectionArgs = {name, password};

        return db.query(TABLE_NAME, columns, selection, selectionArgs, null, null, null);
    }


}
