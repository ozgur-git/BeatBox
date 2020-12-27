package com.example.beatbox;

import javax.inject.Inject;

public class SoundViewModel {

    private Sound mSound;

    private BeatBox mBeatBox;

    public SoundViewModel(BeatBox beatBox) {
        mBeatBox = beatBox;
    }

    public Sound getSound() {
        return mSound;
    }

    public String getTitle(){
        return mSound.getName();
    }
}
