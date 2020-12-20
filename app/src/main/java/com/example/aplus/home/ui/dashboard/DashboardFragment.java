package com.example.aplus.home.ui.dashboard;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;


import com.example.aplus.R;
import com.example.aplus.home.ui.profile.ProfileFragment;
import com.example.aplus.listings.newListing;
import com.example.aplus.login.fragments.LoginActivity;
import com.google.android.material.navigation.NavigationView;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class DashboardFragment extends Fragment {
    String sessionUser, sessionType;

    View buyerLayout, sellerLayout, loginLayout;
    View buyerScrollview, sellerScrollview;

    Button buttonLogin, buttonSignup;
    Button buttonnewListing;
    Button buttonsellerSearch, buttonbuyerSearch;
    EditText editTextSellerSearch,  editTextBuyerSearch;
    WebView webviewSeller, webviewBuyer;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        buyerLayout = view.findViewById(R.id.buyerlinearlayout);
        buyerScrollview = view.findViewById(R.id.buyerScrollview);
        sellerLayout = view.findViewById(R.id.sellerlinearlayout);
        sellerScrollview = view.findViewById(R.id.sellerScrollView);
        loginLayout = view.findViewById(R.id.dashboardaskloginlayout);


        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("SavedPreferences", Context.MODE_PRIVATE);
        sessionUser = sharedPreferences.getString("Username", "");
        sessionType = sharedPreferences.getString("AccountType", "");

        if (sessionUser.equals("")) {
            loginLayout.setVisibility(View.VISIBLE);
            sellerLayout.setVisibility(View.GONE);
            sellerScrollview.setVisibility(View.GONE);
            buyerLayout.setVisibility(View.GONE);
            buyerScrollview.setVisibility(View.GONE);
        } else{
            if (sessionType.equals("S")) {
                sellerLayout.setVisibility(View.VISIBLE);
                sellerScrollview.setVisibility(View.VISIBLE);
                loginLayout.setVisibility(View.GONE);
                buyerLayout.setVisibility(View.GONE);
                buyerScrollview.setVisibility(View.GONE);
            } else{
                buyerLayout.setVisibility(View.VISIBLE);
                buyerScrollview.setVisibility(View.VISIBLE);
                loginLayout.setVisibility(View.GONE);
                sellerLayout.setVisibility(View.GONE);
                sellerScrollview.setVisibility(View.GONE);
            }
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


        /************SellerDashboard*********************/

        buttonnewListing = view.findViewById(R.id.btnnewlisting);
        buttonnewListing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), newListing.class);
                startActivity(intent);
            }
        });

        editTextSellerSearch = view.findViewById(R.id.sellerselfsearch);

        webviewSeller = view.findViewById(R.id.sellerwebview);

        String url = "http://www.psuwal.com/aplus/sellerdashboard.php";
        String postData = null;
        try {
            postData = "username="+ URLEncoder.encode(sessionUser,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        webviewSeller.setBackgroundColor(Color.TRANSPARENT);
        webviewSeller.setVerticalScrollBarEnabled(true);
        webviewSeller.postUrl(url, postData.getBytes());


        /************BuyerDashboard*********************/

        editTextBuyerSearch = view.findViewById(R.id.buyersearchbar);
        buttonbuyerSearch = view.findViewById(R.id.buyersearchbutton);

        webviewBuyer = view.findViewById(R.id.buyerwebview);

        url = "http://www.psuwal.com/aplus/buyerdashboard.php";
        try {
            postData = "username="+ URLEncoder.encode(sessionUser,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        webviewBuyer.setBackgroundColor(Color.TRANSPARENT);
        webviewBuyer.setVerticalScrollBarEnabled(true);
        webviewBuyer.postUrl(url, postData.getBytes());


    }
}