package com.example.shoppingapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shoppingapp.models.ItemModel;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.MyViewHolder>
{
    private List<ItemModel> items;

    public void setItems(List<ItemModel> items)
    {
        this.items = items;
    }

    public void setFilteredList(List<ItemModel> filteredList)
    {
        this.items = filteredList;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position)
    {
        holder.itemName.setText(items.get(position).getItemName());
        holder.imageView.setImageResource(items.get(position).getImage());
    }

    @Override
    public int getItemCount()
    {
        return items.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder
    {
        private TextView itemName;
        private ImageView imageView;
        public MyViewHolder(@NonNull View itemView)
        {
            super(itemView);
            itemName = itemView.findViewById(R.id.itemName);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
