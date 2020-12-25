package com.example.beatbox;

public class Sound {

    private String mAssetPath;
    private String mName;

    public void setAssetPath(String assetPath) {
        mAssetPath = assetPath;
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
