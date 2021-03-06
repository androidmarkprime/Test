package com.example.markprime.test.EventList;


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

import com.example.markprime.test.FragmentInteractionListener;
import com.example.markprime.test.Model.EventObject;
import com.example.markprime.test.Network.NetworkManager;
import com.example.markprime.test.Network.VolleySingletonListener;
import com.example.markprime.test.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class EventListFragment extends Fragment implements EventListAdapterListener {

    private RecyclerView re_event_list;
    private Context context;
    private FragmentInteractionListener fragmentInteractionListener;
    private List<EventObject> eventList = new ArrayList<>();
    private EventListAdapter eventListAdapter;


    public EventListFragment() {
        //required empty public constructor
    }

    public static EventListFragment newInstance() {
        EventListFragment fragment = new EventListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_event_list, container, false);

        setUpRecyclerView(view);


        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        context.registerReceiver(receiver, intentFilter);

        getEvents();

        return view;
    }

    private void setUpRecyclerView(View view) {
        re_event_list = view.findViewById(R.id.re_event_list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        re_event_list.setLayoutManager(linearLayoutManager);
    }

    private void getEvents() {
        NetworkManager.getInstance(context).exampleGetRequest(
                "https://www.skiddle.com/api/v1/events/search?api_key=008f1e6099ecc48e990e3776784d447b&platform=android&type=mobileapp&version=97&limit=40&offset=0&description=1&imagefilter=1&platform=android&order=date&radius=30&latitude=53.3994794&longitude=-2.524805&eventcode=4&aggs=genreids,eventcode", new VolleySingletonListener<JSONObject>() {
            @Override
            public void onResult(JSONObject object) {

                try {
                    JSONArray jsonArray = object.getJSONArray("results");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        eventList.add(new EventObject(jsonArray.getJSONObject(i)));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                setAdapter();

                Log.d("RESPONSE", object.toString());
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
        fragmentInteractionListener = (FragmentInteractionListener) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    private void setAdapter() {
        eventListAdapter = new EventListAdapter(context, eventList, this);
//        Collections.shuffle(eventList);
        re_event_list.setAdapter(eventListAdapter);
    }


    @Override
    public void eventClicked(EventObject eventObject) {
        fragmentInteractionListener.openEventDetailsFragment(eventObject.getFullObject());
    }


    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            final ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            final NetworkInfo ni = connectivityManager.getActiveNetworkInfo();
            if (ni != null && ni.isConnectedOrConnecting()) {
                if (re_event_list.getAdapter() == null) {
                    getEvents()

                    ;}else

                return;
                }
            }



    };

}
