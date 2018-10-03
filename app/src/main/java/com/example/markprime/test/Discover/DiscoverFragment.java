package com.example.markprime.test.Discover;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.markprime.test.FragmentInteractionListener;
import com.example.markprime.test.Model.DiscoverCarouselObject;
import com.example.markprime.test.Model.DiscoverObject;
import com.example.markprime.test.Network.NetworkManager;
import com.example.markprime.test.Network.VolleySingletonErrorListener;
import com.example.markprime.test.Network.VolleySingletonListener;
import com.example.markprime.test.Service.ApiService;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class DiscoverFragment extends Fragment
        implements DiscoverFragmentContract.View, DiscoverAdaptersListener.DiscoverAdapterListener,
        DiscoverAdaptersListener.DiscoverCarouselListener {

    private DiscoverFragmentContract.Presenter presenter;
    private FragmentInteractionListener fragmentInteractionListener;
    private Context context;

    private TextView txt_discover_intro, txt_no_items_available;
    private ImageView img_cross;
    private LinearLayout ll_welcome_container;
    private RecyclerView re_discover;
    private SwipeRefreshLayout swipe_refresh;
    private DiscoverAdapter discoverAdapter;
    private boolean discoverLoaded = false;

    private List<DiscoverObject> discoveryList = new ArrayList<>();

    public DiscoverFragment() {
        // Required empty public constructor
    }

    public static DiscoverFragment newInstance() {
        DiscoverFragment fragment = new DiscoverFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_discover, container, false);

        presenter = new DiscoverFragmentPresenter(this);

        setUpTextViews(view);
        setUpInitialIntro(view);
        setUpRecyclerView(view);
        setUpSwipeRefreshLayout(view);

//        fragmentInteractionListener.showNavBar();

        getDiscoveryData();

        return view;
    }

    private void setUpTextViews(View view){
        txt_no_items_available = view.findViewById(R.id.txt_no_items_available);
    }

    private void setUpInitialIntro(View view){
        txt_discover_intro = view.findViewById(R.id.txt_discover_intro);
        img_cross = view.findViewById(R.id.img_cross);
        ll_welcome_container = view.findViewById(R.id.ll_welcome_container);

//        if(!BaseActivity.prefs.getBoolean(Constants.DISCOVER_VIEWED, false)){
//            ll_welcome_container.setVisibility(View.VISIBLE);
//            BaseActivity.prefs.edit().putBoolean(Constants.DISCOVER_VIEWED, true).apply();
//        }

        img_cross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ll_welcome_container.setVisibility(View.GONE);
            }
        });
    }

    private void setUpRecyclerView(View view){
        re_discover = view.findViewById(R.id.re_discover);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        re_discover.setLayoutManager(linearLayoutManager);
    }

    private void setUpSwipeRefreshLayout(View view){
        swipe_refresh = view.findViewById(R.id.swipe_refresh);

        swipe_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                re_discover.setAdapter(null);
                discoverAdapter = null;
                getDiscoveryData();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        context.registerReceiver(receiver, intentFilter);
    }

    @Override
    public void onPause() {
        super.onPause();
        context.unregisterReceiver(receiver);
    }

    private void getDiscoveryData(){

        NetworkManager.getInstance(context).exampleGetRequest(
                "https://www.skiddle.com/api/v1/discover/?api_key=ce3dcde502f88cb2546467fe272ba50b&userid=91&platform=android", new VolleySingletonListener<JSONObject>() {


                    @Override
                    public void onResult(JSONObject object) {

                        if (discoverLoaded = false) {
                            txt_no_items_available.setVisibility(View.GONE);
                            discoverLoaded();
                            handleNetworkCallSuccessResponse(object);
                            setSwipeRefreshNotRefreshing();

                        } else {
                            re_discover.setAdapter(discoverAdapter);
                            swipe_refresh.setRefreshing(false);
                        }
                    }


                    public boolean discoverLoaded() {
                        discoverLoaded = true;
                    }

                    private void setSwipeRefreshNotRefreshing() {
                        if (swipe_refresh != null) {
                            swipe_refresh.setRefreshing(false);
                        }
                    }

                    private void handleNetworkCallSuccessResponse(JSONObject object) {
                        discoveryList.clear();
                        try {
                            JSONArray dataArray = object.getJSONArray("results");
                            Log.d("Discover Array Details", dataArray.toString());
                            for (int i = 0; i < dataArray.length(); i++) {
                                discoveryList.add(new DiscoverObject(dataArray.getJSONObject(i)));
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
//            fragmentInteractionListener.dismissLoader();
                        }

//        discoverAdapter = new DiscoverAdapter(discoveryList, context,
//                this, this);
//        re_discover.setAdapter(discoverAdapter);
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

    @Override
    public void seeMoreClicked(JSONObject fullJsonString, DiscoverSeeMoreListener listener) {
//        fragmentInteractionListener.openSeeMoreFragment(fullJsonString.toString(), listener);
    }

    @Override
    public void singleEventClicked(String eventId) {
////        fragmentInteractionListener.showLoader();
//        ApiService.getInstance(context).getEventDetails(eventId, new VolleySingletonListener<JSONObject>() {
//            @Override
//            public void onResult(JSONObject object) {
////                fragmentInteractionListener.dismissLoader();
//                try {
//                    fragmentInteractionListener.setEventProfileFrag(object.getJSONObject(Constants.RESULTS));
//                } catch (Exception e){
//                    e.printStackTrace();
//                }
//            }
//        }, new VolleySingletonErrorListener() {
//            @Override
//            public void onErrorResult(VolleyError object) {
////                fragmentInteractionListener.dismissLoader();
//                if(object.getCause() instanceof UnknownHostException){
//                    handleNoConnectionDialog();
//                }
//            }
//        });
    }

    @Override
    public void inspireMeClicked(JSONObject fullObject) {
//        fragmentInteractionListener.setInspireMeFragment(fullObject);
    }

    @Override
    public void goingToSingleEventClicked(final DiscoverObject discoverModel) {
//        try {
//            ApiService.getInstance(context).changeGoingToStatus(buildGoingToJsonObject(
//                    discoverModel.getmInfo().getJSONObject(0).getInt(Constants.GOING_TO),
//                    Integer.parseInt(discoverModel.getmInfo().
//                            getJSONObject(0).getString(Constants.ID))),
//                    new VolleySingletonListener<JSONObject>() {
//                        @Override
//                        public void onResult(JSONObject object) {
//                            try {
//                                handleGoingToToastSuccess(discoverModel.getmInfo().getJSONObject(0).
//                                        getInt(Constants.GOING_TO));
//                            } catch (Exception e){
//                                e.printStackTrace();
//                            }
//                        }
//                    }, new VolleySingletonErrorListener() {
//                        @Override
//                        public void onErrorResult(VolleyError object) {
//                            if(object.getCause() instanceof UnknownHostException){
//                                handleNoConnectionDialog();
//                            }
//                        }
//                    });
//        } catch (Exception e){
//            e.printStackTrace();
//        }
    }

    @Override
    public void carouselItemClicked(DiscoverCarouselObject carouselModel) {
        switch (carouselModel.getmType().toLowerCase()){
            case "event":
                handleCarouselEventClick(carouselModel);
                break;
            case "city":
                handleCarouselLocationClick(carouselModel);
                break;
            case "artist":
                handleCarouselArtistClick(carouselModel);
                break;
            case "genre":
                handleCarouselGenreClicked(carouselModel);
                break;
        }
    }

    @Override
    public void goingToClicked(final DiscoverCarouselObject carouselModel) {
//        try {
//            ApiService.getInstance(context).changeGoingToStatus(buildGoingToJsonObject(
//                    carouselModel.getGoingTo(), Integer.parseInt(carouselModel.getmId())),
//                    new VolleySingletonListener<JSONObject>() {
//                        @Override
//                        public void onResult(JSONObject object) {
//                            handleGoingToToastSuccess(carouselModel.getGoingTo());
//                        }
//                    }, new VolleySingletonErrorListener() {
//                        @Override
//                        public void onErrorResult(VolleyError object) {
//                            Toast.makeText(context, "Oops! Something went wrong", Toast.LENGTH_SHORT).show();
//                        }
//                    });
//        } catch (Exception e){
//            e.printStackTrace();
//        }
    }

    @Override
    public void goingToNoConnection() {
        handleNoConnectionDialog();
    }

    private JSONObject buildGoingToJsonObject(int goingTo, int eventId){
//        JSONObject jsonObject = new JSONObject();
//        try {
//            jsonObject.put(Constants.ACCESSTOKEN,
//                    BaseActivity.prefs.getString(Constants.ACCESS_TOKEN, ""));
//            jsonObject.put("going", goingTo);
//            jsonObject.put("eventid", eventId);
//        } catch (Exception e){
//            e.printStackTrace();
//        }
//        return jsonObject;
    }

    private void handleGoingToToastSuccess(int status){
//        switch (status){
//            case 1:
//                Toast.makeText(context, "Going to this event", Toast.LENGTH_SHORT).show();
//                break;
//            case 9:
//                Toast.makeText(context, "Interested in this event", Toast.LENGTH_SHORT).show();
//                break;
//        }
    }

    private void handleCarouselEventClick(DiscoverCarouselObject carouselModel){
////        fragmentInteractionListener.showLoader();
//        ApiService.getInstance(context).getEventDetails(carouselModel.getmId(), new VolleySingletonListener<JSONObject>() {
//            @Override
//            public void onResult(JSONObject object) {
////                fragmentInteractionListener.dismissLoader();
//                try {
//                    fragmentInteractionListener.setEventProfileFrag(object.getJSONObject(Constants.RESULTS));
//                } catch (Exception e){
//                    e.printStackTrace();
//                }
//            }
//        }, new VolleySingletonErrorListener() {
//            @Override
//            public void onErrorResult(VolleyError object) {
////                fragmentInteractionListener.dismissLoader();
//                if(object.getCause() instanceof UnknownHostException){
//                    handleNoConnectionDialog();
//                }
//            }
//        });
    }

    private void handleCarouselLocationClick(DiscoverCarouselObject carouselModel){
//        try {
//            FilterObject filterObject = new FilterObject();
//            filterObject.setRadius("20");
//            filterObject.setLatitude(String.valueOf(carouselModel.getmLocation().getDouble("lat")));
//            filterObject.setLongitude(String.valueOf(carouselModel.getmLocation().getDouble("lon")));
//
//            fragmentInteractionListener.setViewEventsNoCategory(filterObject, carouselModel.getmName());
//        } catch (Exception e){
//            e.printStackTrace();
//        }
    }

    private void handleCarouselArtistClick(DiscoverCarouselObject carouselModel){
//        try {
//            fragmentInteractionListener.setArtistFragment(carouselModel.getmName(),
//                    carouselModel.getmImage().getString(Constants.DEFAULT), carouselModel.getmId());
//        } catch (Exception e){
//            e.printStackTrace();
//        }
    }

    private void handleCarouselGenreClicked(DiscoverCarouselObject carouselModel){
//        FilterObject filterObject = new FilterObject();
//        filterObject.setGenres(carouselModel.getmId());
//        fragmentInteractionListener.setViewEventsNoCategory(filterObject, carouselModel.getmName());
    }

    private void handleNoConnectionDialog(){
//        fragmentInteractionListener.showSingleInfoDialog("Oops!",
//                "Please check your internet connection and try again");
    }

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            final ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
            final NetworkInfo ni = connectivityManager.getActiveNetworkInfo();
            if(ni != null && ni.isConnectedOrConnecting()){
                if(re_discover.getAdapter() == null){
                    getDiscoveryData();
                }
            }
        }
    };

}