package com.p6majo.main;
import com.p6majo.math.linalg.Vector3D;
import com.p6majo.objects.HitRecord;
import com.p6majo.objects.ListOfHittables;
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


    private ListOfHittables world;
    /*
     **********************************************
     ****           Constructors         **********
     **********************************************
     */


    public RayTracing(){


        world = new ListOfHittables();
        world.add(new Sphere(0.5,new Vector3D(0,0,-1)));
        world.add(new Sphere(100,new Vector3D(0,-100.5,-1)));


        Function<Ray, Vector3D> rayColorFunction =
                ray -> {
                    HitRecord hit = world.hit(ray,0,Double.POSITIVE_INFINITY);
                    if (hit!=null){
                        return hit.getNormal().add(new Vector3D(1,1,1)).mul(0.5);
                     }
                    //background
                    Vector3D unitDirection = ray.getDirection().normalize();
                    double t = 0.5*(unitDirection.getY()+1.);
                    return new Vector3D(1,1,1).mul(1-t).add(new Vector3D(0.5,0.7,1).mul(t));
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
