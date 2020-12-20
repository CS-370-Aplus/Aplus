package com.example.aplus.home.ui.profile;

import android.app.Activity;
import android.content.ContentProviderClient;
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
import android.webkit.WebView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;

import com.example.aplus.R;
import com.example.aplus.home.home_activity;
import com.example.aplus.login.fragments.LoginActivity;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class ProfileFragment extends Fragment {
    String sessionUser;

    View profileloginlayout, profileviewlayout;
    Button buttonLogin, buttonSignup, buttonprofilesave, buttonLogout;

    EditText editTextfirstname, editTextlastname, editTextusername, editTextemail, editTextzipcode;
    SwitchCompat switchEditinfo;

    String firstname, lastname, username, email, zipcode, userid;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

            }
            });



            profileviewlayout.setVisibility(View.VISIBLE);
        }
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        buttonLogin = view.findViewById(R.id.btnlogin);
        buttonLogin.setOnClickListener(view1 -> {
            Intent intent = new Intent(getContext(), LoginActivity.class);
            intent.putExtra("SESSION_PAGE", "0");
            startActivity(intent);
        });

        buttonSignup = view.findViewById(R.id.btnsignup);
        buttonSignup.setOnClickListener(view1 -> {
            Intent intent = new Intent(getContext(), LoginActivity.class);
            intent.putExtra("SESSION_PAGE", "1");
            startActivity(intent);
        });

        /* Profile Display Part*/

        editTextfirstname = view.findViewById(R.id.profilefirstname);
        editTextfirstname.setEnabled(false);
        editTextfirstname.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View v,boolean hasFocus){
                if(!hasFocus){
                    hideKeyboard(v);
                }
            }
        });

        editTextlastname = view.findViewById(R.id.profilelastname);
        editTextlastname.setEnabled(false);
        editTextlastname.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View v,boolean hasFocus){
                if(!hasFocus){
                    hideKeyboard(v);
                }
            }
        });

        editTextusername = view.findViewById(R.id.profileusername);
        editTextusername.setEnabled(false);
        editTextusername.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View v,boolean hasFocus){
                if(!hasFocus){
                    hideKeyboard(v);
                }
            }
        });

        editTextemail = view.findViewById(R.id.profileemail);
        editTextemail.setEnabled(false);
        editTextemail.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View v,boolean hasFocus){
                if(!hasFocus){
                    hideKeyboard(v);
                }
            }
        });

        editTextzipcode = view.findViewById(R.id.profilezipcode);
        editTextzipcode.setEnabled(false);
        editTextzipcode.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View v,boolean hasFocus){
                if(!hasFocus){
                    hideKeyboard(v);
                }
            }
        });

        buttonprofilesave = view.findViewById(R.id.btnprofilesave);
        buttonprofilesave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String efirstname, elastname, eusername, eemail, ezipcode;
                efirstname = editTextfirstname.getText().toString();
                elastname = editTextlastname.getText().toString();
                eusername = editTextusername.getText().toString();
                eemail = editTextemail.getText().toString();
                ezipcode = editTextzipcode.getText().toString();

                if(efirstname.equals("")){
                    editTextfirstname.setError("Firstname can't be EMPTY");
                    editTextfirstname.requestFocus();
                }else if(elastname.equals("")){
                    editTextlastname.setError("Lastname can't be EMPTY");
                    editTextlastname.requestFocus();
                }else if(eusername.equals("")){
                    editTextusername.setError("Username can't be EMPTY");
                    editTextusername.requestFocus();
                }else if(eemail.equals("")){
                    editTextemail.setError("E-mail can't be EMPTY");
                    editTextemail.requestFocus();
                }else if(ezipcode.equals("")){
                    editTextzipcode.setError("ZIP Code can't be EMPTY");
                    editTextzipcode.requestFocus();
                }else{
                    Handler handler = new Handler(Looper.getMainLooper());

                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            String field[] = new String[6];
                            field[0] = "userid";
                            field[1] = "firstname";
                            field[2] = "lastname";
                            field[3] = "username";
                            field[4] = "email";
                            field[5] = "zipcode";

                            String data[] = new String[6];
                            data[0] = userid;
                            data[1] = efirstname;
                            data[2] = elastname;
                            data[3] = eusername;
                            data[4] = eemail;
                            data[5] = ezipcode;

                            PutData putData = new PutData("https://www.psuwal.com/aplus/DBConnection/updateprofile.php", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    String result = putData.getResult();
                                    if(result.contains("Profile Updated")){
                                        Toast.makeText(getActivity(), result, Toast.LENGTH_LONG).show();
                                        switchEditinfo.setChecked(false);
                                    }else{
                                        Toast.makeText(getActivity(), result, Toast.LENGTH_LONG).show();
                                    }
                                    Log.i("PutData", result);
                                }
                            }
                        }
                    });
                }
            }
        });

        buttonLogout = view.findViewById(R.id.buttonlogout);
        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("SavedPreferences", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.apply();
                Intent intent = new Intent(getContext(), home_activity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        switchEditinfo = view.findViewById(R.id.profileeditswitch);
        switchEditinfo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    editTextfirstname.setEnabled(true);
                    editTextlastname.setEnabled(true);
                    editTextusername.setEnabled(true);
                    editTextemail.setEnabled(true);
                    editTextzipcode.setEnabled(true);
                    buttonprofilesave.setEnabled(true);
                }else{
                    editTextfirstname.setEnabled(false);
                    editTextlastname.setEnabled(false);
                    editTextusername.setEnabled(false);
                    editTextemail.setEnabled(false);
                    editTextzipcode.setEnabled(false);
                    buttonprofilesave.setEnabled(false);
                }
            }
        });




    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

}