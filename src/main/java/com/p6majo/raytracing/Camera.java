package com.p6majo.raytracing;

import com.p6majo.math.linalg.Vector3D;

/**
 * The class Camera
 *
 * @author p6maj
 * @version 2022-05-10
 */
public class Camera {


    /*
     *********************************************
     ***           Attributes           **********
     *********************************************
     */

    private double aspectRatio = 16./9;
    private double viewportHeight=2.0;
    private double viewportWidth=aspectRatio*viewportHeight;
    private double focalLength = 1.;

    Vector3D origin = Vector3D.getZERO();
    Vector3D horizontal = new Vector3D(viewportWidth,0,0);
    Vector3D vertical = new Vector3D(0,viewportHeight,0);
    Vector3D lowerLeftCorner = origin.sub(horizontal.mul(0.5)).sub(vertical.mul(0.5)).sub(new Vector3D(0,0,focalLength));


    /*
     **********************************************
     ****           Constructors         **********
     **********************************************
     */

    public Camera(double aspectRatio,double viewportHeight,double focalLength){
        this.aspectRatio=aspectRatio;
        this.viewportHeight=viewportHeight;
        this.viewportWidth=aspectRatio*viewportHeight;
        this.focalLength = focalLength;
    }


    /**
     * Constructor for a default camera
     */
    public Camera(){

    }


    /*
     ***********************************************
     ***           Getters              ************
     ***********************************************
     */

    public double getAspectRatio(){
        return this.aspectRatio;
    }

    public Vector3D getOrigin(){
        return this.origin;
    }

    public Vector3D getLowerLeftCorner(){
        return this.lowerLeftCorner;
    }

    public Vector3D getHorizontal(){
        return this.horizontal;
    }

    public Vector3D getVertical(){
        return this.vertical;
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

    public static Camera getDefaultCamera(){
        return new Camera();
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
