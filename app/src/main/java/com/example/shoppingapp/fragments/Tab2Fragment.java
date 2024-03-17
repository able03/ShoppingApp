package com.example.shoppingapp.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.shoppingapp.MethodsInterface;
import com.example.shoppingapp.R;
import com.google.android.material.button.MaterialButton;


public class Tab2Fragment extends Fragment implements MethodsInterface
{
    private MaterialButton showAletBtn;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tab2, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        initValues();

        showAletBtn.setOnClickListener(show -> {
            AlertDialog.Builder showBtn = new AlertDialog.Builder(getActivity());
            showBtn.setTitle("Alert Dialog");
            showBtn.setMessage("This is an Alert Dialog");
            showBtn.setCancelable(false);
            showBtn.setPositiveButton("Yes", new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface dialog, int which)
                {
                    Toast.makeText(getActivity(), "Yes Clicked", Toast.LENGTH_SHORT).show();
                }
            });

            showBtn.setNegativeButton("No", new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface dialog, int which)
                {
                    Toast.makeText(getActivity(), "No Clicked", Toast.LENGTH_SHORT).show();
                }
            });

            AlertDialog alertDialog = showBtn.create();
            alertDialog.show();


        });
    }

    @Override
    public void initValues()
    {
        showAletBtn = getView().findViewById(R.id.showAlertDialogBtn);
    }

}