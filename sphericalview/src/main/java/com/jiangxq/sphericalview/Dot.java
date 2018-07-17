package com.jiangxq.sphericalview;

import android.graphics.Color;

/**
 * 记录球体上每个点的坐标
 * */
public class Dot {
    private float loc3DX,loc3DY,loc3DZ;
    private float loc2DX,loc2DY;
    private float scale;
    private float[] argb;
    private int popularity;
    private static final int DEFAULT_POPULARITY = 5;

    public Dot(float loc3DX, float loc3DY, float loc3DZ) {
        this(loc3DX,loc3DY,loc3DZ,1.0f);
    }

    public Dot(float loc3DX, float loc3DY, float loc3DZ, float scale) {
        this(loc3DX,loc3DY,loc3DZ,scale,DEFAULT_POPULARITY);
    }

    public Dot(float loc3DX, float loc3DY, float loc3DZ, float scale, int popularity) {
        this.loc3DX = loc3DX;
        this.loc3DY = loc3DY;
        this.loc3DZ = loc3DZ;
        this.scale = scale;
        this.popularity = popularity;
    }

    public float getLoc3DX() {
        return loc3DX;
    }

    public void setLoc3DX(float loc3DX) {
        this.loc3DX = loc3DX;
    }

    public float getLoc3DY() {
        return loc3DY;
    }

    public void setLoc3DY(float loc3DY) {
        this.loc3DY = loc3DY;
    }

    public float getLoc3DZ() {
        return loc3DZ;
    }

    public void setLoc3DZ(float loc3DZ) {
        this.loc3DZ = loc3DZ;
    }

    public float getLoc2DX() {
        return loc2DX;
    }

    public void setLoc2DX(float loc2DX) {
        this.loc2DX = loc2DX;
    }

    public float getLoc2DY() {
        return loc2DY;
    }

    public void setLoc2DY(float loc2DY) {
        this.loc2DY = loc2DY;
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public float[] getArgb() {
        return argb;
    }

    public void setArgb(float[] argb) {
        this.argb = argb;
    }

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }
    public void setColorByArray(float[] rgb){
        if(rgb!=null){
            System.arraycopy(rgb,0,this.argb,this.argb.length-rgb.length,rgb.length);
        }
    }
    public int getColor(){
        int[] result = new int[4];
        for(int i = 0;i<4;i++){
            result[i] = (int)(this.argb[i]*256);
        }
        return Color.argb(result[0],result[1],result[2],result[3]);
    }
}
