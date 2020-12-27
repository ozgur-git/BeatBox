package com.example.beatbox;

import android.content.Context;
import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    Context mContext;

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
       return new BeatBox(mContext);
   }

   @Provides
    SoundViewModel provideSoundViewModel(){
        return new SoundViewModel(new BeatBox(mContext));
   }
}

