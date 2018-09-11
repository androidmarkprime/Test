package com.example.markprime.test.Home;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public class HomePagerAdapter extends FragmentPagerAdapter{

    private List<Fragment> fragmentList;
    private Context context;



    public HomePagerAdapter(FragmentManager fragmentManager, List<Fragment> fragmentList,
                            Context context) {
        super(fragmentManager);
        this.fragmentList = fragmentList;
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) { return fragmentList.get(position); }

    @Override
    public int getCount() { return fragmentList.size(); }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position){
        switch(position){
            case 0: return "Events";
            case 1: return "Categories";
            default: return "";
        }
    }
}
