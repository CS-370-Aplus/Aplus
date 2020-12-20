package com.example.aplus.login.fragments;

import android.app.Activity;
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
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import java.util.Objects;

public class registerFragment extends Fragment {

    EditText editTextFirstname, editTextLastname, editTextUsername, editTextEmail, editTextPassword, editTextPasswordConfirm, editTextZipCode;
    RadioGroup radioGroupBuyerSeller;
    RadioButton radioButtonSelected;
    Button buttonSignUp;
    TextView textViewLogin;


    public registerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        radioGroupBuyerSeller = view.findViewById(R.id.signup_radioGroup);
        buttonSignUp = view.findViewById(R.id.btnRegister);
        textViewLogin = view.findViewById(R.id.goLogin);

        textViewLogin.setOnClickListener(view1 -> ((LoginActivity) requireActivity()).replaceFragments(0));

        buttonSignUp.setOnClickListener(view12 -> {
            String firstname, lastname, username, email, password, password2, zipcode, type, status;
            firstname = editTextFirstname.getText().toString();
            lastname = editTextLastname.getText().toString();
            username = editTextUsername.getText().toString();
            email = editTextEmail.getText().toString();
            password = editTextPassword.getText().toString();
            password2 = editTextPasswordConfirm.getText().toString();
            zipcode = editTextZipCode.getText().toString();

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
            status = "WAITING CONFIRMATION";


            }

        });
    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager) requireActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}