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
import com.example.markprime.test.Model.EventObject;
import com.example.markprime.test.R;

import java.util.ArrayList;
import java.util.List;

public class SavedEventsFragment extends Fragment implements SavedEventsAdapterListener {

    private RecyclerView re_saved;
    private Context context;
    private List<EventObject> savedList = new ArrayList<>();
    private SavedEventsAdapter savedEventsAdapter;
    private FragmentInteractionListener fragmentInteractionListener;

    public SavedEventsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_saved_events, container, false);
        setupRecyclerView(view);

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);

        getSaved();

        return view;


    }

    public static SavedEventsFragment newInstance(int i) {

        SavedEventsFragment saved = new SavedEventsFragment();
        Bundle args = new Bundle();
        saved.setArguments(args);
        return saved;

    }

    private void setupRecyclerView(View view){
        re_saved = view.findViewById(R.id.re_saved_events);
        LinearLayoutManager llm = new LinearLayoutManager(context);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        re_saved.setLayoutManager(llm);

    }

    private void getSaved(){

        setAdapter();

    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        this.context = context;
        fragmentInteractionListener = (FragmentInteractionListener) context;
    }

    @Override
    public void onDetach() {super.onDetach();}

    private void setAdapter(){
        savedEventsAdapter = new SavedEventsAdapter(context, savedList);
        re_saved.setAdapter(savedEventsAdapter);
    }



    /////////////////////////// Broadcast Recievers///////////////

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            final ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            final NetworkInfo ni = connectivityManager.getActiveNetworkInfo();
            if (ni != null && ni.isConnectedOrConnecting()) {
                if (re_saved.getAdapter() == null) {
                    getSaved();

                    ;}else

                    return;
            }
        }



    };

}


