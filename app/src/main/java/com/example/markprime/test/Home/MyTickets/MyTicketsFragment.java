package com.example.markprime.test.Home.MyTickets;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.andexert.library.RippleView;
import com.example.markprime.test.FragmentInteractionListener;
import com.example.markprime.test.Home.Events.EventsAdapter;
import com.example.markprime.test.Model.EventObject;
import com.example.markprime.test.R;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MyTicketsFragment extends Fragment {

    private RecyclerView re_events;
    private Context context;
    private List<EventObject> eventList = new ArrayList<>();
    private EventsAdapter eventsAdapter;
    private FragmentInteractionListener fragmentInteractionListener;

    private static final String EVENT_OBJECT = "event_object";
    private EventObject eventObject;


    public MyTicketsFragment() {
        // Required empty public constructor
    }

    public static MyTicketsFragment newInstance() {
        MyTicketsFragment fragmentTwo = new MyTicketsFragment();
        Bundle args = new Bundle();
        fragmentTwo.setArguments(args);
        return fragmentTwo;
    }

    public static MyTicketsFragment newInstance(JSONObject eventDetails) {
        MyTicketsFragment fragmentTwo = new MyTicketsFragment();
        Bundle args = new Bundle();
        args.putString(EVENT_OBJECT, eventDetails.toString());
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
        View view = inflater.inflate(R.layout.fragment_my_tickets, container, false);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
        fragmentInteractionListener = (FragmentInteractionListener) context;

        if (getArguments() != null) {
            try {
                eventObject = new EventObject(new JSONObject(getArguments().getString(EVENT_OBJECT)));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();

    }


}
