package com.example.beatbox;

import android.app.Application;

public class GlobalVariable extends Application {

    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplicationComponent=DaggerApplicationComponent.builder().applicationModule(new ApplicationModule()).build();
    }

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }
}
