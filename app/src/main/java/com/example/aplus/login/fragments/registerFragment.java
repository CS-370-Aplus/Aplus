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

        editTextFirstname = view.findViewById(R.id.signup_firstname);
        editTextFirstname.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                hideKeyboard(v);
            }
        });
        editTextLastname = view.findViewById(R.id.signup_lastname);
        editTextLastname.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                hideKeyboard(v);
            }
        });
        editTextUsername = view.findViewById(R.id.signup_username);
        editTextUsername.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                hideKeyboard(v);
            }
        });
        editTextEmail = view.findViewById(R.id.signup_email);
        editTextEmail.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                hideKeyboard(v);
            }
        });
        editTextPassword = view.findViewById(R.id.signup_password);
        editTextPassword.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                hideKeyboard(v);
            }
        });
        editTextPasswordConfirm = view.findViewById(R.id.signup_password2);
        editTextPasswordConfirm.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                hideKeyboard(v);
            }
        });
        editTextZipCode = view.findViewById(R.id.signup_zipcode);
        editTextZipCode.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                hideKeyboard(v);
            }
        });
        radioGroupBuyerSeller = view.findViewById(R.id.signup_radioGroup);
        buttonSignUp = view.findViewById(R.id.btnRegister);
        textViewLogin = view.findViewById(R.id.goLogin);

        textViewLogin.setOnClickListener(view1 -> ((LoginActivity) Objects.requireNonNull(getActivity())).replaceFragments(0));

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

            if(firstname.equals("")){
                editTextFirstname.setError("Enter First Name");
                editTextFirstname.requestFocus();
            }else if(lastname.equals("")){
                editTextLastname.setError("Enter Last Name");
                editTextLastname.requestFocus();
            }else if(username.equals("")){
                editTextUsername.setError("Enter Username");
                editTextUsername.requestFocus();
            }else if(email.equals("")){
                editTextEmail.setError("Enter E-mail Address");
                editTextEmail.requestFocus();
            }else if(password.equals("")){
                editTextPassword.setError("Enter Password");
                editTextPassword.requestFocus();
            }else if(!password.equals(password2)){
                editTextPasswordConfirm.setError("Confirm Password doesn't match");
                editTextPasswordConfirm.requestFocus();
            }else if(zipcode.equals("")){
                editTextZipCode.setError("Enter ZIP Code");
                editTextZipCode.requestFocus();
            }else{
                Handler handler = new Handler(Looper.getMainLooper());

                handler.post(() -> {
                    //Starting Write and Read data with URL
                    //Creating array for parameters
                    String[] field = new String[8];
                    field[0] = "firstname";
                    field[1] = "lastname";
                    field[2] = "username";
                    field[3] = "email";
                    field[4] = "password";
                    field[5] = "zipcode";
                    field[6] = "type";
                    field[7] = "status";

                    String[] data = new String[8];
                    data[0] = firstname;
                    data[1] = lastname;
                    data[2] = username;
                    data[3] = email;
                    data[4] = password;
                    data[5] = zipcode;
                    data[6] = finalType;
                    data[7] = status;
                    PutData putData = new PutData("https://psuwal.com/aplus/DBConnection/signup.php", "POST", field, data);
                    if (putData.startPut()) {
                        if (putData.onComplete()) {
                            String result = putData.getResult();
                            if(result.equals("Sign Up Success")){
                                Toast.makeText(getContext(), result, Toast.LENGTH_SHORT).show();
                                ((LoginActivity) Objects.requireNonNull(getActivity())).replaceFragments(0);
                                editTextFirstname.getText().clear();
                                editTextLastname.getText().clear();
                                editTextUsername.getText().clear();
                                editTextEmail.getText().clear();
                                editTextPassword.getText().clear();
                                editTextPasswordConfirm.getText().clear();
                                editTextZipCode.getText().clear();
                                radioButtonSelected = view.findViewById(R.id.buyerRadioBtn);
                                radioButtonSelected.setChecked(true);
                            }else{
                                Toast.makeText(getContext(), result, Toast.LENGTH_SHORT).show();
                            }
                            Log.i("PutData", result);
                        }
                    }
                });
            }

        });
    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager) Objects.requireNonNull(getActivity()).getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}