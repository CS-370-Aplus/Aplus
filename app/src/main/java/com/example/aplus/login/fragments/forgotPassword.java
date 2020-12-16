package com.example.aplus.login.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.aplus.R;

public class forgotPassword extends AppCompatActivity {
    EditText editTextemail;
    TextView textViewLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot_password);

        editTextemail = findViewById(R.id.recovery_email);
        editTextemail.setOnFocusChangeListener((v, hasFocus) -> {
            if(!hasFocus){
                hideKeyboard(v);
            }
        });

        textViewLogin = findViewById(R.id.gotologin);
        textViewLogin.setOnClickListener(view->{
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        });

    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
