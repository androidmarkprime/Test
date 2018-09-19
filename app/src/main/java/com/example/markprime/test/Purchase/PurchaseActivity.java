package com.example.markprime.test.Purchase;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.example.markprime.test.Checkout.CheckoutFragment;
import com.example.markprime.test.EventDetails.EventDetailsFragment;
import com.example.markprime.test.FragmentInteractionListener;
import com.example.markprime.test.Model.EventObject;
import com.example.markprime.test.R;

import org.json.JSONObject;

public class PurchaseActivity extends FragmentActivity implements FragmentInteractionListener {

    private FrameLayout fl_purchase;
    private LinearLayout ll_purchase;
    private FragmentTransaction fragmentTransaction;
    private FragmentManager fragmentManager;
    private FragmentInteractionListener fragmentInteractionListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase);


        setupFrameLayout();
        setupFrameManager();

    }

    public void setEventDetails(JSONObject eventDetails) {
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(fl_purchase.getId(), EventDetailsFragment.newInstance(eventDetails)).addToBackStack(null);
        fragmentTransaction.commit();
    }

    private void setupFrameManager() {
    fragmentManager = getSupportFragmentManager();
    }

    private void setupFrameLayout() {
        fl_purchase = findViewById(R.id.fl_purchase);
    }



    @Override
    public void openEventDetailsFragment(JSONObject eventDetails) {
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(fl_purchase.getId(), EventDetailsFragment.newInstance(eventDetails)).addToBackStack(null);
        fragmentTransaction.commit();

    }

    @Override
    public void openCheckOutFragment(View view) {
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(fl_purchase.getId(), CheckoutFragment.newInstance()).addToBackStack(null);
        fragmentTransaction.commit();

    }
}
