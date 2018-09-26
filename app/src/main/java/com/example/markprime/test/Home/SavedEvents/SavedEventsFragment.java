package com.example.markprime.test.Home.SavedEvents;


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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.markprime.test.FragmentInteractionListener;
import com.example.markprime.test.Home.Events.EventsAdapter;
import com.example.markprime.test.Home.MyTickets.MyTicketsFragment;
import com.example.markprime.test.Model.EventObject;
import com.example.markprime.test.R;

import java.util.ArrayList;
import java.util.List;

public class SavedEventsFragment extends Fragment implements SavedEventsAdapterListener {

    private RecyclerView re_events;
    private Context context;
    private List<EventObject> eventList = new ArrayList<>();
    private EventsAdapter eventsAdapter;


    public SavedEventsFragment() {
        // Required empty public constructor
    }

    public static SavedEventsFragment newInstance() {
        SavedEventsFragment fragmentOne = new SavedEventsFragment();
        Bundle args = new Bundle();
        fragmentOne.setArguments(args);
        return fragmentOne;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_saved_events, container, false);

        return view;
    }

}


