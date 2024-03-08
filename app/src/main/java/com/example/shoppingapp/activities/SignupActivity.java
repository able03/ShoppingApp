package com.example.shoppingapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
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

    }

    private boolean validateSignup()
    {
        boolean nameIsEmpty = name.getText().toString().isEmpty();
        boolean passwordIsEmpty = password.getText().toString().isEmpty();
        boolean conPasswordIsEmpty = conPassword.getText().toString().isEmpty();

        if(nameIsEmpty || passwordIsEmpty || conPasswordIsEmpty)
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

            if(conPasswordIsEmpty)
            {
                conPasswordLayout.setErrorEnabled(true);
                conPasswordLayout.setError("Field can't be empty");
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
        Log.d("SignupActivity", "signupBtnSU running");
        mainDB = new DBHelper(SignupActivity.this);

        if(validateSignup())
        {
            String nameInput = name.getText().toString().trim();
            String passwordInput = password.getText().toString().trim();

            if(checkUsernameIfExists(nameInput))
            {
                mainDB.addData(nameInput, passwordInput);

                Toast.makeText(this, "Sign up success", Toast.LENGTH_SHORT).show();

                finish();
            }
            else
            {
                Toast.makeText(this, "FUCKING FAILED PUTA", Toast.LENGTH_SHORT).show();
            }
        }

    }

    public void loginBtnSU(View v)
    {
        Intent i = new Intent(SignupActivity.this, LoginActivity.class);
        startActivity(i);
    }

    private boolean checkUsernameIfExists(String username)
    {
        mainDB = new DBHelper(SignupActivity.this);
        Cursor cursor = mainDB.readData(username);
        String name = cursor.getString(cursor.getColumnIndexOrThrow("username"));

        if(!name.equals(username))
        {
            nameLayout.setErrorEnabled(false);
            return true;
        }

        nameLayout.setErrorEnabled(true);
        nameLayout.setError("Username already exist");
        cursor.close();
        return false;
    }

    @Override
    protected void onDestroy()
    {
        mainDB.close();
        super.onDestroy();
    }
}