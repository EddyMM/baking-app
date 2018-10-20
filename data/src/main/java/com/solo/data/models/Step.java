package com.solo.data.models;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

class Step {

    @SerializedName("id")
    private int id;

    @SerializedName("shortDescription")
    private String shortDescription;

    @SerializedName("description")
    private String description;

    @SerializedName("videoURL")
    private String videoUrl;

    @SerializedName("thumbnailURL")
    private String thumbnailUrl;

    @NonNull
    @Override
    public String toString() {
        return String.format("Step[description=%s]", this.description);
    }
}
