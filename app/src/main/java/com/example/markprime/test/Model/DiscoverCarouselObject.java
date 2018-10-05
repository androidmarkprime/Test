package com.example.markprime.test.Model;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

public class DiscoverCarouselObject {

    private String mType, mId, mName, mIntro, mDate, mUrl, listingId;
    private JSONObject mImage, mVenue, mLocation;
    private JSONArray tags = new JSONArray();
    private JSONArray stats = new JSONArray();
    private int goingTo, mPoints;

    public DiscoverCarouselObject(JSONObject object){
        try {
            Log.d("OBJ", object.toString());
            this.mType = object.getString("type");
            if(object.has("id")){
                this.mId = object.getString("id");
            }
            if(object.has("name")){
                this.mName = object.getString("name");
            }
            if(object.has("intro")){
                this.mIntro = object.getString("intro");
            }
            this.mImage = object.getJSONObject("image");
            if(object.has("venue")){
                this.mVenue = object.getJSONObject("venue");
            }
            if(object.has("date")){
                this.mDate = object.getString("date");
            }
            if(object.has("location")){
                this.mLocation = object.getJSONObject("location");
            }
            if(object.has("tags")){
                this.tags = object.getJSONArray("tags");
            }
            if(object.has("goingto")){
                this.goingTo = object.getInt("goingto");
            }
            if(object.has("listings_id_lower_case")){
                this.listingId = object.getString("listings_id_lower_case");
            }
            if(object.has("stats")){
                this.stats = object.getJSONArray("stats");
            }
            if(object.has("points")){
                this.mPoints = object.getInt("points");
            }
            if(object.has("url")){
                this.mUrl = object.getString("url");
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public String getmType() {
        return mType;
    }

    public String getmId() {
        return mId;
    }

    public String getmName() {
        return mName;
    }

    public String getmIntro() {
        return mIntro;
    }

    public JSONObject getmImage() {
        return mImage;
    }

    public String getmDate() {
        return mDate;
    }

    public JSONObject getmVenue() {
        return mVenue;
    }

    public JSONObject getmLocation(){
        return mLocation;
    }

    public JSONArray getTags(){
        return tags;
    }

    public int getGoingTo(){
        return goingTo;
    }

    public JSONArray getStats() {
        return stats;
    }

    public int getmPoints(){
        return mPoints;
    }

    public String getmUrl(){
        return mUrl;
    }

    public String getListingId() {
        return listingId;
    }

    public void setGoingTo(int goingTo){
        this.goingTo = goingTo;
    }
}