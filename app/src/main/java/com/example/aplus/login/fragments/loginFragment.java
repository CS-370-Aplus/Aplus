package com.example.aplus.login.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.aplus.R;
import com.example.aplus.home.home_activity;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class loginFragment extends Fragment {

    EditText editTextUsername, editTextPassword;
    RadioGroup radioGroupBuyerSeller;
    RadioButton radioButtonSelected;
    Button buttonLogin;
    TextView textViewSignup, textViewForgotPassword;

    public loginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        editTextUsername = view.findViewById(R.id.login_username);
        editTextUsername.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View v, boolean hasFocus){
                if(!hasFocus){
                    hideKeyboard(v);
                }
            }
        });

        editTextPassword = view.findViewById(R.id.login_password);
        editTextPassword.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View v,boolean hasFocus){
                if(!hasFocus){
                    hideKeyboard(v);
                }
            }
        });

        radioGroupBuyerSeller = view.findViewById(R.id.login_radioGroup);
        buttonLogin = view.findViewById(R.id.btnLogin);
        textViewSignup = view.findViewById(R.id.goSignup);
        textViewForgotPassword = view.findViewById(R.id.forgotpasswordtext);

        textViewSignup.setOnClickListener(view1 -> ((LoginActivity) requireActivity()).replaceFragments(1));

        textViewForgotPassword.setOnClickListener(view1-> {
            Intent intent = new Intent(getContext(), forgotPassword.class);
            startActivity(intent);
        });

        buttonLogin.setOnClickListener(view12 -> {
            String username, password, type;
            username = editTextUsername.getText().toString();
            password = editTextPassword.getText().toString();

            radioButtonSelected = view.findViewById(R.id.buyerRadioBtn);
            type = radioButtonSelected.getText().toString();

            switch(radioGroupBuyerSeller.getCheckedRadioButtonId()){
                case R.id.buyerRadioBtn:
                    radioButtonSelected = view.findViewById(R.id.buyerRadioBtn);
                    type = radioButtonSelected.getText().toString();
                    break;
                case R.id.sellerRadioBtn:
                    radioButtonSelected = view.findViewById(R.id.sellerRadioBtn);
                    type = radioButtonSelected.getText().toString();
                    break;
            }

            if(type.equals("Buyer")){
                type = "B";
            }else if(type.equals("Seller")){
                type = "S";
            }

            String finalType = type;

            if(username.equals("")){
                editTextUsername.setError("Enter your Username");
                editTextUsername.requestFocus();
            }else if(password.equals("")){
                editTextPassword.setError("Enter your Password");
                editTextPassword.requestFocus();
            }else{
                Handler handler = new Handler(Looper.getMainLooper());

                handler.post(new Runnable(){
                    @Override
                    public void run(){
                        String field[] = new String[3];
                        field[0] = "username";
                        field[1] = "password";
                        field[2] = "type";

                        String data[] = new String[3];
                        data[0] = username;
                        data[1] = password;
                        data[2] = finalType;

                        PutData putData = new PutData("https://www.psuwal.com/aplus/DBConnection/login.php", "POST", field, data);
                        if (putData.startPut()) {
                            if (putData.onComplete()) {
                                String result = putData.getResult();
                                if(result.contains("Logged in")){
                                    String passUsername = result.substring(result.lastIndexOf(" ")+1);
                                    Toast.makeText(getContext(), result, Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getContext(), home_activity.class);
                                    intent.putExtra("SESSION_USERNAME", passUsername);
                                    startActivity(intent);
                                    editTextUsername.getText().clear();
                                    editTextPassword.getText().clear();
                                    radioButtonSelected = view.findViewById(R.id.buyerRadioBtn);
                                    radioButtonSelected.setChecked(true);
                                }else{
                                    Toast.makeText(getContext(), result, Toast.LENGTH_SHORT).show();
                                }
                                Log.i("PutData", result);
                            }
                        }

                    }
                });
            }

        });
    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}