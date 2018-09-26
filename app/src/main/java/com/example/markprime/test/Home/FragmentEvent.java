package com.example.markprime.test.Home;

import android.support.annotation.Nullable;

public class FragmentEvent {

    public final String id;
    public final Object payload;

    public FragmentEvent(String id, @Nullable Object payload) {
        this.id = id;
        this.payload = payload;
    }


}
