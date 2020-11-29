package com.example.aplus;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

public class loginFragment extends Fragment {


    public loginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_login, container, false);
        final EditText loginEmail = v.findViewById(R.id.login_email);
        final EditText loginPassword = v.findViewById(R.id.login_password);
        final Button loginBtn = v.findViewById(R.id.login);

        loginEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.length() == 0){
                    loginBtn.setEnabled(false);
                }
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                loginBtn.setEnabled(charSequence.length() != 0);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        loginPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.length() == 0){
                    loginBtn.setEnabled(false);
                }
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                loginBtn.setEnabled(charSequence.length() != 0);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });




        return v;
    }

}