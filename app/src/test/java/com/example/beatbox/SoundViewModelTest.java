package com.example.beatbox;

import org.junit.Before;

import javax.inject.Inject;

import static org.mockito.Mockito.mock;

public class SoundViewModelTest {

    private BeatBox mBeatBox;
    private Sound mSound;
    private SoundViewModel mSubject;

    @Before
    public void setUp() throws Exception {
        mBeatBox=mock(BeatBox.class);
        mSound=new Sound("assetPath");
        mSubject=new SoundViewModel(mBeatBox);
        mSubject.setSound(mSound);
    }


}
