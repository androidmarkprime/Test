package com.example.markprime.test.Discover;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.markprime.test.Model.DiscoverCarouselObject;
import com.example.markprime.test.Model.DiscoverObject;
import com.example.markprime.test.Model.EventObject;

public abstract class BaseViewHolder extends RecyclerView.ViewHolder {

    public BaseViewHolder(View itemView) {
        super(itemView);
    }


    public void setContent(DiscoverObject content, int position){

    }

    public void bindView(Context context, EventObject eventObject,
                         String category, int position){

    }

//    public void setHeaderTitle(String headerTitle, FilterObject filterObject){
//
//    }

    public void setContent(DiscoverCarouselObject content, int position){

    }



}
