package com.p6majo.objects;

import com.p6majo.math.linalg.Vector3D;

/**
 * The class HitRecord
 *
 * @author p6maj
 * @version 2022-05-11
 */
public class HitRecord {


    /*
     *********************************************
     ***           Attributes           **********
     *********************************************
     */

    private Vector3D p;
    private Vector3D normal;
    private double t;

    /*
     **********************************************
     ****           Constructors         **********
     **********************************************
     */

    public HitRecord(Vector3D p, Vector3D normal, double t) {
        this.p = p;
        this.normal = normal;
        this.t = t;
    }


    /*
     ***********************************************
     ***           Getters              ************
     ***********************************************
     */

    public Vector3D getP() {
        return p;
    }

    public Vector3D getNormal() {
        return normal;
    }

    public double getT() {
        return t;
    }

    /*
     ***********************************************
     ***           Setters              ************
     ***********************************************
     */

//    public void setP(Vector3D p) {
//        this.p = p;
//    }
//
//    public void setNormal(Vector3D normal) {
//        this.normal = normal;
//    }
//
//    public void setT(double t) {
//        this.t = t;
//    }

    /*
     ***********************************************
     ***           Public methods       ************
     ***********************************************
     */


    /*
     ***********************************************
     ***           Private methods      ************
     ***********************************************
     */


    /*
     ***********************************************
     ***           Overrides            ************
     ***********************************************
     */



    /*
     ***********************************************
     ***           toString             ************
     ***********************************************
     */

    @Override
    public String toString() {
        return super.toString();
    }



}
