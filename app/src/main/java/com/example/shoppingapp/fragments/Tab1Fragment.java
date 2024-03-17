package com.example.shoppingapp.fragments;

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
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.button.MaterialButton;

public class Tab1Fragment extends Fragment implements MethodsInterface
{

    private MaterialButton showBtn, dismissBtn;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tab1, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        initValues();
        showBtn.setOnClickListener(show -> {
            BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getActivity());
            View view1 = LayoutInflater.from(getActivity()).inflate(R.layout.bottom_sheet_layout, null);
            bottomSheetDialog.setContentView(view1);
            bottomSheetDialog.show();

            dismissBtn.setOnClickListener(dismiss -> {
                bottomSheetDialog.dismiss();
            });

            bottomSheetDialog.setOnDismissListener(dismissBtmSheet -> {
                Toast.makeText(getActivity(), "Bottom Sheet Dialog Dismissed", Toast.LENGTH_SHORT).show();
            });
        });


    }

    @Override
    public void initValues()
    {
        showBtn = getView().findViewById(R.id.showBtn);
        dismissBtn = getView().findViewById(R.id.showBtn);
    }
}