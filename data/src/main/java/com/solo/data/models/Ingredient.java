package com.solo.data.models;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

class Ingredient {

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
}
