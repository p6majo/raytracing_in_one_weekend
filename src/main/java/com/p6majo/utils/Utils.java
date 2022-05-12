package com.p6majo.utils;

/**
 * The class Utils
 *
 * @author p6maj
 * @version 2022-05-12
 */
public class Utils {

    public static double clamp(double x,double xMin, double xMax){
        if (x<xMin) return xMin;
        if (x>xMax) return xMax;
        return x;
    }

}
