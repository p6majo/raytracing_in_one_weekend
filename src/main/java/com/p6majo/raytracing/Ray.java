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

    private Vector3D origin;
    private Vector3D direction;


    /*
     **********************************************
     ****           Constructors         **********
     **********************************************
     */


    public Ray(Vector3D origin, Vector3D direction) {
        this.origin = origin;
        this.direction = direction;
    }

    /*
     ***********************************************
     ***           Getters              ************
     ***********************************************
     */

    public Vector3D getOrigin(){
        return this.origin;
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
        return origin.add(direction.mul(t));
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
        return this.origin +"+"+this.direction+"*t";
    }


}
