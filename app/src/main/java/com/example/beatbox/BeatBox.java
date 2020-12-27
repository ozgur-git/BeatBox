package com.example.beatbox;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;

import javax.inject.Inject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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

    public void play(Sound sound){

        Integer soundID=sound.getSoundId();


        if (soundID==null) return;

        else {
            mSoundPool.play(soundID,1.0f,1.0f,1,0,1.0f);
        }
    }
}
