package com.example.msn_r.beatbox;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

public class SoundsViewModel extends BaseObservable{
    private Sounds mSound;
    private BeatBox mBeatBox;

    public SoundsViewModel(BeatBox mBeatBox) {
        this.mBeatBox = mBeatBox;
    }

    public Sounds getSound() {
        return mSound;
    }
    @Bindable
    public String getTitle(){
        return mSound.getmName();
    }

    public void setSound(Sounds mSound) {
        this.mSound = mSound;
        notifyChange();
    }

    public void onButtonClicked() {
        mBeatBox.play(mSound);
    }
}
