package com.example.shoppingapp.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shoppingapp.R;
import com.example.shoppingapp.adapters.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class LikesFragment extends Fragment
{
    private TabLayout tabLayout;
    private ViewPager2 viewPager2;
    private ViewPagerAdapter viewPagerAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_likes, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        initValues();
        viewPager2.setAdapter(viewPagerAdapter);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener()
        {
            @Override
            public void onTabSelected(TabLayout.Tab tab)
            {
                viewPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab)
            {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab)
            {

            }
        });

       viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback()
       {
           @Override
           public void onPageSelected(int position)
           {
               super.onPageSelected(position);
               tabLayout.getTabAt(position).select();
           }
       });
    }

    private void initValues()
    {
        tabLayout = getView().findViewById(R.id.tabLayout);
        viewPager2 = getView().findViewById(R.id.viewPager2);
        viewPagerAdapter = new ViewPagerAdapter(getActivity());
    }


}