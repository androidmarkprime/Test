package com.example.markprime.test.Discover.SeeMore;

import com.example.markprime.test.Model.InspireMeEventsObject;

import org.json.JSONArray;

import java.util.List;

public interface SeeMoreFragmentContract {

    interface Presenter {
        List<InspireMeEventsObject> buildInspireMeList(JSONArray data);
    }

    interface View {

    }

}
