package com.example.markprime.test.Home.CategoriesFragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.markprime.test.FragmentInteractionListener;
import com.example.markprime.test.R;

import java.util.ArrayList;

public class CategoriesFragment extends Fragment implements
        CategoriesFragmentContract.View, CategoriesItemListener{

    private CategoriesFragmentContract.Presenter presenter;
    private FragmentInteractionListener fragmentInteractionListener;
    private CategoriesAdapter categoriesAdapter;
//    private List<EventsArray> eventsArray = new ArrayList<>();

    private RecyclerView re_categories;

    private Context context;

    public CategoriesFragment(){}

    public static CategoriesFragment newInstance() {
        CategoriesFragment fragment = new CategoriesFragment();
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
        View view = inflater.inflate(R.layout.fragment_categories, container, false);

//        presenter = new CategoriesFragmentPresenter(this);
//        fragmentInteractionListener.showSearchLayout();
//        fragmentInteractionListener.hideTitleText();

//        setupViews(view);

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        context.registerReceiver(receiver, intentFilter);

        return view;
    }




    @Override
    public void handleCategoryClick(String object) {

    }

    @Override
    public void handleSavedOrRecentlyViewedClick(String type) {

    }

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            final ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
            final NetworkInfo ni = connectivityManager.getActiveNetworkInfo();
            if(ni != null && ni.isConnectedOrConnecting()){
//                if(re_categories.getAdapter() == null){
////                    getCategories();
//                }
            }
        }
    };
}
