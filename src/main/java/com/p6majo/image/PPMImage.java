package com.p6majo.image;

import com.p6majo.logger.Logger;
import com.p6majo.math.linalg.Vector3D;
import com.p6majo.raytracing.Camera;
import com.p6majo.raytracing.Ray;
import com.p6majo.raytracing.Renderer;

import java.awt.*;
import java.io.File;
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
    private Color[] colors;

    /*
     **********************************************
     ****           Constructors         **********
     **********************************************
     */


    public PPMImage(int width,int height){
        this.height = height;
        this.width = width;
    }

    public PPMImage(int width,int height, boolean test){
        this(width,height);
        this.colors = new Color[width*height];
        if (test){
            for (int h = 0; h < height; h++) {
                for (int w = 0; w < width; w++) {
                    this.colors[w+h*width]=new Color((int) (255*h/(height-1)),(int) (255*w/(width-1)),(int) (255*0.25));
                }
            }
        }

    }


    /*
     ***********************************************
     ***           Getters              ************
     ***********************************************
     */

    public int getWidth(){
        return this.width;
    }

    public int getHeight(){
        return this.height;
    }

    /*
     ***********************************************
     ***           Setters              ************
     ***********************************************
     */

    public void setColors(Color... colors){
        if (this.colors==null){
            this.colors = new Color[this.width*this.height];
        }
        if (colors.length<height*width){
            Logger.logging(Logger.Level.warning,"not enough colors for image dimension, some pixels are set to black");
            System.arraycopy(colors,0,this.colors,0,colors.length);
            for (int i = colors.length; i < this.colors.length; i++)
                this.colors[i]=Color.BLACK;
        }
        else if (colors.length>height*width){
            Logger.logging(Logger.Level.warning,"too colors for image dimension, some colors are omitted");
            System.arraycopy(colors,0,this.colors,0,this.colors.length);
        }
        else{
            this.colors = colors;
        }
    }



    /*
     ***********************************************
     ***           Public methods       ************
     ***********************************************
     */

    public void save(String path) {
        try (FileWriter fw = new FileWriter(path)) {
            fw.write("P3\n");
            fw.write(width + " " + height + "\n");
            fw.write("255\n");

            for (int h = 0; h < height; h++) {
                for (int w = 0; w < width; w++) {
                    double u = (double) w / (width - 1);
                    double v = (double) h / (height - 1);

                    Color pixColor = colors[w + h * width];
                    int red = pixColor.getRed();
                    int green = pixColor.getGreen();
                    int blue = pixColor.getBlue();

                    fw.write(red + " " + green + " " + blue + " ");
                }
                fw.write("\n");
            }
            System.err.println("\nDone.");

        } catch (IOException e) {
            e.printStackTrace();
            Logger.logging(Logger.Level.error, "File not found exception caused by path " + path);
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
        return "PPM Image of dimension: "+width+"x"+height;
    }


}
