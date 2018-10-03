package com.example.markprime.test.Discover.InspireMe;

import com.example.markprime.test.Model.InspireMeEventsObject;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class InspireMeFragmentPresenter implements InspireMeFragmentContract.Presenter {

    private InspireMeFragmentContract.View view;
    private List<InspireMeEventsObject> inspireMeEventsList= new ArrayList();

    InspireMeFragmentPresenter(InspireMeFragmentContract.View view){
        this.view = view;
    }

    @Override
    public List<InspireMeEventsObject> buildInspireMeEventsList(JSONArray data) {
        for(int i = 0; i < data.length(); i++){
            try {
                inspireMeEventsList.add(new InspireMeEventsObject(data.getJSONObject(i)));
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return inspireMeEventsList;
    }
}
