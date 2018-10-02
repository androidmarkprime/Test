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
            this.mType = object.getString(Constants.TYPE);
            if(object.has(Constants.ID)){
                this.mId = object.getString(Constants.ID);
            }
            if(object.has(Constants.NAME)){
                this.mName = object.getString(Constants.NAME);
            }
            if(object.has(Constants.INTRO)){
                this.mIntro = object.getString(Constants.INTRO);
            }
            this.mImage = object.getJSONObject(Constants.IMAGE);
            if(object.has(Constants.VENUE)){
                this.mVenue = object.getJSONObject(Constants.VENUE);
            }
            if(object.has(Constants.DATE)){
                this.mDate = object.getString(Constants.DATE);
            }
            if(object.has(Constants.LOCATION)){
                this.mLocation = object.getJSONObject(Constants.LOCATION);
            }
            if(object.has(Constants.TAGS)){
                this.tags = object.getJSONArray(Constants.TAGS);
            }
            if(object.has(Constants.GOING_TO)){
                this.goingTo = object.getInt(Constants.GOING_TO);
            }
            if(object.has(Constants.LISTING_ID_LOWER_CASE)){
                this.listingId = object.getString(Constants.LISTING_ID_LOWER_CASE);
            }
            if(object.has(Constants.STATS)){
                this.stats = object.getJSONArray(Constants.STATS);
            }
            if(object.has(Constants.POINTS)){
                this.mPoints = object.getInt(Constants.POINTS);
            }
            if(object.has(Constants.URL)){
                this.mUrl = object.getString(Constants.URL);
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