package com.example.beatbox;

import android.content.Context;
import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    Context mContext;
    BeatBox mBeatBox;

    public ApplicationModule(Context context) {
        mContext = context;
    }


    @Provides
   Sound provideSounds()
    {
       return new Sound();
   }

   @Provides
    BeatBox provideBeatBox() {
        mBeatBox=new BeatBox(mContext);
       return mBeatBox;
   }

   @Provides
    SoundViewModel provideSoundViewModel(){
        return new SoundViewModel(mBeatBox);
   }
}

