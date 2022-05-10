package com.p6majo.raytracing;

import com.p6majo.image.PPMImage;

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
    private String name = java.time.LocalTime.now()+"_DefaultImage.ppm";
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

    public void render(){
        image.render(this,name);
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
