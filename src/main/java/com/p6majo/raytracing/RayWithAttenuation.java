package com.p6majo.raytracing;

import com.p6majo.math.linalg.Vector3D;

import java.awt.*;

/**
 * The class RayWithAlbedo
 *
 * @author p6majo
 * @version 2022-05-13
 */
public class RayWithAttenuation extends Ray{

    /*
     *********************************************
     ***           Attributes           **********
     *********************************************
     */

    private Vector3D attenuation;


    /*
     **********************************************
     ****           Constructors         **********
     **********************************************
     */

    public RayWithAttenuation(Vector3D origin, Vector3D direction, Vector3D attenuation) {
        super(origin, direction);
        this.attenuation = attenuation;
    }
    /*
     ***********************************************
     ***           Getters              ************
     ***********************************************
     */

    public Vector3D getAttenuation(){
        return this.attenuation;
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
