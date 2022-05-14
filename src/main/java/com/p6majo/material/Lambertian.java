package com.p6majo.material;

import com.p6majo.math.linalg.Vector3D;
import com.p6majo.objects.HitRecord;
import com.p6majo.raytracing.Ray;
import com.p6majo.raytracing.RayWithAttenuation;

import java.awt.*;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * The class Lambertian
 *
 * @author p6majo
 * @version 2022-05-13
 */
public class Lambertian extends Material{

    /*
     *********************************************
     ***           Attributes           **********
     *********************************************
     */

    protected Vector3D albedo;
    protected Function<Vector3D,Vector3D> albedoFunction;

    /*
     **********************************************
     ****           Constructors         **********
     **********************************************
     */

    public Lambertian(Vector3D color){
        this.albedo = color;
    }
    public Lambertian(Function<Vector3D,Vector3D> albedoFunction){
        this.albedoFunction = albedoFunction;
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
    public RayWithAttenuation scatter(Ray rayIn,HitRecord rec) {
        //create diffusion alternatives
        //Vector3D scatterDirection = rec.getNormal().add(Vector3D.randomInHemisphere(rec.getNormal()));
        Vector3D scatterDirection = rec.getNormal().add(Vector3D.randomUnitVector());

        //Catch degenerate scatter direction
        if (scatterDirection.nearZero())
            scatterDirection=rec.getNormal();

        Vector3D localAlbedo = null;
        if (this.albedoFunction==null)
            localAlbedo = this.albedo;
        else
            localAlbedo = this.albedoFunction.apply(rec.getP());

        RayWithAttenuation scattered = new RayWithAttenuation(rec.getP(),scatterDirection,localAlbedo);
        return scattered;
    }

    @Override
    public String toString() {
        return super.toString();
    }


}
