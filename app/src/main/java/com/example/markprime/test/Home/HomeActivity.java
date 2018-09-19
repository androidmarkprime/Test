package com.example.markprime.test.Home;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.customtabs.CustomTabsIntent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.markprime.test.Checkout.CheckoutFragment;
import com.example.markprime.test.EventDetails.EventDetailsFragment;
import com.example.markprime.test.EventList.EventListFragment;
import com.example.markprime.test.FragmentInteractionListener;
import com.example.markprime.test.Home.Events.EventsFragment;
import com.example.markprime.test.Home.MyTickets.MyTicketsFragment;
import com.example.markprime.test.Home.SavedEvents.SavedEventsFragment;
import com.example.markprime.test.Model.EventObject;
import com.example.markprime.test.Purchase.PurchaseActivity;
import com.example.markprime.test.R;

import org.json.JSONObject;

public class HomeActivity extends AppCompatActivity implements FragmentInteractionListener {


    private LinearLayout ll_menu, ll_home_main, ll_profile_contaner, ll_loader,
            menu_my_tickets_container, menu_reps_container,
            menu_events_container, menu_artists_container,
            menu_brands_container, menu_setting_container,
            menu_help_container;
    private DrawerLayout dl_home;
    private TabLayout tablayout_home;
    private ViewPager vp_home;
    private FrameLayout fl_vp__home;
    private Button btn_menu;

    private boolean drawerOpen = false;
    private Boolean loaderVisible = false;

    private Context context;

    public  FragmentManager fm;
    private FragmentTransaction transaction;
    public  FragmentPagerAdapter adapterViewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        final ViewPager vpPager = findViewById(R.id.vp_home);
        adapterViewPager = new ViewPagerAdapter(getSupportFragmentManager(), HomeActivity.this);
        vpPager.setAdapter(adapterViewPager);

        setupLoader();

        setupFM();
        setupViews();
        setDrawerLayout();
        setupButtons();
        setupMenu();

        TabLayout tabLayout = findViewById(R.id.tablayout_home);
        tablayout_home.setupWithViewPager(vpPager);

    }


    public static class ViewPagerAdapter extends FragmentPagerAdapter{
        private static int num = 3;
        private Context context;
        private String tabTitles[] = new String[] {"Events", "Saved Events", "My Tickets"};

        SparseArray<Fragment> cache = new SparseArray<>();

        public ViewPagerAdapter(FragmentManager fm, Context context) {
            super(fm);
            this.context = context;
        }

        @Override
        public Fragment getItem(int position) {
            Fragment f = cache.get(position, null);

            if (f == null) {
                switch (position) {
                    case 0:
                        f = EventsFragment.newInstance(0);

                        break;
                    case 1:
                        f = SavedEventsFragment.newInstance(1);
                        break;
                    case 2:
                        f = MyTicketsFragment.newInstance(2);
                        break;
                    default:
                        return null;
                }
                cache.put(position, f);
            }

            return f;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            super.destroyItem(container, position, object);
            cache.remove(position);
        }

        @Override
        public int getCount() {
            return tabTitles.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            // Generate title based on item position
            return tabTitles[position];
        }

    }

    private void setupLoader(){
        ll_loader = findViewById(R.id.ll_loader);
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
        fl_vp__home = findViewById(R.id.fl_vp__home);

        tablayout_home.setupWithViewPager(vp_home);
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
                    Toast.makeText(HomeActivity.this, "Events Clicked", Toast.LENGTH_SHORT).show();
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
//                    String url = "https://help.skiddle.com/";
//                    CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
//                    CustomTabsIntent customTabsIntent = builder.build();
//                    customTabsIntent.launchUrl(Uri.parse(url));
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


    private void setupFM(){
        fm = getSupportFragmentManager();
        transaction = fm.beginTransaction();
    }


    public void showLoader() {

        new Handler().post(new Runnable() {
            @Override
            public void run() {

                ll_loader.setVisibility(View.VISIBLE);
                loaderVisible = true;
                ll_loader.setClickable(true);
                ll_loader.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Do nothing
                    }
                });

            }
        });

    }

    public boolean isLoaderVisible() {
        return ll_loader.getVisibility() == View.VISIBLE;
    }

    public void dismissLoader() {
        if (loaderVisible == true) {
            loaderVisible = false;
        }
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                ll_loader.setVisibility(View.GONE);
                ll_loader.setOnClickListener(null);
            }
        });
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////
    //FragmentInteractionListener

    private void setEventListFragment(){
        transaction=fm.beginTransaction();
        transaction.replace(fl_vp__home.getId(), EventsFragment.newInstance(0)).addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        if(fm.getBackStackEntryCount() > 1){
            fm.popBackStack();
        } else {
            if(loaderVisible){
                dismissLoader();
            }else{
                super.onBackPressed();
            }
            finish();
        }
    }

    @Override
    public void openEventDetailsFragment(JSONObject eventDetails) {
//        fl_vp__home.setVisibility(View.VISIBLE);
//        transaction = fm.beginTransaction();
//        transaction.replace(fl_vp__home.getId(), EventDetailsFragment.newInstance(eventDetails)).addToBackStack(null);
//        transaction.commit();
    }

    public void openCheckOutFragment(View view) {
//        transaction = fm.beginTransaction();
//        transaction.replace(fl_vp__home.getId(), CheckoutFragment.newInstance()).addToBackStack(null);
//        transaction.commit();
    }


}
