package com.example.markprime.test.Model;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;

public class InspireMeEventsObject  implements Serializable {

    private String type, id, name, date, headerHex;
    private JSONObject image, venue, location;
    private JSONArray tags;
    private int goingTo;

    public InspireMeEventsObject(JSONObject object){
        try {
            Log.d("INSPIRE", object.toString());
            this.type = object.getString("type");
            this.id = object.getString("id");
            this.name = object.getString("name");
            this.date = object.getString("date");
            this.image = object.getJSONObject("image");
            this.venue = object.getJSONObject("venue");
            this.location = object.getJSONObject("location");
            this.headerHex = object.getString("headerhex");
            this.tags = object.getJSONArray("tags");
            this.goingTo = object.getInt("goingto");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public String getType() {
        return type;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getHeaderHex(){
        return headerHex;
    }

    public JSONObject getImage() {
        return image;
    }

    public JSONObject getVenue() {
        return venue;
    }

    public JSONObject getLocation() {
        return location;
    }

    public JSONArray getTags(){
        return tags;
    }

    public int getGoingTo(){
        return goingTo;
    }

    public void setGoingTo(int goingTo){
        this.goingTo = goingTo;
    }

}