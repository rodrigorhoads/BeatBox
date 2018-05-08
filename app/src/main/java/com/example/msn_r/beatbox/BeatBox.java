package com.example.msn_r.beatbox;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BeatBox {
    private final static String TAG = "BeatBox";
    private static final String SOUND_FOLDER = "sample_sounds";
    private static final int MAX_SOUNDS = 5;

    private AssetManager mAssets;
    private List<Sounds> mSounds = new ArrayList<Sounds>();
    private SoundPool mSoundPool;

    public BeatBox(Context context) {
        this.mAssets = context.getAssets();
        mSoundPool = new SoundPool(MAX_SOUNDS, AudioManager.STREAM_MUSIC, 0);
        loadSonds();
    }

    private void loadSonds() {
        String[] soundNames;
        try {
            soundNames = mAssets.list(SOUND_FOLDER);
            Log.i(TAG, "Found " + soundNames.length + " sounds");
        } catch (IOException e) {
            Log.e(TAG, "not found sounds in list", e);
            return;
        }

        for (String fileName : soundNames) {
            try{
            String assetPath = SOUND_FOLDER + "/" + fileName;
            Sounds sound = new Sounds(assetPath);
            load(sound);
            mSounds.add(sound);
            } catch (IOException e) {
                Log.e(TAG,"Não pode carregar",e);
            }
        }
    }

    public void play(Sounds sound){
        Integer soundId = sound.getmSoundsId();
        if(soundId==null){
            return;
        }
        mSoundPool.play(soundId,1.0f,   1.0f,1,0,1.0f);
    }

    public void release(){
        mSoundPool.release();
    }
    private void load(Sounds sounds) throws IOException {
            AssetFileDescriptor afd = mAssets.openFd(sounds.getmAssetPath());
            int soundId= mSoundPool.load(afd,1);
            sounds.setmSoundsId(soundId);
    }
    public List<Sounds> getmSounds() {
        return mSounds;
    }
}
