package com.solo.bakingapp;

import android.app.Application;

import timber.log.Timber;


public class BakingApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        initLogging();
    }

    private void initLogging() {
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }
}
