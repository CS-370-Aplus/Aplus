package com.example.aplus.home.ui.profile;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.example.aplus.R;
import com.example.aplus.home.home_activity;
import com.example.aplus.login.fragments.LoginActivity;
import com.example.aplus.login.fragments.loginFragment;
import com.example.aplus.login.fragments.registerFragment;

public class ProfileFragment extends Fragment {
    String sessionUser;

    View layoutLinear;
    Button buttonLogin, buttonSignup;
    WebView webview;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        layoutLinear = view.findViewById(R.id.profileLinearlayout);
        webview = view.findViewById(R.id.profilewebview);
         sessionUser= getActivity().getIntent().getStringExtra("SESSION_USERNAME");
        if(sessionUser == null){
            webview.setVisibility(View.GONE);
            layoutLinear.setVisibility(View.VISIBLE);
        }else{
            layoutLinear.setVisibility(View.GONE);
            webview.setVisibility(View.VISIBLE);
        }
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        buttonLogin = view.findViewById(R.id.btnlogin);
        buttonLogin.setOnClickListener(view1 -> {
            Intent intent = new Intent(getContext(), LoginActivity.class);
            intent.putExtra("SESSION_USERNAME", sessionUser);
            intent.putExtra("SESSION_PAGE", "0");
            startActivity(intent);
        });

        buttonSignup = view.findViewById(R.id.btnsignup);
        buttonSignup.setOnClickListener(view1 -> {
            Intent intent = new Intent(getContext(), LoginActivity.class);
            intent.putExtra("SESSION_USERNAME", sessionUser);
            intent.putExtra("SESSION_PAGE", "1");
            startActivity(intent);
        });
    }

}