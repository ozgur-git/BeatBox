package com.example.beatbox;

import dagger.Component;

@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

   void inject(BeatBoxFragment beatBoxFragment);
   void inject(BeatBox beatBox);
   void inject(BeatBoxFragment.SoundHolder soundHolder);
}
