package com.example.markprime.test.Discover.InspireMe;

import com.example.markprime.test.Model.InspireMeEventsObject;

public interface InspireMeAdapterListener {

    void itemClicked(InspireMeEventsObject eventsModel);
    void goingToClicked(InspireMeEventsObject eventsModel);
    void goingToClickedNoConnection();
}
