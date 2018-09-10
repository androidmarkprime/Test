//package com.example.markprime.test.Discover;
//
//import android.content.BroadcastReceiver;
//import android.content.Context;
//import android.content.Intent;
//import android.content.IntentFilter;
//import android.net.ConnectivityManager;
//import android.net.NetworkInfo;
//import android.os.Bundle;
//import android.support.v4.app.Fragment;
//import android.support.v4.widget.SwipeRefreshLayout;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import com.example.markprime.test.FragmentInteractionListener;
//import com.example.markprime.test.Model.DiscoverObject;
//import com.example.markprime.test.Network.NetworkManager;
//import com.example.markprime.test.Network.VolleySingletonErrorListener;
//import com.example.markprime.test.Network.VolleySingletonListener;
//import com.example.markprime.test.R;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.util.ArrayList;
//import java.util.List;
//
//
//public class DiscoverFragment extends Fragment implements DiscoverAdapterListener {
//
//    private DiscoverFragmentContract.Presenter presenter;
//    private FragmentInteractionListener fragmentInteractionListener;
//    private Context context;
//    private RecyclerView re_discover;
//    private SwipeRefreshLayout discover_swiperefresh;
//    private List<DiscoverObject> discoverList = new ArrayList<>();
//    private DiscoverAdapter discoverAdapter;
//
//    public DiscoverFragment() {
//        // Required empty public constructor
//    }
//
//    public static DiscoverFragment newInstance() {
//        DiscoverFragment fragment = new DiscoverFragment();
//        Bundle args = new Bundle();
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }
//
//
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        View view = inflater.inflate(R.layout.fragment_discover, container, false);
//
//        setUpRecyclerView(view);
//        setUpSwipeRefreshLayout(view);
//
//        IntentFilter intentFilter = new IntentFilter();
//        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
//        context.registerReceiver(receiver, intentFilter);
//
//        getDiscoveryData();
//
//        return view;
//
//    }
//
//
//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        this.context = context;
//        fragmentInteractionListener = (FragmentInteractionListener) context;
//
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//    }
//
//
//    private void setUpRecyclerView(View view){
//        re_discover = view.findViewById(R.id.re_discover);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
//        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//        re_discover.setLayoutManager(linearLayoutManager);
//    }
//
//    private void setUpSwipeRefreshLayout(View view){
//        discover_swiperefresh = view.findViewById(R.id.discover_swiperefresh);
//
//        discover_swiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                re_discover.setAdapter(null);
//                getDiscoveryData();
//            }
//        });
//    }
//
//    private void getDiscoveryData() {
//        NetworkManager.getInstance(context).exampleGetRequest("https://www.skiddle.com/api/v1/events/search?api_key=008f1e6099ecc48e990e3776784d447b&platform=android&type=mobileapp&version=97&limit=40&offset=0&description=1&imagefilter=1&platform=android&order=date&radius=30&latitude=53.3994794&longitude=-2.524805&eventcode=4&aggs=genreids,eventcode", new VolleySingletonListener<JSONObject>() {
//            @Override
//            public void onResult(JSONObject object) {
//
//                try {
//                    JSONArray jsonArray = object.getJSONArray("results");
//                    for (int i = 0; i < jsonArray.length(); i++) {
//                        discoverList.add(new DiscoverObject(jsonArray.getJSONObject(i)));
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//                setAdapter();
//
//                Log.d("RESPONSE", object.toString());
//            }
//        }, new VolleySingletonErrorListener() {
//            @Override
//            public void onErrorResult(Exception error) {
//
//
//                Log.d("ERROR", error.getMessage() + " ");
//            }
//        });
//    }
//
//    private void setAdapter() {
//        discoverAdapter = new DiscoverAdapter(context, discoverList, this);
//        re_discover.setAdapter(discoverAdapter);
//    }
//
//    private void setSwipeRefreshNotRefreshing(){
//        if(discover_swiperefresh != null){
//            discover_swiperefresh.setRefreshing(false);
//        }
//    }
//
//    private BroadcastReceiver receiver = new BroadcastReceiver() {
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            final ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
//            final NetworkInfo ni = connectivityManager.getActiveNetworkInfo();
//            if (ni != null && ni.isConnectedOrConnecting()) {
//                if (re_discover.getAdapter() == null) {
//                    getDiscoveryData()
//
//                    ;}else
//
//                    return;
//            }
//        }
//
//
//
//    };
//}
