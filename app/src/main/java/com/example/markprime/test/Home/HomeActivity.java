package com.example.markprime.test.Home;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.markprime.test.Home.Events.EventsFragment;
import com.example.markprime.test.Home.MyTickets.MyTicketsFragment;
import com.example.markprime.test.Home.SavedEvents.SavedEventsFragment;
import com.example.markprime.test.R;

public class HomeActivity extends AppCompatActivity {


    private LinearLayout ll_menu, ll_home_main, ll_profile_contaner, menu_my_tickets_container, menu_reps_container,
            menu_events_container, menu_artists_container, menu_brands_container, menu_setting_container,
            menu_help_container;
    private DrawerLayout dl_home;
    private TabLayout tablayout_home;
    private ViewPager vp_home;
    private FrameLayout fl_vp__home;
    private Button btn_menu;

    private boolean drawerOpen = false;

    private Context context;

    public  FragmentManager fm;
    private FragmentTransaction transaction;
    public  FragmentPagerAdapter adapterViewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        final ViewPager vpPager = findViewById(R.id.vp_home);
        adapterViewPager = new ViewPagerAdapter(getSupportFragmentManager());
        vpPager.setAdapter(adapterViewPager);


        context = this;

        setupFM();
        setupViews();
        setDrawerLayout();
        setupButtons();
        setupMenu();

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
                    return EventsFragment.newInstance(0, "Events");
                case 1: // Fragment # 0 - This will show FirstFragment different title
                    return SavedEventsFragment.newInstance(1, "Saved Events");
                case 2: // Fragment # 1 - This will show SecondFragment
                    return MyTicketsFragment.newInstance(2, "My Tickets");
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return num;
        }
    }

    private void setupViews(){
        menu_my_tickets_container = findViewById(R.id.menu_my_tickets_container);
        menu_reps_container = findViewById(R.id.menu_reps_container);
        menu_events_container = findViewById(R.id.menu_events_container);
        menu_artists_container = findViewById(R.id.menu_artists_container);
        menu_brands_container = findViewById(R.id.menu_brands_container);
        menu_setting_container = findViewById(R.id.menu_setting_container);
        menu_help_container = findViewById(R.id.menu_help_container);
        tablayout_home = findViewById(R.id.tablayout_home);
        ll_menu = findViewById(R.id.ll_menu);
    }

    private void setupButtons(){
        btn_menu = findViewById(R.id.btn_menu);

        btn_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawerOpen) {
                    closeDrawer();
                } else {
                    openDrawer();
                }
            }
        });

    }


    private void setupMenu() {
        menu_my_tickets_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeDrawer();
                try {
                    showMyTickets();
                    Toast.makeText(HomeActivity.this, "My Tickets Clicked", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        menu_reps_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeDrawer();
                try {
                    Toast.makeText(HomeActivity.this, "Encore Reps Clicked", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        menu_events_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeDrawer();
                try {
                    Toast.makeText(HomeActivity.this, "Encore Reps Clicked", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        menu_artists_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeDrawer();
                try {
                    Toast.makeText(HomeActivity.this, "Artists Clicked", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        menu_brands_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeDrawer();
                try {
                    Toast.makeText(HomeActivity.this, "Brands Clicked", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        menu_setting_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeDrawer();
                try {
                    Toast.makeText(HomeActivity.this, "Settings Clicked", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        menu_help_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeDrawer();
                try {
                    Toast.makeText(HomeActivity.this, "Help Clicked", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });



    }

    private void openDrawer() {
        dl_home.openDrawer(GravityCompat.START);
    }

    private void closeDrawer() { dl_home.closeDrawer(GravityCompat.START); }

    private void setDrawerLayout() {
        dl_home = (DrawerLayout) findViewById(R.id.dl_home);
        dl_home.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                float moveFactor = (menu_my_tickets_container.getWidth() * slideOffset);
                ll_menu.setTranslationX(1);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                drawerOpen = true;
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                drawerOpen = false;
            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
    }

    public void showMyTickets() {
        transaction = fm.beginTransaction();
        transaction.replace(fl_vp__home.getId(),
                MyTicketsFragment.newInstance(2, "My Tickets")).addToBackStack(null);
        transaction.commit();
        tablayout_home.setVisibility(View.GONE);
        vp_home.setVisibility(View.GONE);
        fl_vp__home.setVisibility(View.VISIBLE);
    }


//    @Override
//    public void showEventList(String object) {
//        transaction = fm.beginTransaction();
//        transaction.replace(fl_vp__home.getId(),
//                ViewEventsFragment.newInstance(object, false)).addToBackStack(null);
//        transaction.commit();
//        tablayout_home.setVisibility(View.GONE);
//        vp_home.setVisibility(View.GONE);
//        fl_vp__home.setVisibility(View.VISIBLE);
//    }


    private void setupFM(){
        fm = getSupportFragmentManager();
        transaction = fm.beginTransaction();
    }


}
