package com.example.shoppingapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shoppingapp.DBHelper;
import com.example.shoppingapp.R;

public class ItemClickedActivity extends AppCompatActivity
{
    private TextView itemNameClicked;
    private ImageView itemImageClicked;
    private Button button;
    private boolean isAddedToCart;
    private DBHelper db;
    private static String name;
    private static int image;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_clicked);
        initValues();
        setExtraData();

    }

    private void initValues()
    {
        itemNameClicked = findViewById(R.id.itemNameClicked);
        itemImageClicked = findViewById(R.id.itemImageClicked);
        button = findViewById(R.id.addToCartBtn);
        db = new DBHelper(this);
    }

    private void setExtraData()
    {
        name = this.getIntent().getStringExtra("name");
        image = this.getIntent().getIntExtra("image", 0);

        itemNameClicked.setText(name);
        itemImageClicked.setImageResource(image);
    }

    public void backBtn(View v)
    {
        finish();
    }

    public void addToCartBtn(View view)
    {
        db.addCartData(name);
        finish();
    }


}