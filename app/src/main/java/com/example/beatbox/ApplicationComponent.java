package com.example.beatbox;

import dagger.Component;

import javax.inject.Singleton;

@Component(modules = ApplicationModule.class)
@Singleton
public interface ApplicationComponent {

   void inject(BeatBoxFragment beatBoxFragment);
   void inject(BeatBox beatBox);
   void inject(BeatBoxFragment.SoundHolder soundHolder);
}
