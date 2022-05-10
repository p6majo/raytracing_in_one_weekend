package com.p6majo.image;

import com.p6majo.logger.Logger;

import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

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


    public PPMImage(int width,int height, Color[] colors){

        this.height = height;
        this.width = width;
        this.colors = colors;
    }

    public PPMImage(int width, int height){
        this(width,height,null);
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


    public void render(String path){
        try (FileWriter fw = new FileWriter(path)) {
            fw.write("P3\n");
            fw.write(width+" "+height+"\n");
            fw.write("255\n");

            if (this.colors!=null) {
                for (int h = 0; h < height; h++) {
                    for (int w = 0; w < width; w++) {
                        Color color = this.colors[w + h * width];
                        int red = color.getRed();
                        int green = color.getGreen();
                        int blue = color.getBlue();
                        fw.write(red+" "+green+" "+blue+" ");
                    }
                    fw.write("\n");
                }
              System.err.println("\nDone.");
            }
            else {
                Logger.logging(Logger.Level.warning,"No colors were rendered, since the image is empty.");
            }
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
