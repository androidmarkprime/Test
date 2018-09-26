package com.example.markprime.test.Home;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.markprime.test.Home.Events.EventsFragment;
import com.example.markprime.test.Home.MyTickets.MyTicketsFragment;
import com.example.markprime.test.Home.SavedEvents.SavedEventsFragment;
import com.example.markprime.test.R;

import java.util.ArrayList;
import java.util.List;

public class ViewHolderFragment extends Fragment implements ViewHolderFragmentContract.View{


    private ViewHolderFragmentAdapter pagerAdapter;

    private ViewPager vp_home;
    private TabLayout tablayout_home;
    private Context context;
    private ViewHolderFragmentContract.Presenter presenter;


    public ViewHolderFragment() {
        // Required empty public constructor
    }

    public static ViewHolderFragment newInstance() {
        ViewHolderFragment fragment = new ViewHolderFragment();
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
        View view = inflater.inflate(R.layout.fragment_view_holder, container, false);

        setUpViewPager(view);
        setUpTabLayout(view);

        setUpPagerAdapter();

        presenter = new ViewHolderFragmentPresenter(this);

        return view;
    }

    private void setUpViewPager(View view){
        vp_home = view.findViewById(R.id.vp_home);
    }

    private void setUpTabLayout(View view){
        tablayout_home = view.findViewById(R.id.tablayout_home);
    }

    private void setUpPagerAdapter(){
        List<android.support.v4.app.Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(EventsFragment.newInstance());
        fragmentList.add(SavedEventsFragment.newInstance());
        fragmentList.add(MyTicketsFragment.newInstance());
        pagerAdapter = new ViewHolderFragmentAdapter(getChildFragmentManager(), fragmentList, context);
        vp_home.setAdapter(pagerAdapter);
        tablayout_home.setSelectedTabIndicatorColor(getResources().
                getColor(R.color.Turq));
        tablayout_home.setTabTextColors(
                getResources().getColor(R.color.Grey), getResources().
                        getColor(R.color.Turq));
        tablayout_home.setupWithViewPager(vp_home);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}

