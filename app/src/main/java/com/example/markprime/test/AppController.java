package com.example.markprime.test;

import android.app.Application;

import com.example.markprime.test.Network.NetworkManager;

public class AppController extends Application{

    @Override
    public void onCreate() {
        super.onCreate();

        NetworkManager.getInstance(this);
    }

}
