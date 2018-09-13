package com.example.markprime.test.Home.Fragment2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.markprime.test.Home.Fragment1.EventsAdapter;
import com.example.markprime.test.Home.Fragment1.EventsAdapterListener;
import com.example.markprime.test.Model.EventObject;
import com.example.markprime.test.Network.NetworkManager;
import com.example.markprime.test.Network.VolleySingletonErrorListener;
import com.example.markprime.test.Network.VolleySingletonListener;
import com.example.markprime.test.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TwoFragment extends Fragment {

    private RecyclerView re_events;
    private Context context;
    private List<EventObject> eventList = new ArrayList<>();
    private EventsAdapter eventsAdapter;


    public TwoFragment() {
        // Required empty public constructor
    }

    public static TwoFragment newInstance(int i) {
        TwoFragment fragmentTwo = new TwoFragment();
        Bundle args = new Bundle();
        fragmentTwo.setArguments(args);
        return fragmentTwo;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_two, container, false);

        return view;
    }


}
