package com.example.shoppingapp.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.shoppingapp.activities.ItemClickedActivity;
import com.example.shoppingapp.activities.MainActivity;
import com.example.shoppingapp.adapters.ItemAdapter;
import com.example.shoppingapp.R;
import com.example.shoppingapp.models.ItemModel;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment
{
    private SearchView searchView;
    private List<ItemModel> itemList = new ArrayList<>();
    private ItemAdapter itemAdapter;
    private RecyclerView recyclerView;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.fragment_home, container, false);

        // Inflate the layout for this fragment
       return view;
    }

//    private void initValues()
//    {
//        searchView = (SearchView) getActivity().findViewById(R.id.search_view);
//        itemList = new ArrayList<>();
//        itemAdapter = new ItemAdapter(getContext());
//        recyclerView = (RecyclerView) getActivity().findViewById(R.id.itemRV);
//    }

    private void filteredList(String newText)
    {
        List<ItemModel> filteredList = new ArrayList<>();
        for(ItemModel item : itemList)
        {
            if(item.getItemName().toLowerCase().contains(newText.toLowerCase()))
            {
                filteredList.add(item);
            }
        }
        
        if(filteredList.isEmpty())
        {
            Toast.makeText(getView().getContext(), "No data found", Toast.LENGTH_SHORT).show();
        }

        else
        {
            itemAdapter.setFilteredList(filteredList);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        searchView = view.findViewById(R.id.search_view);
        searchView.clearFocus();
        recyclerView = view.findViewById(R.id.itemRV);
        itemAdapter = new ItemAdapter(this.getContext());

        int img = R.drawable.ic_item1;

        itemList.add(new ItemModel(1, "Corn", 12, 12.00, img));
        itemList.add(new ItemModel(2, "Sundae", 4, 12.00, img));
        itemList.add(new ItemModel(3, "Adobo", 2, 12.00, img));
        itemList.add(new ItemModel(4, "BBQ", 22, 12.00, img));
        itemList.add(new ItemModel(5, "Mango", 32, 12.00, img));
        itemList.add(new ItemModel(6, "Hotdog", 62, 12.00, img));


        itemAdapter.setItems(itemList);
        recyclerView.setAdapter(itemAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(view.getContext(), 2));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener()
        {
            @Override
            public boolean onQueryTextSubmit(String query)
            {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText)
            {
                filteredList(newText);
                return true;
            }
        });


    }
}