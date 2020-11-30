package com.example.aplus.login.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.aplus.LoginActivity;
import com.example.aplus.R;

public class registerFragment extends Fragment {

    EditText editTextFirstname, editTextLastname, editTextUsername, editTextEmail, editTextPassword, editTextPasswordConfirm;
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

        editTextFirstname = view.findViewById(R.id.login_firstname);
        editTextLastname = view.findViewById(R.id.login_lastname);
        editTextUsername = view.findViewById(R.id.login_username);
        editTextEmail = view.findViewById(R.id.login_email);
        editTextPassword = view.findViewById(R.id.login_password);
        editTextPasswordConfirm = view.findViewById(R.id.login_password2);
        radioGroupBuyerSeller = view.findViewById(R.id.radioGroup);
        buttonSignUp = view.findViewById(R.id.btnRegister);
        textViewLogin = view.findViewById(R.id.goLogin);

        textViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((LoginActivity) getActivity()).replaceFragments(0);
            }
        });

        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

}