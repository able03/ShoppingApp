package com.example.shoppingapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
        boolean nameIsEmpty = name.getText().toString().isEmpty();
        boolean passwordIsEmpty = password.getText().toString().isEmpty();
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
        String nameInput = name.getText().toString().trim();
        String passwordInput = password.getText().toString().trim();

        if(validateLogin())
        {
           if(checkLogin(nameInput, passwordInput))
           {
               Intent i = new Intent(LoginActivity.this, MainActivity.class);
               startActivity(i);
           }
           else
           {

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
        Cursor cursor = mainDB.readData(name, password);
        boolean checkLogin = false;

        while(cursor.moveToNext())
        {
            String nameDB = cursor.getString(cursor.getColumnIndexOrThrow("username"));
            String passwordDB = cursor.getString(cursor.getColumnIndexOrThrow("password"));
            boolean nameIsMatched = nameDB.equals(name);
            boolean passwordIsMatched = passwordDB.equals(password);

            if(!nameIsMatched || !passwordIsMatched)
            {
                if(!nameIsMatched)
                {
                    nameLayout.setErrorEnabled(true);
                    nameLayout.setError("Wrong username");
                }

                if(!passwordIsMatched)
                {
                    passwordLayout.setErrorEnabled(true);
                    passwordLayout.setError("Wrong password");
                }
                return false;

            }

        }
        nameLayout.setErrorEnabled(false);
        passwordLayout.setErrorEnabled(false);
        return true;
    }
}