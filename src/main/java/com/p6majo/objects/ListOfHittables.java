package com.p6majo.objects;

import com.p6majo.raytracing.Ray;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The class ListOfHittables
 *
 * @author p6maj
 * @version 2022-05-12
 */
public class ListOfHittables implements Hittable{


    /*
     *********************************************
     ***           Attributes           **********
     *********************************************
     */

    List<Hittable> objectList;
    /*
     **********************************************
     ****           Constructors         **********
     **********************************************
     */



    public ListOfHittables(){
        objectList = new ArrayList<>();
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

    public void clear(){
        this.objectList.clear();
    }

    public void add(Hittable object){
        this.objectList.add(object);
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


    @Override
    public HitRecord hit(Ray ray, double tMin, double tMax) {
        HitRecord tmpRecord=null;
        double closest_so_far = Double.POSITIVE_INFINITY;

        for (Hittable hittable : objectList) {
            HitRecord rec = hittable.hit(ray,tMin,closest_so_far);
            if (rec!=null){
                closest_so_far=rec.getT();
                tmpRecord = rec;
            }
        }

        return tmpRecord;
    }

    /*
     ***********************************************
     ***           toString             ************
     ***********************************************
     */

    @Override
    public String toString() {
       return "["+objectList.stream().map(x->x.toString()).collect(Collectors.joining(","))+"]";
    }



}
