package com.example.markprime.test.Home.MyTickets;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.markprime.test.Home.Events.EventsAdapter;
import com.example.markprime.test.Model.EventObject;
import com.example.markprime.test.R;

import java.util.ArrayList;
import java.util.List;

public class MyTicketsFragment extends Fragment {

    private RecyclerView re_events;
    private Context context;
    private List<EventObject> eventList = new ArrayList<>();
    private EventsAdapter eventsAdapter;


    public MyTicketsFragment() {
        // Required empty public constructor
    }

    public static MyTicketsFragment newInstance(int i, String title) {
        MyTicketsFragment fragmentTwo = new MyTicketsFragment();
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
