package com.example.shoppingapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.shoppingapp.models.ItemModel;

import java.util.List;

public class ItemDBHelper extends SQLiteOpenHelper
{
    private List<ItemModel> items;

    public ItemDBHelper(@Nullable Context context)
    {
        super(context, "items.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {

    }
}
