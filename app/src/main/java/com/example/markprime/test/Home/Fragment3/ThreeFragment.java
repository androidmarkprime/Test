package com.example.markprime.test.Home.Fragment3;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.markprime.test.Home.Fragment2.TwoFragment;
import com.example.markprime.test.R;

public class ThreeFragment extends Fragment {

    public ThreeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_three, container, false);
    }

    public static ThreeFragment newInstance(int i) {

        ThreeFragment fragmentThree = new ThreeFragment();
        Bundle args = new Bundle();
        fragmentThree.setArguments(args);
        return fragmentThree;

    }
}
