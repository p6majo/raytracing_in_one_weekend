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


        Function<Ray, Color> rayColorFunction =
                ray -> {
                    HitRecord hit = world.hit(ray,0,Double.POSITIVE_INFINITY);
                    if (hit!=null){
                        return new Color((int)( 255*(hit.getNormal().getX()+1)/2),(int) (255*(hit.getNormal().getY()+1)/2),(int) (255*(hit.getNormal().getZ()+1)/2));
                    }
                    //background
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
