package com.example.shoppingapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.shoppingapp.models.ItemModel;

import java.util.List;

public class ItemDBHelper extends SQLiteOpenHelper
{
    private List<ItemModel> items;
    private final String TABLE_NAME = "item";
    private final String COLUMN_ID = "id";
    private final String COLUMN_NAME = "name";
    private final String COLUMN_QTY = "quantity";
    private final String COLUMN_PRICE = "price";
    private Context context;


    public ItemDBHelper(@Nullable Context context)
    {
        super(context, "items.db", null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String query = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "  +
                COLUMN_NAME + " VARCHAR(50), " + COLUMN_QTY + " INTEGER, " + COLUMN_PRICE + " DOUBLE);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        String query = "DROP TABLE IF EXISTS " + TABLE_NAME;
        onCreate(db);
    }

    public void addCartData(String name, int qty, double price)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_QTY, qty);
        values.put(COLUMN_PRICE, price);

        long i = db.insert(TABLE_NAME, null, values);
        if(i != -1)
        {
            Toast.makeText(context, "Add success", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(context, "Add failed", Toast.LENGTH_SHORT).show();
        }
    }

    public Cursor readCartData()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_ID, COLUMN_NAME, COLUMN_QTY, COLUMN_PRICE};

        return db.query(TABLE_NAME, columns, null, null, null, null, null);
    }
}
