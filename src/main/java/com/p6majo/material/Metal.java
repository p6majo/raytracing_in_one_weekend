package com.p6majo.material;

import com.p6majo.math.linalg.Vector3D;
import com.p6majo.objects.HitRecord;
import com.p6majo.raytracing.Ray;
import com.p6majo.raytracing.RayWithAttenuation;
import java.util.function.Function;

/**
 * The class Metal
 *
 * @author p6majo
 * @version 2022-05-13
 */
public class Metal extends Lambertian{


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
    public Metal(Vector3D color) {
        super(color);
    }
    public Metal(Function<Vector3D,Vector3D> albedoFunction){super(albedoFunction);}

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
        Vector3D reflected = rayIn.getDirection().reflect(rec.getNormal());

        Vector3D localAlbedo = null;
        if (this.albedoFunction==null)
            localAlbedo = this.albedo;
        else
            localAlbedo = this.albedoFunction.apply(rec.getP());

        RayWithAttenuation scattered = new RayWithAttenuation(rec.getP(),reflected,localAlbedo);
        if (scattered.getDirection().dot(rec.getNormal())>0)
            return scattered;
        else
            return null;
    }

    @Override
    public String toString() {
        return super.toString();
    }


}
