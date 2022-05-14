package com.p6majo.utils;

import com.p6majo.math.linalg.Vector3D;

/**
 * The class Utils
 *
 * @author p6maj
 * @version 2022-05-12
 */
public class Utils {

    public static Vector3D clamp(Vector3D vector, double xMin,double xMax){
        return new Vector3D(clamp(vector.getX(),xMin,xMax),clamp(vector.getY(),xMin,xMax),clamp(vector.getZ(),xMin,xMax));
    }

    public static double clamp(double x,double xMin, double xMax){
        if (x<xMin) return xMin;
        if (x>xMax) return xMax;
        return x;
    }

    public static Vector3D vectorRoot(Vector3D vec){
        return new Vector3D(Math.sqrt(vec.getX()),Math.sqrt(vec.getY()),Math.sqrt(vec.getZ()));
    }

    public double randomDouble(double min,double max){
        return min+(max-min)*Math.random();
    }

}
