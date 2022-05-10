package com.p6majo.raytracing;

import com.p6majo.math.linalg.Vector3D;

/**
 * The class Ray
 *
 * @author p6maj
 * @version 2022-05-10
 */
public class Ray {


    /*
     *********************************************
     ***           Attributes           **********
     *********************************************
     */

    private Vector3D point;
    private Vector3D direction;


    /*
     **********************************************
     ****           Constructors         **********
     **********************************************
     */


    public Ray(Vector3D point, Vector3D direction) {
        this.point = point;
        this.direction = direction;
    }

    /*
     ***********************************************
     ***           Getters              ************
     ***********************************************
     */

    public Vector3D getPoint(){
        return this.point;
    }

    public Vector3D getDirection(){
        return this.direction;
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

    public Vector3D at(double t){
        return point.add(direction.mul(t));
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
