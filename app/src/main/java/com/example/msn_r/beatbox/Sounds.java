package com.example.msn_r.beatbox;

public class Sounds {
    private String mAssetPath;
    private String mName;
    private Integer mSoundsId;

    public Sounds(String assetPath){
            mAssetPath=assetPath;
            String[] components= assetPath.split("/");
            String fileName =components[components.length-1];
            mName=fileName.replace(".wav","");
    }

    public String getmAssetPath() {
        return mAssetPath;
    }

    public String getmName() {
        return mName;
    }

    public Integer getmSoundsId() {
        return mSoundsId;
    }

    public void setmSoundsId(Integer mSoundsId) {
        this.mSoundsId = mSoundsId;
    }
}
