package com.p6majo.objects;

import com.p6majo.math.linalg.Vector3D;
import com.p6majo.raytracing.Ray;

import java.util.function.Function;

/**
 * The class Sphere
 *
 * @author p6majo
 * @version 2022-05-10
 */
public class Sphere implements Hittable{

    /*
     *********************************************
     ***           Attributes           **********
     *********************************************
     */

    private double r;
    private Vector3D center;



    /*
     **********************************************
     ****           Constructors         **********
     **********************************************
     */

    public Sphere(double r, Vector3D center) {
        this.r = r;
        this.center = center;
    }

    /*
     ***********************************************
     ***           Getters              ************
     ***********************************************
     */

    public Vector3D getCenter() {
        return center;
    }

    public double getR() {
        return r;
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

    public HitRecord hit(Ray ray, double tMin, double tMax) {
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
        double root = 0;
        Vector3D oc = ray.getOrigin().sub(this.center);
        double a = ray.getDirection().dot(ray.getDirection());
        double half_b = oc.dot(ray.getDirection());
        double c = oc.dot(oc)-this.r*this.r;
        double discriminant = half_b*half_b-a*c;
        if (discriminant<0)
            return null;
        else {
            //find the neares root that lies in the acceptable range.
            double sq = Math.sqrt(discriminant);
            root = (-half_b-sq)/a;
            if (root<tMin||tMax<root) {
                root=(-half_b+sq)/a;
                if (root<tMin||tMax<root)
                    return null;
            }

        }


        Vector3D point = ray.at(root);
        Vector3D normal = point.sub(center);
        HitRecord rec= new HitRecord(point,normal,root);
        if (rec!=null){
            Vector3D outward_normal = rec.getP().sub(this.center).mul(1./this.r);
            rec.setFaceNormal(ray,outward_normal);
        }

        return rec;
    };

}
