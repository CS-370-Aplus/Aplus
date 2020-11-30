package com.example.aplus.login.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import androidx.fragment.app.Fragment;

import com.example.aplus.R;

public class registerFragment extends Fragment {

    EditText editTextFirstname, editTextLastname, editTextUsername, editTextEmail, editTextPassword, editTextPasswordConfirm;
    RadioGroup radioGroupBuyerSeller;
    Button buttonSignUp;


    public registerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_register, container, false);

        editTextFirstname = view.findViewById(R.id.login_firstname);
        editTextLastname = view.findViewById(R.id.login_lastname);
        editTextUsername = view.findViewById(R.id.login_username);
        editTextEmail = view.findViewById(R.id.login_email);
        editTextPassword = view.findViewById(R.id.login_password);
        editTextPasswordConfirm = view.findViewById(R.id.login_password2);


        // Inflate the layout for this fragment
        return view;
    }

}