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
    private Vector3D lookFrom=new Vector3D(-1,1,0.5);
    private Vector3D lookAt=new Vector3D(0,0,-1);
    private Vector3D vup=new Vector3D(0,1,0);

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
        Vector3D w =lookFrom.sub(lookAt).normalize();
        Vector3D u = vup.cross(w).normalize();
        Vector3D v = w.cross(u);

        this.viewportWidth=aspectRatio*viewportHeight;
        origin = lookFrom;
        horizontal = u.mul(viewportWidth);
        vertical = v.mul(viewportHeight);
        lowerLeftCorner = origin.sub(horizontal.mul(0.5)).sub(vertical.mul(0.5)).sub(w.mul(focalLength));
    }

    /**
     * Constructor for a default camera
     */
    public Camera(){
        initialize();
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
