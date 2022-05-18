package com.p6majo.objects;

import com.p6majo.material.Material;
import com.p6majo.math.linalg.Vector3D;
import com.p6majo.raytracing.Ray;

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
    private boolean frontFace;
    private Material material;
    private double radius;

    /*
     **********************************************
     ****           Constructors         **********
     **********************************************
     */

    public HitRecord(Vector3D p, Vector3D normal, double t,Material material) {
        this.p = p;
        this.normal = normal;
        this.t = t;
        this.material = material;
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

    public Material getMaterial(){return this.material;}

    public boolean isFrontFace(){
        return this.frontFace;
    }

    public double getRadius(){
        return this.radius;
    }

    /*
     ***********************************************
     ***           Setters              ************
     ***********************************************
     */

    public void setRadius(double r){
        this.radius=r;
    }

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

    public void setFaceNormal(Ray ray, Vector3D outwardNormal){
        frontFace = ray.getDirection().dot(outwardNormal)<0;
        normal=frontFace? outwardNormal:outwardNormal.neg();

    }

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
