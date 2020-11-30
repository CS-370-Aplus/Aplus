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

    View v;
    EditText loginEmail, loginPassword;
    Button loginBtn;

    public loginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_login, container, false);
        loginEmail = v.findViewById(R.id.login_email);
        loginPassword = v.findViewById(R.id.login_password);
        loginBtn = v.findViewById(R.id.login);
        loginBtn.setOnClickListener(view -> {
            String username = loginEmail.getText().toString();
            String password = loginPassword.getText().toString();
            String type = "login";

            LoginWorker loginWorker = new LoginWorker(v.getContext());
            loginWorker.execute(type, username, password);
        });

        loginEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                loginBtn.setEnabled(charSequence.length() > 0 && loginPassword.getText().length() > 0);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                loginBtn.setEnabled(charSequence.length() > 0 && loginPassword.getText().length() > 0);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        loginPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                loginBtn.setEnabled(charSequence.length() > 0 && loginEmail.getText().length() > 0);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                loginBtn.setEnabled(charSequence.length() > 0 && loginEmail.getText().length() > 0);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        return v;
    }
}