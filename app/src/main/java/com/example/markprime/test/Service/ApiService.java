package com.example.markprime.test.Service;

import android.content.Context;

public class ApiService {

    private static final String TAG = ApiService.class.getSimpleName();

    private static ApiService instance = null;

    private String API_URL = "https://www.skiddle.com/api/v1/";
    private String API_URL2 = "https://www.skiddle.com/api/v2/";
    private final String API_PREFERENCES = "user/preferences/";
    private final String API_USER = "user/%1$s/";
    private final String API_EVENTS = "events/";
    private final String API_TICKETS = "events/%1$s/tickets/";
    private final String API_USER_ORDERS = "user/orders/";
    private final String API_ARTISTS = "artist/%1$s/";
    private final String API_GET_CHECKOUT = "events/%1s/tickets/";
    private final String GENRES = "genres/";


    private final Context _context;

    private ApiService(Context context) {
        _context = context;
    }

    public static synchronized ApiService getInstance(Context context) {
        if (instance == null) {
            instance = new ApiService(context);
        }
        return instance;
    }


}
