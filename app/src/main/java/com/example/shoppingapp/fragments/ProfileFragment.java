package com.example.shoppingapp.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.shoppingapp.R;
import com.example.shoppingapp.activities.LoginActivity;

public class ProfileFragment extends Fragment
{
    private TextView profile_name;
    private Button logoutButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        initValues();

        //will get the string passed from the login activity
        String name = getActivity().getIntent().getStringExtra("name");
        profile_name.setText(name);

        //logout button
        logoutButton.setOnClickListener(item -> {
            Intent intent = new Intent(this.getContext(), LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        });

    }

    private void initValues()
    {
        profile_name = getView().findViewById(R.id.acc_profile_name);
        logoutButton = getView().findViewById(R.id.logoutBtn);
    }

}