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
            if(object.has("type")){
                this.mType = object.getString("type");
            }
            if(object.has("title")){
                this.mTitle = object.getString("title");
            }
            if(object.has("intro")){
                this.mIntro = object.getString("intro");
            }
            if(object.has("url")){
                this.mUrl = object.getString("url");
            }
            if(object.has("url_title")){
                this.mUrlTitle = object.getString("url_title");
            }
            if(object.has("info")){
                this.mInfo = object.getJSONArray("info");
            }
            if(object.has("image")){
                this.image = object.getJSONObject("image");
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
