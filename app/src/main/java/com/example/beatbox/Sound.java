package com.example.beatbox;

public class Sound {

    private String mAssetPath;
    private String mName;

    public void setAssetPath(String assetPath) {
        mAssetPath = assetPath;
    }

    public Sound() {
    }

    public Sound(String assetPath) {
        mAssetPath = assetPath;
        String[] components=mAssetPath.split("/");
        mName=components[1].replace(".wav","");

    }

    public String getAssetPath() {
        return mAssetPath;
    }

    public void setName() {
        String[] components=mAssetPath.split("/");
        mName=components[1].replace(".wav","");
    }

    public String getName() {
       return mName;
    }
}
