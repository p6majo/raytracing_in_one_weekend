package com.p6majo.image;

import com.p6majo.logger.Logger;
import com.p6majo.math.linalg.Vector3D;
import com.p6majo.raytracing.Camera;
import com.p6majo.raytracing.Ray;
import com.p6majo.raytracing.Renderer;

import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.function.Function;

/**
 * The class PPMImage
 *
 * @author p6maj
 * @version 2022-05-10
 */
public class PPMImage {


    /*
     *********************************************
     ***           Attributes           **********
     *********************************************
     */

    private int height;
    private int width;

    /*
     **********************************************
     ****           Constructors         **********
     **********************************************
     */


    public PPMImage(int width,int height){
        this.height = height;
        this.width = width;
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


    public void render(Renderer renderer, String path){
        try (FileWriter fw = new FileWriter(path)) {
            fw.write("P3\n");
            fw.write(width+" "+height+"\n");
            fw.write("255\n");

            Camera cam = renderer.getCamera();

            Vector3D origin = cam.getOrigin();
            Vector3D lowerLeftCorner = cam.getLowerLeftCorner();
            Vector3D horizontal = cam.getHorizontal();
            Vector3D vertical = cam.getVertical();

            Function<Ray,Color> rayColorFunction = renderer.getRayColorFunction();

            for (int h = 0; h < height; h++) {
                for (int w = 0; w < width; w++) {
                    double u = (double) w/ (width-1);
                    double v = (double) h/ (height-1);
                    Ray ray = new Ray(origin, lowerLeftCorner.add(horizontal.mul(u)).add(vertical.mul(v)).sub(origin));
                    Color pixColor = rayColorFunction.apply(ray);
                    int red = pixColor.getRed();
                    int green = pixColor.getGreen();
                    int blue = pixColor.getBlue();
                    fw.write(red+" "+green+" "+blue+" ");
                }
                fw.write("\n");
            }
            System.err.println("\nDone.");

        } catch (IOException e) {
            e.printStackTrace();
            Logger.logging(Logger.Level.error,"File not found exception caused by path "+path);
        }
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



    /*
     ***********************************************
     ***           toString             ************
     ***********************************************
     */

    @Override
    public String toString() {
        return super.toString();
    }


}
