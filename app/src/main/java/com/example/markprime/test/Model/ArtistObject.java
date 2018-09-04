package com.example.markprime.test.Model;

import org.json.JSONException;
import org.json.JSONObject;

public class ArtistObject {

    private String artistName;
    private String artistImage;
    private String spotifymp3;
    private String spotifyurl;

    private JSONObject artistFullObject;


    public ArtistObject (JSONObject jsonArtistObject){

        try {
            artistName = jsonArtistObject.getString("name");
            artistImage = jsonArtistObject.getString("image");
            spotifymp3 = jsonArtistObject.getString("spotifymp3url");
            spotifyurl = jsonArtistObject.getString("spotifyartisturl");

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public String getArtistName() {
        return artistName;
    }

    public String getArtistImage() {
        return artistImage;
    }

    public String getSpotifymp3() {
        return spotifymp3;
    }

    public String getSpotifyurl() {
        return spotifyurl;
    }

    public JSONObject getArtistFullObject() {
        return artistFullObject;
    }
}
