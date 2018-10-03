package com.example.markprime.test.Discover.SeeMore;

import com.example.markprime.test.Model.InspireMeEventsObject;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class SeeMoreFragmentPresenter implements SeeMoreFragmentContract.Presenter{

    private SeeMoreFragmentContract.View view;
    private List<InspireMeEventsObject> eventsList = new ArrayList<>();

    SeeMoreFragmentPresenter(SeeMoreFragmentContract.View view){
        this.view = view;
    }

    @Override
    public List<InspireMeEventsObject> buildInspireMeList(JSONArray data) {
        for(int i = 0; i < data.length(); i++){
            try {
                eventsList.add(new InspireMeEventsObject(data.getJSONObject(i)));
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return eventsList;
    }
}
