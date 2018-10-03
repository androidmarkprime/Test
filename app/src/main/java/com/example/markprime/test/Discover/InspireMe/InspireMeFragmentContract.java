package com.example.markprime.test.Discover.InspireMe;

import com.example.markprime.test.Model.InspireMeEventsObject;

import org.json.JSONArray;

import java.util.List;

public interface InspireMeFragmentContract{

    interface View {

    }

    interface Presenter {
        List<InspireMeEventsObject> buildInspireMeEventsList(JSONArray data);
    }

}
