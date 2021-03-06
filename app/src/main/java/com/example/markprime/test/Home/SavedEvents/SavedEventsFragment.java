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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.markprime.test.FragmentInteractionListener;
import com.example.markprime.test.Home.Events.EventsAdapter;
import com.example.markprime.test.Home.MyTickets.MyTicketsFragment;
import com.example.markprime.test.Model.EventObject;
import com.example.markprime.test.R;
import com.example.markprime.test.utils.SharedPrefs;

import java.util.ArrayList;
import java.util.List;

public class SavedEventsFragment extends Fragment implements SavedEventsAdapterListener {

    private RecyclerView re_saved_events;
    private FragmentInteractionListener fragmentInteractionListener;
    private SavedEventsContract.Presenter presenter;
    private Context context;
    private List<EventObject> eventList = new ArrayList<>();
    private SavedEventsAdapter savedEventsAdapter;
    SharedPrefs sharedPrefs;

    private static final String SAVED = "saved";



    public SavedEventsFragment() {
        // Required empty public constructor
    }

    public static SavedEventsFragment newInstance() {
        SavedEventsFragment fragmentOne = new SavedEventsFragment();
        Bundle args = new Bundle();
        fragmentOne.setArguments(args);
        return fragmentOne;
    }

//    public static SavedEventsFragment newInstance(String saved) {
//        SavedEventsFragment fragmentOne = new SavedEventsFragment();
//        Bundle args = new Bundle();
//        args.putString(SAVED, saved);
//        fragmentOne.setArguments(args);
//        return fragmentOne;
//    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_saved_events, container, false);


        // Get favorite items from SharedPreferences.
        sharedPrefs = new SharedPrefs();
        eventList = sharedPrefs.getEvents(context);

        if(eventList != null) {
            setupRecycler(view);
            setAdapter();
        } else{

        }

        return view;
    }

    private void setupRecycler(View view) {

        re_saved_events = view.findViewById(R.id.re_saved_events);
        LinearLayoutManager llmSaved = new LinearLayoutManager(context);
        llmSaved.setOrientation(LinearLayoutManager.VERTICAL);
        re_saved_events.setLayoutManager(llmSaved);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
        fragmentInteractionListener = (FragmentInteractionListener) context;
//        if(getArguments() != null){
//            saved = getArguments().getString(SAVED);
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    private void setAdapter() {
        savedEventsAdapter = new SavedEventsAdapter(context, eventList);
//        Collections.shuffle(eventList);
        re_saved_events.setAdapter(savedEventsAdapter);

        re_saved_events.addOnChildAttachStateChangeListener(savedEventsAdapter);
    }



}


