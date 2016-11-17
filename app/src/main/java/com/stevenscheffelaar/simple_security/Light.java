package com.stevenscheffelaar.simple_security;

/**
 * Created by Steven on 11/16/2016.
 */

public class Light {
    private int mLightNum;
    private String mOnCode;
    private String mOffCode;


    public Light(int lightNum, String onCode, String offCode) {
        mLightNum =lightNum;
        mOnCode = onCode;
        mOffCode = offCode;
    }

    public int getLightNum() { return mLightNum; }

    public String getOnCode() { return mOnCode; }

    public String getOffCode() { return mOffCode; }
}
