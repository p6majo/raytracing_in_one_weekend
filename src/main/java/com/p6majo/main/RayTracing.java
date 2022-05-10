package com.p6majo.main;
import com.p6majo.math.linalg.Vector3D;
import com.p6majo.raytracing.Camera;
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
        Function<Ray, Color> rayColorFunction =
                ray -> {
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
