package com.p6majo.main;
import com.p6majo.material.DiElectric;
import com.p6majo.material.Lambertian;
import com.p6majo.material.Material;
import com.p6majo.material.Metal;
import com.p6majo.math.complex.Complex;
import com.p6majo.math.complex.ComplexFunction;
import com.p6majo.math.linalg.Vector3D;
import com.p6majo.math.mandel.MandelIterator;
import com.p6majo.objects.HitRecord;
import com.p6majo.objects.ListOfHittables;
import com.p6majo.objects.Sphere;
import com.p6majo.raytracing.Camera;
import com.p6majo.raytracing.Ray;
import com.p6majo.raytracing.RayWithAttenuation;
import com.p6majo.raytracing.Renderer;

import java.util.function.BiFunction;
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
    private static ComplexFunction zSquare = new ComplexFunction() {
        @Override
        public Complex eval(Complex z) {
            return z.mul(z);
        }

        @Override
        public ComplexFunction derivative() {
           return null;
        }

        @Override
        public Complex evalDerivative(Complex z) {
            return null;
        }
    };

    private static MandelIterator iterator = new MandelIterator(zSquare,255,100,3,new Complex(Math.cos(Math.PI/2),Math.sin(Math.PI/2)));

    /*
     **********************************************
     ****           Constructors         **********
     **********************************************
     */


    public RayTracing(){


        world = new ListOfHittables();

        Function<Vector3D,Vector3D> mandelFunction= o -> {
            //Stereographic projection
            double u = o.getX()/(1.-o.getZ());
            double v = o.getY()/(1.-o.getZ());
            int steps = iterator.iterations(new Complex(u,v));
            //System.out.println(u + "+i" + v + " -> " + steps);
            if (steps==255)
                return Vector3D.getZERO();
            else if (steps>255*0.9)
                return new Vector3D(1,1,1);
            else{
                return new Vector3D((double) steps/(255*0.9),0,0);
            }

        };
        Material ground =new Lambertian(new Vector3D(0.8,0.8,0.0));
        Material center = new Lambertian(mandelFunction);
        Material left = new DiElectric(1.5);
        Material right = new Metal(new Vector3D(0.8,0.6,0.2));

        world.add(new Sphere(1,new Vector3D(0,0.5,-2),center));
        world.add(new Sphere(0.5,new Vector3D(-1,0,-1),left));
        world.add(new Sphere(-0.4,new Vector3D(-1,0,-1),left));
        world.add(new Sphere(0.5,new Vector3D(1,0,-1),right));
        world.add(new Sphere(100,new Vector3D(0,-100.5,-1),ground));

        //capture function inside an array to avoid problem of
        //possible uninitialized function in the case of recursive call

        BiFunction<Ray,Integer, Vector3D>[] rayColorFunctions = new BiFunction[]{null};
        rayColorFunctions[0] =
                (ray,depth) -> {

                    if (depth<=0) //end recursion if caught in the trap between materials
                        return Vector3D.getZERO();

                    HitRecord hit = world.hit(ray,0.001,Double.POSITIVE_INFINITY);
                    if (hit!=null){

                        Material mat = hit.getMaterial();
                        if (mat!=null){

                            RayWithAttenuation scattered = mat.scatter(ray,hit);
                            return scattered.getAttenuation().hadamardProduct(rayColorFunctions[0].apply(scattered,depth-1));


                        }
                        return Vector3D.getZERO();

                    }
                    //background
                    Vector3D unitDirection = ray.getDirection().normalize();
                    double t = 0.5*(unitDirection.getY()+1.);
                    return new Vector3D(1,1,1).mul(1-t).add(new Vector3D(0.5,0.7,1).mul(t));
                };

        Renderer renderer = new Renderer(rayColorFunctions[0],new Camera(120), Renderer.Quality.MEDIUM);
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
