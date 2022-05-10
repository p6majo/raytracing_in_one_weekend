package com.p6majo.objects;

import com.p6majo.math.linalg.Vector3D;

/**
 * The class Sphere
 *
 * @author p6majo
 * @version 2022-05-10
 */
public class Sphere {

    /*
     *********************************************
     ***           Attributes           **********
     *********************************************
     */

    private double r;
    private Vector3D center;



    /*
     **********************************************
     ****           Constructors         **********
     **********************************************
     */

    public Sphere(double r, Vector3D center) {
        this.r = r;
        this.center = center;
    }

    /*
     ***********************************************
     ***           Getters              ************
     ***********************************************
     */

    public Vector3D getCenter() {
        return center;
    }

    public double getR() {
        return r;
    }

    /*
     ***********************************************
     ***           Setters              ************
     ***********************************************
     */



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