package com.p6majo.objects;

import com.p6majo.material.Material;
import com.p6majo.math.linalg.Vector3D;
import com.p6majo.raytracing.Ray;

import java.util.function.Function;

/**
 * The class MandelSphere
 *
 * It is a sphere with a profile. The profile is provided by the color function
 *
 * @author p6majo
 * @version 2022-05-18
 */
public class MandelSphere extends Sphere{

    /*
     *********************************************
     ***           Attributes           **********
     *********************************************
     */

    private Function<Vector3D,Double> heightFunction;



    /*
     **********************************************
     ****           Constructors         **********
     **********************************************
     */

    public MandelSphere(double r, Vector3D center, Material material, Function<Vector3D, Double> heightFunction) {
        super(r,center,material);
        this.heightFunction=heightFunction;
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


    @Override
    /**
     * calculates, whether an intersection takes place between the ray x=o+t*v and the sphere (x-c)(x-c)=r*r
     * (o+tv-c)(o+tv-c)-r*r=0
     *
     * oc = o-c
     *
     * oc*oc+2*t*oc*v+t*t+v*v-r*r=0
     * t*t+2*oc*v/(v*v)*t+(oc*oc-r*r)/(v*v)=0
     *
     * b=2*oc*v
     * c=oc*oc-r*r
     *
     * a=v*v
     * discriminant**2=(b*b-c*a)/(v*v)**2
     *
     */
    public HitRecord hit(Ray ray, double tMin, double tMax) {

        Double lambda = super.calculateIntersectionWithSphere(ray, tMin, tMax);
        if (lambda == null)
            return null;
        int count = 0;
        double dl = (lambda - tMin) / 10;
        while (dl>0.01) {
            //now raymarching between tMin and lambda
            dl = (lambda - tMin) / 10;
            double minDiff = 1./0;
            int minDiffIndex = 0;
            int i = 0;
            for (i = 0; i < 11; i++) {
                double l = tMin + dl * i;
                Vector3D pr = ray.at(l);
                double rayHeight = pr.sub(getCenter()).length();
                double sphereHeight = heightFunction.apply(pr.mul(getR() / rayHeight)); //calculate the height at the bottom of the sphere's surface
                double diff =Math.abs(rayHeight-sphereHeight);
                if (diff<minDiff){
                    minDiff=diff;
                    minDiffIndex = i;
                }
                if (rayHeight < sphereHeight){
                    tMin = tMin+dl*(i-2);
                    lambda = tMin+dl*(i+2);
                    break;//for loop
                }
            }
            if (i>10){//no refinement of the interval return ray of shortest diff
                lambda = tMin+dl*minDiffIndex;
                break; //while loop
            }
            count++;
            if (count>20){
                System.out.println(count);
            }
        }

        Vector3D point = ray.at(lambda);
        Vector3D normal = point.sub(super.getCenter());


        HitRecord rec= new HitRecord(point,normal,lambda,getMaterial());
        rec.setRadius(getR()); //set radius of the sphere to calculate accurate colors
        if (rec!=null){
            Vector3D outward_normal = rec.getP().sub(super.getCenter()).mul(1./getR());
            rec.setFaceNormal(ray,outward_normal);
        }
        return rec;
    };

}
