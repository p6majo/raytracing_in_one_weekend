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
    private double theta = Math.PI/2; // vertical field of view
    private Vector3D lookFrom;
    private Vector3D lookAt;
    private Vector3D vup;

    private Vector3D origin = Vector3D.getZERO();
    private Vector3D horizontal = new Vector3D(viewportWidth,0,0);
    private Vector3D vertical = new Vector3D(0,viewportHeight,0);
    private Vector3D lowerLeftCorner = origin.sub(horizontal.mul(0.5)).sub(vertical.mul(0.5)).sub(new Vector3D(0,0,focalLength));


    /*
     **********************************************
     ****           Constructors         **********
     **********************************************
     */

    public Camera(double vertFov, double aspectRatio,double viewportHeight,double focalLength){
        this.theta = vertFov/180*Math.PI;
        double h = Math.tan(theta/2);
        this.aspectRatio=aspectRatio;
        this.viewportHeight=2.*h;
        this.focalLength = focalLength;
        initialize();
    }

    public Camera(double vertFov,Vector3D lookFrom, Vector3D lookAt, Vector3D vup, double aspectRatio){
        this.theta = vertFov/180*Math.PI;
        double h = Math.tan(theta/2);
        this.viewportHeight=2.*h;
        this.lookFrom = lookFrom;
        this.lookAt = lookAt;
        this.vup = vup;
        initialize();

    }

    private void initialize(){
        this.viewportWidth=aspectRatio*viewportHeight;
        horizontal = new Vector3D(viewportWidth,0,0);
        vertical = new Vector3D(0,viewportHeight,0);
        lowerLeftCorner = origin.sub(horizontal.mul(0.5)).sub(vertical.mul(0.5)).sub(new Vector3D(0,0,focalLength));
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

    public Ray getRay(double u, double v){
        return new Ray(origin,lowerLeftCorner.add(horizontal.mul(u)).add(vertical.mul(v)).sub(origin));
    }

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
