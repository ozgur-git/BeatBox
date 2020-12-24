package com.example.beatbox;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Logger;

public class BeatBox {

    Logger mLogger=Logger.getLogger(getClass().getName());
//    Logger mLogger=Logger.getLogger("abc");

    private static final String TAG="BeatBox";

    private static final String SOUNDS_FOLDER="sample_sounds";

    private AssetManager mAssets;

    public BeatBox(Context context) {

        mAssets=context.getAssets();
        loadSounds();
    }

    private void loadSounds(){

        String[] listAssets=null;
        try {
            listAssets=mAssets.list(SOUNDS_FOLDER);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Arrays.asList(listAssets).forEach((item)->mLogger.info("filename is "+item));



    }
}
