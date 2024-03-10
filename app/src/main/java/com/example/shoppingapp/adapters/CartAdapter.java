package com.example.shoppingapp.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shoppingapp.DBHelper;
import com.example.shoppingapp.R;
import com.example.shoppingapp.models.ItemCartModel;
import com.example.shoppingapp.models.ItemModel;

import java.util.ArrayList;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyViewHolder>
{
    private List<ItemCartModel> itemCartList;
    private DBHelper dbHelper;
    private Context context;

    public CartAdapter(Context context)
    {
        this.context = context;
    }

    public void setItemCartList(List<ItemCartModel> itemCartList)
    {
        this.itemCartList = itemCartList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_rv_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position)
    {
        dbHelper = new DBHelper(context);

        holder.itemCartName.setText(itemCartList.get(position).getName());

        holder.deleteCartItem.setOnClickListener(item -> {
            dbHelper.deleteCartData(itemCartList.get(position).getName());
            itemCartList.remove(itemCartList.get(position));
            notifyDataSetChanged();

        });
//
//        holder.itemCartCardView.setOnClickListener(item -> {
//            Log.d("cart", "cart clicked");
//        });
    }

    @Override
    public int getItemCount()
    {
        return itemCartList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder
    {
        private final TextView itemNumber, itemCartName;
        private final Button deleteCartItem;
        private final CardView itemCartCardView;

        public MyViewHolder(@NonNull View itemView)
        {
            super(itemView);

            itemNumber = (TextView) itemView.findViewById(R.id.itemNumber);
            itemCartName = (TextView) itemView.findViewById(R.id.itemNumber);
            deleteCartItem = (Button) itemView.findViewById(R.id.deleteCartItemBtn);
            itemCartCardView = (CardView) itemView.findViewById(R.id.itemCardView);
        }
    }

}
