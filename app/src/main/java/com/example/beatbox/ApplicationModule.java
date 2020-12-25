package com.example.beatbox;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

   @Provides
   Sound provideSounds(){
       return new Sound();
   }

   @Provides
    BeatBox provideBeatBox() {
       return new BeatBox();
   }
}

