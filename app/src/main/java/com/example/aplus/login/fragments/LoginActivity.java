package com.example.aplus.login.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.example.aplus.R;

public class LoginActivity extends FragmentActivity {
    String sessionUser, sessionPage;

    private static final int NUM_PAGES = 2;

    private ViewPager2 viewPager;


        @Override
        public int getItemCount() {
            return NUM_PAGES;
        }
    }


}
