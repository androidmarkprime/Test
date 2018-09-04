package com.example.markprime.test.Model;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class DeliveryObject extends Drawable{

    private Drawable iv_delivery_image;
    private String tv_delivery_name;
    private String tv_delivery_description;
    private String tv_delivery_price;

    public DeliveryObject(Drawable image, String name, String description,
                          String price){
        this.iv_delivery_image = image;
        this.tv_delivery_name = name;
        this.tv_delivery_description = description;
        this.tv_delivery_price = price;
    }

    public Drawable getIv_delivery_image() {
        return iv_delivery_image;
    }

    public String setTv_delivery_name() {
        return tv_delivery_name;
    }

    public String getTv_delivery_description() {
        return tv_delivery_description;
    }

    public String getTv_delivery_price() {
        return tv_delivery_price;
    }

    @Override
    public void draw(@NonNull Canvas canvas) {

    }

    @Override
    public void setAlpha(int i) {

    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {

    }

    @Override
    public int getOpacity() {
        return 0;
    }
}
