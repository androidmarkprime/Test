package com.example.markprime.test.Home.SavedEvents;

import com.example.markprime.test.Model.EventObject;

import java.util.ArrayList;
import java.util.List;

public interface SavedEventsContract {

    interface Presenter {
        void clearOldestEvents(String type);
        void checkEventDateIsValid(List<EventObject> savedEventList);
        ArrayList<EventObject> getEventsByType(String type);
    }

    interface View {
        long getTimeStamp();
        long convertDateToTimeStamp(String dateString);
    }
}
