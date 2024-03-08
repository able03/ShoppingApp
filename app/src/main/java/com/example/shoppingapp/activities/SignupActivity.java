package com.example.shoppingapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.shoppingapp.DBHelper;
import com.example.shoppingapp.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class SignupActivity extends AppCompatActivity
{
    private TextInputLayout nameLayout, passwordLayout, conPasswordLayout;
    private TextInputEditText name, password, conPassword;
    private DBHelper mainDB;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        initValues();

    }

    private void initValues()
    {
        nameLayout = findViewById(R.id.textInputLayout0);
        passwordLayout = findViewById(R.id.textInputLayout02);
        conPasswordLayout = findViewById(R.id.textInputLayout3);

        name = findViewById(R.id.tiName2);
        password = findViewById(R.id.tiPassword2);
        conPassword = findViewById(R.id.tiConfirmPassword);

        mainDB = new DBHelper(this);

    }

    private boolean validateSignup()
    {
        boolean nameIsEmpty = TextUtils.isEmpty(name.getText().toString());
        boolean passwordIsEmpty = TextUtils.isEmpty(password.getText().toString());
        boolean conPasswordIsEmpty = TextUtils.isEmpty(conPassword.getText().toString());

        String passwordInput = password.getText().toString().trim();
        String conPasswordInput = conPassword.getText().toString().trim();

        if(nameIsEmpty || passwordIsEmpty || conPasswordIsEmpty || !passwordInput.equals(conPasswordInput))
        {
            if(nameIsEmpty)
            {
                nameLayout.setErrorEnabled(true);
                nameLayout.setError("Field can't be empty");
            }

            if(passwordIsEmpty)
            {
                passwordLayout.setErrorEnabled(true);
                passwordLayout.setError("Field can't be empty");
            }

            if(conPasswordIsEmpty){
                conPasswordLayout.setErrorEnabled(true);
                conPasswordLayout.setError("Field can't be empty");
            }

            if(!passwordInput.equals(conPasswordInput))
            {
                conPasswordLayout.setErrorEnabled(true);
                conPasswordLayout.setError("Password does not match");
            }

            return false;
        }

        nameLayout.setErrorEnabled(false);
        passwordLayout.setErrorEnabled(false);
        conPasswordLayout.setErrorEnabled(false);
        return true;
    }

    public void signupBtnSU(View v)
    {
        if(validateSignup())
        {
            String nameInput = name.getText().toString().trim();
            String passwordInput = password.getText().toString().trim();

            if(checkUsernameIfExists(nameInput))
            {
                mainDB.addData(nameInput, passwordInput);
                finish();
            }
        }
    }

    public void loginBtnSU(View v)
    {
//        Intent i = new Intent(SignupActivity.this, LoginActivity.class);
//        startActivity(i);
        finish();
    }

    private boolean checkUsernameIfExists(String username)
    {
        String nameInput = name.getText().toString().trim();
        Cursor cursor = mainDB.readData(nameInput);

        if(cursor.getCount() == 0)
        {
            nameLayout.setErrorEnabled(false);
            return true;
        }
        cursor.close();
        nameLayout.setErrorEnabled(true);
        nameLayout.setError("Username already exists");
        return false;
    }

    @Override
    protected void onDestroy()
    {
        mainDB.close();
        super.onDestroy();
    }
}