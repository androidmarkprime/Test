package com.example.markprime.test.Home;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.markprime.test.Home.Events.EventsFragment;
import com.example.markprime.test.Home.MyTickets.MyTicketsFragment;
import com.example.markprime.test.Home.SavedEvents.SavedEventsFragment;

public class HomeActivityPagerAdapter {

    public static class ViewPagerAdapter extends FragmentPagerAdapter {
        private static int num = 3;

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0: // Fragment # 0 - This will show FirstFragment
                    return EventsFragment.newInstance(0, "Events");
                case 1: // Fragment # 0 - This will show FirstFragment different title
                    return MyTicketsFragment.newInstance(1, "Two");
                case 2: // Fragment # 1 - This will show SecondFragment
                    return SavedEventsFragment.newInstance(2, "Three");
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
