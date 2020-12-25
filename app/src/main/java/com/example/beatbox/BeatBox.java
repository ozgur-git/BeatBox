package com.example.beatbox;

import android.content.Context;
import android.content.res.AssetManager;

import javax.inject.Inject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

public class BeatBox {

    Logger mLogger=Logger.getLogger(getClass().getName());
//    Logger mLogger=Logger.getLogger("abc");
    private ApplicationComponent mApplicationComponent;

    private static final String TAG="BeatBox";

    private static final String SOUNDS_FOLDER="sample_sounds";

    private AssetManager mAssets;
    private List<Sound> mSounds=new ArrayList<>();
    private Context mContext;
    @Inject
    Sound sound;
    public BeatBox() {
        mApplicationComponent=DaggerApplicationComponent.builder().applicationModule(new ApplicationModule()).build();
        mApplicationComponent.inject(this);
        loadSounds();
    }

    public void setContext(Context context) {
        mContext = context;
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
            sound.setAssetPath(SOUNDS_FOLDER+"/"+item);
            sound.setName();
            mSounds.add(sound);
//            mSounds.add(new Sound(SOUNDS_FOLDER+"/"+item));//todo DI
        });
    }

    public List<Sound> getSounds() {
        return mSounds;
    }
}
