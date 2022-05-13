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

    private int samplesPerPixel =100;

    private Function<Ray,Vector3D> rayColorFunction;


    /*
     **********************************************
     ****           Constructors         **********
     **********************************************
     */

    public Renderer(Function<Ray, Vector3D> rayColorFunction){
        this(rayColorFunction,Camera.getDefaultCamera(),Quality.LOW);
    }

    public Renderer(Function<Ray, Vector3D> rayColorFunction,Camera camera){
        this(rayColorFunction,camera,Quality.LOW);
    }

    public Renderer(Function<Ray, Vector3D> rayColorFunction, Camera camera, Quality quality){
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

    public Function<Ray,Vector3D> getRayColorFunction(){
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
     */
    public void render(){

        int width = this.image.getWidth();
        int height = this.image.getHeight();
        Color[] colors = new Color[width*height];


        for (int h =0; h <height; h++) {
            for (int w = 0; w < width; w++) {
                Vector3D color = Vector3D.getZERO();
                for (int s = 0; s < samplesPerPixel; s++) {
                    double u = ((double) w + Math.random()) / (width - 1);
                    double v = ((double) h + Math.random()) / (height - 1);

                    Ray ray = camera.getRay(u, v);
                    color = color.add(rayColorFunction.apply(ray));
                }
                color=color.mul(255./samplesPerPixel);
                colors[w + (height-1-h) * width] =new Color((int) color.getX(),(int) color.getY(), (int) color.getZ());
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
