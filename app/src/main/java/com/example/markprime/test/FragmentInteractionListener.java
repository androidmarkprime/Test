package com.example.markprime.test;

import org.json.JSONObject;

public interface FragmentInteractionListener {

    void openEventDetailsFragment(JSONObject eventDetails);

    void openCheckOutFragment(JSONObject eventDetails);

    void openMyTicketsFragment(JSONObject eventDetails);
}