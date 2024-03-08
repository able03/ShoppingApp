package com.example.shoppingapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.shoppingapp.R;
import com.example.shoppingapp.fragments.CartFragment;
import com.example.shoppingapp.fragments.HomeFragment;
import com.example.shoppingapp.fragments.LikesFragment;
import com.example.shoppingapp.fragments.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity
{
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initValues();

        replaceFragment(new HomeFragment());
        bottomNavigationView.setOnItemSelectedListener(item ->
        {
            int id = item.getItemId();

            if(id == R.id.home)
            {
                replaceFragment(new HomeFragment());
            }
            else if(id == R.id.likes)
            {
                replaceFragment(new LikesFragment());
            }
            else if(id == R.id.cart)
            {
                replaceFragment(new CartFragment());
            }
            else if(id == R.id.profile)
            {
                replaceFragment(new ProfileFragment());
            }

            return true;
        });
    }

    private void initValues()
    {
        //bottom app bar
        bottomNavigationView = findViewById(R.id.bottomNav);
    }

    private void replaceFragment(Fragment fragment)
    {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }


}