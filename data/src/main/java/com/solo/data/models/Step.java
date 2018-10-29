package com.solo.data.models;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Step implements Serializable {
    // TODO: Use parcelable instead of serializable

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

    public String getShortDescription() {
        return shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }
}
