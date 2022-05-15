package com.p6majo.material;

import com.p6majo.math.linalg.Vector3D;
import com.p6majo.objects.HitRecord;
import com.p6majo.raytracing.Ray;
import com.p6majo.raytracing.RayWithAttenuation;

/**
 * The class DiElectric
 *
 * @author p6majo
 * @version 2022-05-15
 */
public class DiElectric extends Material{

    /*
     *********************************************
     ***           Attributes           **********
     *********************************************
     */
    private double ir = 1;


    /*
     **********************************************
     ****           Constructors         **********
     **********************************************
     */

    public DiElectric(double indexOfRefraction){
        ir = indexOfRefraction;
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
    public RayWithAttenuation scatter(Ray rayIn, HitRecord rec) {
        Vector3D unitIn = rayIn.getDirection().normalize();

        double refractionRatio = rec.isFrontFace()?1.0/ir:ir;

        double cosTheta = Math.min(unitIn.dot(rec.getNormal().neg()),1.);
        double sinTheta = Math.sqrt(1.-cosTheta*cosTheta);

        boolean reflection = refractionRatio*sinTheta>1.0;

        Vector3D direction = null;
        if (reflection || reflectance(cosTheta,refractionRatio)>Math.random()){
            direction = unitIn.reflect(rec.getNormal());
        }
        else{
            direction = unitIn.refract(rec.getNormal(),refractionRatio);
        }

        return new RayWithAttenuation(rec.getP(),direction,new Vector3D(1,1,1));
    }

    private double reflectance(double cosine, double ref_idx) {
            // Use Schlick's approximation for reflectance.
           double r0 = (1.-ref_idx) / (1+ref_idx);
            r0 = r0*r0;
            return r0 + (1-r0)*Math.pow((1 - cosine),5);
    }

    @Override
    public String toString() {
        return super.toString();
    }


}
