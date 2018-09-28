package com.example.markprime.test.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;


import com.example.markprime.test.Model.EventObject;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SharedPrefs {

    public static final String PREFS_SAVED = "PREFS_SAVED";
    public static final String SAVED = "Saved";
    public static final String PREFS_TICKETS = "PREFS_TICKETS";
    public static final String TIKCETS = "Tickets";

    public SharedPrefs() {
        super();
    }

    // This four methods are used for maintaining favorites.
    public void saveEvents(Context context, List<EventObject> save) {
        SharedPreferences settings;
        Editor editor;

        settings = context.getSharedPreferences(PREFS_SAVED,
                Context.MODE_PRIVATE);
        editor = settings.edit();

        Gson gson = new Gson();
        String jsonSaved = gson.toJson(save);

        editor.putString(SAVED, jsonSaved);

        editor.apply();
    }

    public void addEvents(Context context, EventObject eventObject) {
        List<EventObject> save = getEvents(context);
        if (save == null)
            save = new ArrayList<EventObject>();
        save.add(eventObject);
        saveEvents(context, save);
    }

    public void removeEvents(Context context, EventObject eventObject) {
        ArrayList<EventObject> save = getEvents(context);
        if (save != null) {
            save.remove(eventObject);
            saveEvents(context, save);
        }
    }

    public ArrayList<EventObject> getEvents(Context context) {
        SharedPreferences settings;
        List<EventObject> save;

        settings = context.getSharedPreferences(PREFS_SAVED,
                Context.MODE_PRIVATE);

        if (settings.contains(SAVED)) {
            String jsonSaved = settings.getString(SAVED, null);
            Gson gson = new Gson();
            EventObject[] savedItems = gson.fromJson(jsonSaved,
                    EventObject[].class);

            save = Arrays.asList(savedItems);
            save = new ArrayList<EventObject>(save);
        } else
            return null;

        return (ArrayList<EventObject>) save;
    }


}
