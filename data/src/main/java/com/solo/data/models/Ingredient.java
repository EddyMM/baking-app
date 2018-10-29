package com.solo.data.models;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Ingredient implements Serializable {
    // TODO: Use parcelable instead of serializable

    @SerializedName("quantity")
    private float quantity;

    @SerializedName("measure")
    private String measure;

    @SerializedName("ingredient")
    private String name;

    @NonNull
    @Override
    public String toString() {
        return String.format("Ingredient[name=%s]", this.name);
    }

    public float getQuantity() {
        return quantity;
    }

    public String getMeasure() {
        return measure;
    }

    public String getName() {
        return name;
    }
}
