package com.example.beatbox;

import android.app.Application;

public class GlobalVariables extends Application {

    ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplicationComponent=DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(getApplicationContext())).build();
    }

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }
}
