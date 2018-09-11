package com.example.markprime.test;


import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.FrameLayout;


import com.example.markprime.test.Checkout.CheckoutFragment;
import com.example.markprime.test.EventDetails.EventDetailsFragment;
import com.example.markprime.test.EventList.EventListFragment;

import org.json.JSONObject;

public class MainActivity extends FragmentActivity implements FragmentInteractionListener {


    private android.support.v4.app.FragmentManager fragmentManager;
    private android.support.v4.app.FragmentTransaction fragmentTransaction;

    private FrameLayout fl_fragment_container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpFrameLayout();
        setupFragmentManager();
        setInitialFragment();


    }

    private void setUpFrameLayout(){
        fl_fragment_container = findViewById(R.id.fl_fragment_container);
    }


    private void setupFragmentManager(){

        fragmentManager = getSupportFragmentManager();
    }


    private void setInitialFragment(){
        fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(fl_fragment_container.getId(), EventListFragment.newInstance()).addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void openEventDetailsFragment(JSONObject eventDetails) {
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(fl_fragment_container.getId(), EventDetailsFragment.newInstance(eventDetails)).addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        if(fragmentManager.getBackStackEntryCount() > 1){
            fragmentManager.popBackStack();
        } else {
            finish();
        }
    }


    public void openCheckOutFragment(View view) {
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(fl_fragment_container.getId(), CheckoutFragment.newInstance()).addToBackStack(null);
        fragmentTransaction.commit();
    }



}
