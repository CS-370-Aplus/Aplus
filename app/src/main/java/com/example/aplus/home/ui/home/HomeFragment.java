package com.example.aplus.home.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.aplus.R;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class HomeFragment extends Fragment {

    WebView webview;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        String sessionUser = getActivity().getIntent().getStringExtra("SESSION_USERNAME");
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
        webview.postUrl(url, postData.getBytes());
        return view;
    }
}