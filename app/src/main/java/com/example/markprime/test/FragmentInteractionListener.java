package com.example.markprime.test;

import android.content.Context;
import android.view.View;

import org.json.JSONObject;

public interface FragmentInteractionListener {

    void openEventDetailsFragment(JSONObject eventDetails);

    void openCheckOutFragment(View view);
}