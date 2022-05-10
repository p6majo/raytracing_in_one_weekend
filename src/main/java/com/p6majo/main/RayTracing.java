package com.p6majo.main;
import com.p6majo.math.linalg.Vector3D;
import com.p6majo.objects.Sphere;
import com.p6majo.raytracing.Ray;
import com.p6majo.raytracing.Renderer;

import java.awt.Color;
import java.util.function.Function;

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


    public RayTracing(){

        Sphere sphere = new Sphere(0.5,new Vector3D(0,0,-1));

        /**
         * calculates, whether an intersection takes place between the ray x=o+t*v and the sphere (x-c)(x-c)=r*r
         */
        Function<Ray,Boolean> hitSphere = ray-> {
            Vector3D oc = ray.getOrigin().sub(sphere.getCenter());
            double a = ray.getDirection().dot(ray.getDirection());
            double b = 2.*oc.dot(ray.getDirection());
            double c = oc.dot(oc)-sphere.getR()*sphere.getR();
            double discriminant = b*b-4*a*c;
            return discriminant>0;
        };

        Function<Ray, Color> rayColorFunction =
                ray -> {
                    if (hitSphere.apply(ray))
                        return new Color(255,0,0);
                    Vector3D unitDirection = ray.getDirection().normalize();
                    double t = 0.5*(unitDirection.getY()+1.);
                    return new Color((int) ((1.-t)*255+t*178),(int)((1-t)*255+t*0.7*255),255);
                };

        Renderer renderer = new Renderer(rayColorFunction);
        renderer.render();
    }


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
        new RayTracing();
    }

}
