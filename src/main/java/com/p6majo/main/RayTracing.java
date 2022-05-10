package com.p6majo.main;

import com.p6majo.math.linalg.Vector3D;

/**
 * The class RayTracing
 *
 * @author p6maj
 * @version 2022-05-10
 */
public class RayTracing {


    /*
     *********************************************
     ***           Attributes           **********
     *********************************************
     */



    /*
     **********************************************
     ****           Constructors         **********
     **********************************************
     */




    /*
     ***********************************************
     ***           Getters              ************
     ***********************************************
     */

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

    public static void main(String[] args) {

        //image
        double aspectRatio =16./9;
        int width = 400;
        int height = (int) (width/aspectRatio);

        //camera
        double viewportHeight=2.;
        double viewportWidth=2./aspectRatio;
        double focalLength=1.;

        Vector3D origin = Vector3D.getZERO();
        Vector3D horizontal = new Vector3D(viewportWidth,0,0);
        Vector3D vertical = new Vector3D(0,viewportHeight,0);
        Vector3D lowerLeftCorner = origin.sub(horizontal.mul(0.5)).sub(vertical.mul(0.5)).sub(new Vector3D(0,0,focalLength));

        //Render
        

    }

}
