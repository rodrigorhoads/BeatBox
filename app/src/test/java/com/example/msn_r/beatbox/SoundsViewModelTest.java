package com.example.msn_r.beatbox;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;


public class SoundsViewModelTest {
    private BeatBox beatBox;
    private Sounds sound;
    private SoundsViewModel mSubject;
    @Before
    public void setUp() throws Exception {
        beatBox=  mock(BeatBox.class);
        sound=new Sounds("assetPath");
        mSubject=new SoundsViewModel(beatBox);
        mSubject.setSound(sound);
    }

    @Test
    public void exposedSoundNameAsTitle(){
        assertThat(mSubject.getTitle(),is(sound.getmName()));
    }

    @Test
    public void callsBeatBoxPlayOnButtonClick(){
        mSubject.onButtonClicked();

        verify(beatBox).play(sound);
    }
}