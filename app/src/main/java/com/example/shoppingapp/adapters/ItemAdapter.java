package com.example.shoppingapp.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shoppingapp.R;
import com.example.shoppingapp.activities.ItemClickedActivity;
import com.example.shoppingapp.models.ItemModel;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.MyViewHolder>
{
    private List<ItemModel> items;
    private Context context;

    public ItemAdapter(Context context)
    {
        this.context = context;
    }

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

        holder.itemCardView.setOnClickListener(item -> {
            Intent intent = new Intent(context, ItemClickedActivity.class);
            intent.putExtra("name", items.get(position).getItemName());
            intent.putExtra("image",items.get(position).getImage());
            context.startActivity(intent);
        });
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
        private CardView itemCardView;

        public MyViewHolder(@NonNull View itemView)
        {
            super(itemView);
            itemName = itemView.findViewById(R.id.itemName);
            imageView = itemView.findViewById(R.id.imageView);
            itemCardView = itemView.findViewById(R.id.itemCardView);
        }
    }
}
