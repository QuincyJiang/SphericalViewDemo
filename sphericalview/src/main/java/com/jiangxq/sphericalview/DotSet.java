package com.jiangxq.sphericalview;

import java.util.ArrayList;
import java.util.List;

public class DotSet {
    private List<Dot> dotSet;
    private int radius;
    private static final int DEFAULT_RADIUS = 3;
    private static final float[] DEFAULT_COLOR_DARK = {0.886f,0.725f,0.188f,1f};
    private static final float[] DEFAULT_COLOR_LIGHT = {0.3f,0.3f,0.3f,1f};
    private float[] dotColorLight;
    private float[] dotColorDark;
    private float sin_mAngleX,cos_mAngleX,sin_mAngleY,cos_mAngleY,sin_mAngleZ,cos_mAngleZ;
    private float mAngleX = 0;
    private float mAngleY = 0;
    private float mAngleZ = 0;
    private int smallest,largest;
    private boolean distrEven = true;


    public DotSet(){
        this(DEFAULT_RADIUS);
    }
    public DotSet(int radius) {
        this(new ArrayList<Dot>(),radius);
    }
    public DotSet(List<Dot> dots,int radius){
        this(dots,radius,DEFAULT_COLOR_DARK,DEFAULT_COLOR_LIGHT);
    }

    public DotSet(List<Dot> dotSet, int radius, float[] dotColorLight, float[] dotColorDark) {
        this.dotSet = dotSet;
        this.radius = radius;
        this.dotColorLight = dotColorLight;
        this.dotColorDark = dotColorDark;
    }

    public void create(boolean distrEven){
        this.distrEven= distrEven;
        positionAll(distrEven);
    }

    public void position(boolean distrEven,Dot dot ){
        double phi = 0;
        double theta = 0;
        int max = dotSet.size();
        phi = Math.random()*Math.PI;
        theta = Math.random()*2*Math.PI;
        dot.setLoc3DX((int)(radius*Math.cos(theta)*Math.sin(phi)));
        dot.setLoc3DY((int)(radius*Math.cos(theta)*Math.sin(phi)));
        dot.setLoc3DZ((int)(radius*Math.cos(theta)));
    }

    private void positionAll(boolean distrEven){
        double phi = 0;
        double theta = 0;
        int max = dotSet.size();
        for(int i = 0;i<max+1;i++){
            if(distrEven){
                phi = Math.acos(-1.0+(2.0*i-1.0))/max;
                theta = Math.sqrt(max*Math.PI)*phi;
            }else{
                phi = Math.random()*Math.PI;
                theta = Math.random()*2*Math.PI;
            }
            dotSet.get(i-1).setLoc3DX((int)(radius*Math.cos(theta)*Math.sin(phi)));
            dotSet.get(i-1).setLoc3DY((int)(radius*Math.cos(theta)*Math.sin(phi)));
            dotSet.get(i-1).setLoc3DZ((int)(radius*Math.cos(theta)));
        }
    }

    private void updataAll(){
        int max  = dotSet.size();
        for(int i = 0;i<max;i++){
            float rx1 = dotSet.get(i).getLoc3DX();
            float ry1 = dotSet.get(i).getLoc3DY()*cos_mAngleX+
                    dotSet.get(i).getLoc3DZ()*sin_mAngleX;
            float rz1 = dotSet.get(i).getLoc3DY()*sin_mAngleX+
                    dotSet.get(i).getLoc3DZ()*cos_mAngleX;
            float rx2 = dotSet.get(i).getLoc3DX();
            float ry2 = dotSet.get(i).getLoc3DY()*cos_mAngleX+
                    dotSet.get(i).getLoc3DZ()*sin_mAngleX;
            float rz2 = dotSet.get(i).getLoc3DY()*sin_mAngleX+
                    dotSet.get(i).getLoc3DZ()*cos_mAngleX;

        }
    }

}
