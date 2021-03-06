package com.example.beatbox;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.widget.SeekBar;

import javax.inject.Inject;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

public class BeatBox {

    Logger mLogger=Logger.getLogger(getClass().getName());
//    Logger mLogger=Logger.getLogger("abc");

    private static final int MAX_SOUNDS=5;

    private static final String TAG="BeatBox";

    private static final String SOUNDS_FOLDER="sample_sounds";

    private AssetManager mAssets;
    private List<Sound> mSounds=new ArrayList<>();
    private Context mContext;
    private SoundPool mSoundPool;

    private float mPlayBackSpeed;

    @Inject
    Sound sound;

    public BeatBox(Context context) {
        mContext=context;
        mSoundPool=new SoundPool(MAX_SOUNDS, AudioManager.STREAM_MUSIC,0);
        loadSounds();
    }

    private void load(Sound sound){
        AssetFileDescriptor assetFileDescriptor=null;
        try {
            assetFileDescriptor=mAssets.openFd(sound.getAssetPath());
        } catch (IOException e) {
            e.printStackTrace();
        }

        sound.setSoundId(mSoundPool.load(assetFileDescriptor,1));
    }

    private void loadSounds(){
        mAssets=mContext.getAssets();
        InputStreamReader inputStreamReader=null;
        LinkedList<Character> mCharList=new LinkedList<>();
        try {
            inputStreamReader=new InputStreamReader(mAssets.open("sample_text"+ File.separator+"input.txt"));
           int data=inputStreamReader.read();
            while (data!=-1){
                mCharList.add((char)data);
                data=inputStreamReader.read();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        mLogger.info("Char array is "+mCharList);
        String[] listAssets=null;
        try {
            listAssets=mAssets.list(SOUNDS_FOLDER);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Arrays.asList(listAssets).forEach((item)->{
            mLogger.info("filename is "+item);
            ((GlobalVariables)mContext.getApplicationContext()).getApplicationComponent().inject(this);
            sound.setAssetPath(SOUNDS_FOLDER+"/"+item);
            sound.setName();
            mSounds.add(sound);
            load(sound);
//            mSounds.add(new Sound(SOUNDS_FOLDER+"/"+item));//todo DI
        });
    }

    public List<Sound> getSounds() {
        return mSounds;
    }

    public void setPlayBackSpeed(float playBackSpeed) {
        mPlayBackSpeed = playBackSpeed;
    }

    public void play(Sound sound){
        Integer soundID=sound.getSoundId();
        mLogger.info("playback speed is "+mPlayBackSpeed);
        if (soundID==null) return;
        else {
            mSoundPool.play(soundID,1.0f,1.0f,1,0,mPlayBackSpeed);
        }
    }

    public void release(){
        mSoundPool.release();
    }

//    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//
//        mPlayBackSpeed=(float) progress/100;
//    }
}
