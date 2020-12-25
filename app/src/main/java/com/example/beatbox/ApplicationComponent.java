package com.example.beatbox;

import dagger.Component;

@Component(modules = ApplicationComponent.class)
public interface ApplicationComponent {

   void inject(BeatBoxFragment beatBoxFragment);
   void inject(BeatBox beatBox);
}
