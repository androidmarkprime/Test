package com.example.markprime.test.Model;

import org.json.JSONException;
import org.json.JSONObject;

public class GenreObject {

    private String genreName;

    public GenreObject(JSONObject jsonGenreObject){

        try {
            genreName = jsonGenreObject.getString("name");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getGenreName() {
        return genreName;
    }
}
