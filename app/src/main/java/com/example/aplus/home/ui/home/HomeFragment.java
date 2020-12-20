package com.example.aplus.home.ui.home;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.aplus.R;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class HomeFragment extends Fragment {

    WebView webview;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("SavedPreferences", Context.MODE_PRIVATE);
        String sessionUser;
        sessionUser = sharedPreferences.getString("Username", "");
        //Toast.makeText(getContext(), sessionUser, Toast.LENGTH_SHORT).show();
        if(sessionUser == null){
            sessionUser = "";
        }
        String url = "http://www.psuwal.com/aplus/home.php";
        String postData = null;
        try {
            postData = "username="+ URLEncoder.encode(sessionUser,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        webview = view.findViewById(R.id.homewebview);
        webview.setBackgroundColor(Color.TRANSPARENT);
        webview.postUrl(url, postData.getBytes());
        return view;
    }
}