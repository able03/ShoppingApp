package com.example.shoppingapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.shoppingapp.DBHelper;
import com.example.shoppingapp.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity
{
    private TextInputEditText name, password;
    private TextInputLayout nameLayout, passwordLayout;
    private Button login, signup;
    private DBHelper mainDB;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initValues();

    }


    private void initValues()
    {
        name = findViewById(R.id.tiName);
        password = findViewById(R.id.tiPassword);

        nameLayout = findViewById(R.id.textInputLayout);
        passwordLayout = findViewById(R.id.textInputLayout2);

        login = (Button) findViewById(R.id.loginBtn);
        signup = (Button) findViewById(R.id.signupBtn);

        mainDB = new DBHelper(LoginActivity.this);
    }

    private boolean validateLogin()
    {
        boolean nameIsEmpty = TextUtils.isEmpty(name.getText().toString().trim());
        boolean passwordIsEmpty = TextUtils.isEmpty(password.getText().toString().trim());

        if(nameIsEmpty || passwordIsEmpty)
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

            return false;
        }

        nameLayout.setErrorEnabled(false);
        passwordLayout.setErrorEnabled(false);
        return true;
    }

    public void loginBtn(View v)
    {
        //check if the fields are empty
        if(validateLogin())
        {
            String nameInput = name.getText().toString().trim();
            String passwordInput = password.getText().toString().trim();

            //check if the name and password are matched to an existing
            //account in the database
            if(checkLogin(nameInput, passwordInput))
            {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);

                //will pass the name in the profile fragment
                intent.putExtra("name", nameInput);
                startActivity(intent);
            }
        }
    }

    public void signupBtn(View v)
    {
        Intent i = new Intent(LoginActivity.this, SignupActivity.class);
        startActivity(i);
    }

    private boolean checkLogin(String name, String password)
    {
        //cursor will return zero (0) if there
        //is no account existing in the database
        Cursor cursor = mainDB.readData(name, password);
        if(cursor.getCount() != 0)
        {
            nameLayout.setErrorEnabled(false);
            return true;
        }
        cursor.close();
        nameLayout.setErrorEnabled(true);
        nameLayout.setError("No user found");
        return false;
    }
}