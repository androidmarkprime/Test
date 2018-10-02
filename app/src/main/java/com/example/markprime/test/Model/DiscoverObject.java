package com.example.markprime.test.Model;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

public class DiscoverObject {

    private String mType, mTitle, mIntro, mUrl, mUrlTitle;
    private JSONArray mInfo;
    private JSONObject image, mFullObject;

    public DiscoverObject(JSONObject object){
        Log.d("OBJECT", object.toString());
        try {
            this.mFullObject = object;
            if(object.has(Constants.TYPE)){
                this.mType = object.getString(Constants.TYPE);
            }
            if(object.has(Constants.TITLE)){
                this.mTitle = object.getString(Constants.TITLE);
            }
            if(object.has(Constants.INTRO)){
                this.mIntro = object.getString(Constants.INTRO);
            }
            if(object.has(Constants.URL)){
                this.mUrl = object.getString(Constants.URL);
            }
            if(object.has(Constants.URL_TITLE)){
                this.mUrlTitle = object.getString(Constants.URL_TITLE);
            }
            if(object.has(Constants.INFO)){
                this.mInfo = object.getJSONArray(Constants.INFO);
            }
            if(object.has(Constants.IMAGE)){
                this.image = object.getJSONObject(Constants.IMAGE);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public String getmType() {
        return mType;
    }

    public String getmTitle() {
        return mTitle;
    }

    public String getmIntro() {
        return mIntro;
    }

    public String getmUrl() {
        return mUrl;
    }

    public String getmUrlTitle() {
        return mUrlTitle;
    }

    public JSONArray getmInfo() {
        return mInfo;
    }

    public JSONObject getImage(){
        return image;
    }

    public JSONObject getmFullObject(){ return mFullObject; }
}
