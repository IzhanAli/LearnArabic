package com.example.miwok;

public class Word {
    private String mDefaultword;
    private String mMivokword;
    private int mResId;
    public static final int IMAGE_PROVIDED = -1;
    public Word(String def, String mivok){
        mDefaultword = def;
        mMivokword = mivok;

    }
    public Word(String def, String mivok, int res){
        mDefaultword = def;
        mMivokword = mivok;
        mResId = res;

    }

    public String getDefaultword() {
        return mDefaultword;
    }
    public String getMivokword(){
        return mMivokword;
    }
    public int getImageId(){
        return mResId;
    }

    public boolean hasImg(){
        return mResId != IMAGE_PROVIDED;
    }
}
