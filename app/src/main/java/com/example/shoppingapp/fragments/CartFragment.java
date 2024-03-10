package com.example.shoppingapp.fragments;

import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shoppingapp.DBHelper;
import com.example.shoppingapp.ItemDBHelper;
import com.example.shoppingapp.R;
import com.example.shoppingapp.adapters.CartAdapter;
import com.example.shoppingapp.models.ItemCartModel;

import java.util.ArrayList;
import java.util.List;

public class CartFragment extends Fragment
{
    private RecyclerView cartRV;
    private CartAdapter cartAdapter;
    private List<ItemCartModel> itemCarts = new ArrayList<>();
    private DBHelper mainDB;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        cartRV = view.findViewById(R.id.cartRV);
        mainDB = new DBHelper(this.getContext());
        cartAdapter = new CartAdapter(this.getContext());
        addListCartData();

        cartAdapter.setItemCartList(itemCarts);
        cartRV.setAdapter(cartAdapter);
        cartRV.setLayoutManager(new LinearLayoutManager(getActivity()));

    }

    private void addListCartData()
    {
        Cursor cursor = mainDB.readCartData();

        try
        {
            while(cursor.moveToNext())
            {
                Log.d("AddListCartData",": running");
                String cartName = cursor.getString(cursor.getColumnIndexOrThrow("cart_name"));

                itemCarts.add(new ItemCartModel(1, cartName, 10, 10));
            }
        }
        finally
        {
            cursor.close();
        }
    }
}