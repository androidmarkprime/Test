package com.example.markprime.test.Discover;

import com.example.markprime.test.Model.DiscoverCarouselObject;
import com.example.markprime.test.Model.DiscoverObject;

import org.json.JSONObject;

public class DiscoverAdaptersListener {

    interface DiscoverAdapterListener {
//        void seeMoreClicked(JSONObject fullJsonString, DiscoverSeeMoreListener listener);
        void singleEventClicked(String eventId);
        void inspireMeClicked(JSONObject fullObject);
        void goingToSingleEventClicked(DiscoverObject discoverObject);
    }

    interface DiscoverCarouselListener {
        void carouselItemClicked(DiscoverCarouselObject carouselObject);
        void goingToClicked(DiscoverCarouselObject carouselObject);
        void goingToNoConnection();
    }
}
