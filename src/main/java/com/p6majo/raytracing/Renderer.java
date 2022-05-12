package com.p6majo.raytracing;

import com.p6majo.image.PPMImage;
import com.p6majo.math.linalg.Vector3D;

import java.awt.*;
import java.util.function.Function;

/**
 * The class Renderer
 *
 * @author p6maj
 * @version 2022-05-10
 */
public class Renderer {


    /*
     *********************************************
     ***           Attributes           **********
     *********************************************
     */

    private Camera camera;
    private PPMImage image;
    private String name = (java.time.LocalTime.now()+"_DefaultImage.ppm").replace(':','-');
    public enum Quality {LOW,MEDIUM,HIGH};

    private Function<Ray,Color> rayColorFunction;


    /*
     **********************************************
     ****           Constructors         **********
     **********************************************
     */

    public Renderer(Function<Ray, Color> rayColorFunction){
        this(rayColorFunction,Camera.getDefaultCamera(),Quality.LOW);
    }

    public Renderer(Function<Ray, Color> rayColorFunction,Camera camera){
        this(rayColorFunction,camera,Quality.LOW);
    }

    public Renderer(Function<Ray, Color> rayColorFunction, Camera camera, Quality quality){
        this.camera=camera;
        this.rayColorFunction = rayColorFunction;
        double width=1920;
        double aspectRatio = this.camera.getAspectRatio();
        double height = width/aspectRatio;
        switch(quality){
            case LOW -> {
                width*=0.25;
                height*=0.25;
                this.image=new PPMImage((int) width,(int) height);
                break;
            }
            case MEDIUM -> {
                width*=0.5;
                height*=0.5;
                this.image=new PPMImage((int) width,(int) height);
                break;
            }
            case HIGH -> {
                this.image=new PPMImage((int) width,(int) height);
                break;
            }
        }
    }


    /*
     ***********************************************
     ***           Getters              ************
     ***********************************************
     */

    public Camera getCamera(){
        return this.camera;
    }

    public Function<Ray,Color> getRayColorFunction(){
        return this.rayColorFunction;
    }
    /*
     ***********************************************
     ***           Setters              ************
     ***********************************************
     */

    public void setName(String name){
        this.name=name;
    }
    /*
     ***********************************************
     ***           Public methods       ************
     ***********************************************
     */

    /**
     * Scan the entire view port of the camera and write the color of each pixel to
     * a ppm image
     * @param renderer
     * @param path
     */
    public void render(){

        int width = this.image.getWidth();
        int height = this.image.getHeight();
        Color[] colors = new Color[width*height];


        Vector3D origin = this.camera.getOrigin();
        Vector3D lowerLeftCorner = this.camera.getLowerLeftCorner();
        Vector3D horizontal = this.camera.getHorizontal();
        Vector3D vertical = this.camera.getVertical();

        for (int h =0; h <height; h++) {
            for (int w = 0; w < width; w++) {
                double u = (double) w / (width - 1);
                double v = (double) h / (height - 1);
                Ray ray = camera.getRay(u,v);
                Color pixColor = rayColorFunction.apply(ray);
                colors[w + (height-1-h) * width] = pixColor;
            }
        }
        this.image.setColors(colors);
        this.image.save(name);
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
