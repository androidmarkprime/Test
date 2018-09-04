package com.example.markprime.test.Model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class EventObject {

    private String eventName;
    private String venueName;
    private String venueAdd;
    private String venueTown;
    private String venuePostCode;
    private String venuePhone;
    private String imageUrl;
    private String date;
    private String description;
    private String doorsOpen;
    private String doorsClose;
    private String lastEntry;
    private String minAge;
    private Boolean tickets;
    private String entryPrice;
    private JSONArray artists;
    private JSONArray genres;

    private JSONObject fullObject;


    public EventObject(JSONObject jsonEventObject){

        try {
            eventName =jsonEventObject.getString("eventname");
            venueName =jsonEventObject.getJSONObject("venue").getString("name");
            venueAdd =jsonEventObject.getJSONObject("venue").getString("address");
            venueTown =jsonEventObject.getJSONObject("venue").getString("town");
            venuePostCode =jsonEventObject.getJSONObject("venue").getString("postcode");
            venuePhone =jsonEventObject.getJSONObject("venue").getString("phone");
            imageUrl =jsonEventObject.getString("largeimageurl");
            date =jsonEventObject.getString("date");
            description =jsonEventObject.getString("description");
            doorsOpen =jsonEventObject.getJSONObject("openingtimes").getString("doorsopen");
            doorsClose =jsonEventObject.getJSONObject("openingtimes").getString("doorsclose");
            lastEntry =jsonEventObject.getJSONObject("openingtimes").getString("lastentry");
            minAge =jsonEventObject.getString("minage");
            tickets =jsonEventObject.getBoolean("tickets");
            entryPrice =jsonEventObject.getString("entryprice");
            artists =jsonEventObject.getJSONArray("artists");
            genres =jsonEventObject.getJSONArray("genres");
            fullObject=jsonEventObject;

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    public String getEventName() {
        return eventName;
    }

    public String getVenueName() {
        return venueName;
    }

    public String getVenueAdd() {
        return venueAdd;
    }

    public String getVenueTown() {
        return venueTown;
    }

    public String getVenuePostCode() {
        return venuePostCode;
    }

    public String getVenuePhone() {
        return venuePhone;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public String getDoorsOpen() {
        return doorsOpen;
    }

    public String getDoorsClose() {
        return doorsClose;
    }

    public String getLastEntry() {
        return lastEntry;
    }

    public String getMinAge() {
        return minAge;
    }

    public Boolean getTickets() {
        return tickets;
    }

    public String getEntryPrice() {
        return entryPrice;
    }

    public JSONArray getArtists() {
        return artists;
    }

    public JSONArray getGenres() {
        return genres;
    }

    public JSONObject getFullObject() { return fullObject; }
}
