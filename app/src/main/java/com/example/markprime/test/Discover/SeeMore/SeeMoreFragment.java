package com.example.markprime.test.Discover.SeeMore;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.markprime.test.Discover.BaseActivity;
import com.example.markprime.test.Discover.DiscoverSeeMoreListener;
import com.example.markprime.test.Discover.InspireMe.InspireMeAdapter;
import com.example.markprime.test.Discover.InspireMe.InspireMeAdapterListener;
import com.example.markprime.test.FragmentInteractionListener;
import com.example.markprime.test.Model.InspireMeEventsObject;
import com.example.markprime.test.Network.VolleySingletonErrorListener;
import com.example.markprime.test.Network.VolleySingletonListener;
import com.example.markprime.test.Service.ApiService;

import org.json.JSONObject;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class SeeMoreFragment extends Fragment
        implements SeeMoreFragmentContract.View, InspireMeAdapterListener {

    private FragmentInteractionListener fragmentInteractionListener;
    private Context context;
    private SeeMoreFragmentContract.Presenter presenter;

    private RecyclerView re_events;
    private TextView txt_category_name;
    private ImageView img_see_on_map;

    private InspireMeAdapter inspireMeAdapter;

    private static String FULL_JSON_STRING = "full_json_string";

    private JSONObject fullObject;
    private List<InspireMeEventsObject> eventList = new ArrayList<>();

    private DiscoverSeeMoreListener discoverSeeMoreListener;

    public SeeMoreFragment() {
        // Required empty public constructor
    }

    public static SeeMoreFragment newInstance(String fullJsonString, DiscoverSeeMoreListener listener) {
        SeeMoreFragment fragment = new SeeMoreFragment();
        Bundle args = new Bundle();
        args.putString(FULL_JSON_STRING, fullJsonString);
        args.putParcelable("LISTENER", listener);
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
        View view = inflater.inflate(R.layout.fragment_see_more, container, false);

        presenter = new SeeMoreFragmentPresenter(this);

//        fragmentInteractionListener.showNavBar();
//        fragmentInteractionListener.showBackIcon();

        setUpTextView(view);
        setUpRecyclerView(view);
        setUpImageViews(view);

        return view;
    }

    private void setUpTextView(View view){
        txt_category_name = view.findViewById(R.id.txt_category_name);
        try {
            txt_category_name.setText(fullObject.getString(Constants.TITLE));
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private void setUpRecyclerView(View view){
        re_events = view.findViewById(R.id.re_events);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        re_events.setLayoutManager(linearLayoutManager);
        setAdapter();
    }

    private void setUpImageViews(View view){
        img_see_on_map = view.findViewById(R.id.img_see_on_map);
        img_see_on_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentInteractionListener.displayPartialEventMapFragment(inspireMeAdapter.getInspireMeEventsList());
            }
        });
    }

    private void setAdapter(){
        try {
            if(eventList.isEmpty()){
                eventList = presenter.buildInspireMeList(fullObject.getJSONArray(Constants.INFO));
            }
            inspireMeAdapter = new InspireMeAdapter(context, eventList, this);
            re_events.setAdapter(inspireMeAdapter);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        fragmentInteractionListener = (FragmentInteractionListener) context;
        this.context = context;
        if(getArguments() != null){
            try {
                fullObject = new JSONObject(getArguments().getString(FULL_JSON_STRING));
                discoverSeeMoreListener = getArguments().getParcelable("LISTENER");
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void itemClicked(InspireMeEventsModel eventsModel) {
        fragmentInteractionListener.showLoader();
        ApiService.getInstance(context).getEventDetails(eventsModel.getId(),
                new VolleySingletonListener<JSONObject>() {
                    @Override
                    public void onResult(JSONObject object) {
                        fragmentInteractionListener.dismissLoader();
                        try{
                            fragmentInteractionListener.setEventProfileFrag(object.getJSONObject(Constants.RESULTS));
                        } catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }, new VolleySingletonErrorListener() {
                    @Override
                    public void onErrorResult(VolleyError object) {
                        fragmentInteractionListener.dismissLoader();
                        if(object.getCause() instanceof UnknownHostException){
                            goingToClickedNoConnection();
                        }
                    }
                });
    }

    @Override
    public void goingToClicked(final InspireMeEventsModel eventsModel) {
        discoverSeeMoreListener.updateDiscoverGoingToStatus(eventsModel.getId(), eventsModel.getGoingTo());
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put(Constants.ACCESSTOKEN,
                    BaseActivity.prefs.getString(Constants.ACCESS_TOKEN, ""));
            jsonObject.put("going", eventsModel.getGoingTo());
            jsonObject.put("eventid", eventsModel.getId());
            ApiService.getInstance(context).changeGoingToStatus(jsonObject, new VolleySingletonListener<JSONObject>() {
                @Override
                public void onResult(JSONObject object) {
                    handleGoingToToastSuccess(eventsModel.getGoingTo());
                }
            }, new VolleySingletonErrorListener() {
                @Override
                public void onErrorResult(VolleyError object) {
                    Toast.makeText(context, "Oops! Something went wrong", Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void goingToClickedNoConnection() {
        fragmentInteractionListener.showSingleInfoDialog("Oops!",
                "Please check your internet connection and try again");
    }

    private void handleGoingToToastSuccess(int status){
        switch (status){
            case 0:
                Toast.makeText(context, "Unsaved", Toast.LENGTH_SHORT).show();
                break;
            case 1:
                Toast.makeText(context, "Going!", Toast.LENGTH_SHORT).show();
                break;
            case 9:
                Toast.makeText(context, "Saved", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}

