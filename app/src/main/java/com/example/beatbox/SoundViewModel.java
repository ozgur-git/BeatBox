package com.example.beatbox;

import android.widget.SeekBar;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.ObservableField;

import java.util.logging.Logger;

public class SoundViewModel extends BaseObservable {

    Logger mLogger=Logger.getLogger(getClass().getName());

    private int changeValue;

    private Sound mSound;
    private BeatBox mBeatBox;

    public SoundViewModel(BeatBox beatBox) {
        mBeatBox = beatBox;
    }

    public void setSound(Sound sound) {
        mSound = sound;
        notifyPropertyChanged(BR.title);
    }

    public Sound getSound() {
        return mSound;
    }

    public int getChangeValue() {
        return changeValue;
    }

    public void setChangeValue(int changeValue) {
        this.changeValue = changeValue;
        mLogger.info("progress is "+changeValue);
    }

    @Bindable
    public String getTitle(){
        return mSound.getName();
    }

    public BeatBox getBeatBox() {
        return mBeatBox;
    }

    public void onButtonClicked() {
        mBeatBox.play(mSound);
        mLogger.info("change value is "+changeValue);
   }

    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        changeValue=progress;


    }

}
