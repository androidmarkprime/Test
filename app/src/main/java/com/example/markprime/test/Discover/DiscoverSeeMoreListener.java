package com.example.markprime.test.Discover;

import android.os.Parcelable;

public interface DiscoverSeeMoreListener extends Parcelable {
    void updateDiscoverGoingToStatus(String ID, int goingTo);
}
