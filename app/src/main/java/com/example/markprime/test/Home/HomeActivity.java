package com.example.markprime.test.Home;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;

import com.example.markprime.test.Checkout.CheckoutFragment;
import com.example.markprime.test.EventDetails.EventDetailsFragment;
import com.example.markprime.test.EventList.EventListFragment;
import com.example.markprime.test.Home.Fragment1.EventsFragment;
import com.example.markprime.test.Home.Fragment2.TwoFragment;
import com.example.markprime.test.Home.Fragment3.ThreeFragment;
import com.example.markprime.test.R;

import org.json.JSONObject;

public class HomeActivity extends AppCompatActivity {

    FragmentPagerAdapter adapterViewPager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ViewPager vpPager = (ViewPager) findViewById(R.id.vp_home);
        adapterViewPager = new ViewPagerAdapter(getSupportFragmentManager());
        vpPager.setAdapter(adapterViewPager);



    }


    public static class ViewPagerAdapter extends FragmentPagerAdapter{
        private static int num = 3;

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0: // Fragment # 0 - This will show FirstFragment
                    return EventsFragment.newInstance(0);
                case 1: // Fragment # 0 - This will show FirstFragment different title
                    return TwoFragment.newInstance(1);
                case 2: // Fragment # 1 - This will show SecondFragment
                    return ThreeFragment.newInstance(2);
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return num;
        }
    }
}
