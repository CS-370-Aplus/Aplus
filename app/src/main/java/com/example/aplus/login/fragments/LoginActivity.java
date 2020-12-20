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
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sessionPage= getIntent().getStringExtra("SESSION_PAGE");

        viewPager = findViewById(R.id.viewPager);
        FragmentStateAdapter pagerAdapter = new ScreenSlidePagerAdapter(this);
        pagerAdapter.createFragment(0);
        pagerAdapter.createFragment(1);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setCurrentItem(Integer.parseInt(sessionPage));
    }

    @Override
    public void onBackPressed() {
        if(viewPager.getCurrentItem() == 0){
            super.onBackPressed();
        }else{
            viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
        }
    }

    public void replaceFragments(int pos) {
        viewPager.setCurrentItem(pos);
    }

    private static class ScreenSlidePagerAdapter extends FragmentStateAdapter{
        public ScreenSlidePagerAdapter(FragmentActivity fa){
            super(fa);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position){
            Fragment newFragment;
            switch (position){
                case 0:
                    newFragment = new loginFragment();
                    break;
                case 1:
                    newFragment = new registerFragment();
                    break;
                default:
                    throw new IllegalStateException("Unexpected value of position: " + position);
            }
            return newFragment;
        }

        @Override
        public int getItemCount() {
            return NUM_PAGES;
        }
    }


}
