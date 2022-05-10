package com.p6majo.math.linalg;

import com.p6majo.logger.Logger;
import com.p6majo.logger.Logger.Level;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;


/**
 * @author p6majo
 * @version 1.0
 * @date 2019-08-26
 * @date 2020-05-15 Transformation by Matrix
 */
public class Vector3D {

    /************************/
    /****       statics   ***/
    /************************/

    public static Vector3D getUnitZ(){ return new Vector3D(0,0,1); }
    public static Vector3D getUnitY(){ return new Vector3D(0,1,0); }
    public static Vector3D getUnitX(){ return new Vector3D(1,0,0); }
    public static Vector3D getZERO() {
        return new Vector3D(0, 0, 0);
    }
    public static Vector3D random(double maxX, double maxY, double maxZ){ return new Vector3D(-maxX+2.*Math.random()*maxX,-maxY+2.*Math.random()*maxY,-maxZ+2.*Math.random()*maxZ); }

    /**
     * Parse vector3D from format "(x|y|z)"
     * @param vectorString
     * @return
     */
    public static Vector3D parse(String vectorString){
        vectorString = vectorString.trim();
         vectorString = vectorString.substring(1,vectorString.length()-1); //remove parenthesis

        StringTokenizer tokenizer = new StringTokenizer(vectorString,"|");
        if (tokenizer.countTokens()<3)
            Logger.logging(Level.error,"Not enough components for a vector "+vectorString);

        double x = Double.parseDouble(tokenizer.nextToken());
        double y = Double.parseDouble(tokenizer.nextToken());
        double z = Double.parseDouble(tokenizer.nextToken());

        return new Vector3D(x,y,z);
    }

    /**********************/
    /***   attributes   ***/
    /**********************/
    private double x;
    private double y;
    private double z;

    /*********************/
    /***  constructors ***/
    /*********************/

    public Vector3D(double x, double y, double z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /************************/
    /****       getter    ***/
    /************************/

    public double getX(){ return x; }
    public double getY(){ return y; }
    public double getZ(){ return z; }

    public List<Double> toList(){
        List<Double> components = new ArrayList<>();
        components.add(getX());
        components.add(getY());
        components.add(getZ());
        return components;
    }

    /************************/
    /****       setter    ***/
    /************************/


    /******************************/
    /****     public methods    ***/
    /******************************/

    public double getDistance(Vector3D point){
        return Math.sqrt(directionTo(point).getNorm2());
    }

    /**
     * Calculate the direction vector from this to point
     * @param point
     * @return
     */
    public Vector3D directionTo(Vector3D point){
        return new Vector3D(point.x-x,point.y-y,point.z-z);
    }


    public double getNorm2(){
        return this.x*this.x+this.y*this.y+this.z*this.z;
    }

    public double length(){
        return Math.sqrt(this.getNorm2());
    }

    public Vector3D normalize(){
        return this.mul(1./this.length());
    }

    public static Vector3D midPoint(Vector3D one, Vector3D two){
        return new Vector3D((one.x+two.x)/2,(one.y+two.y)/2,(one.z+two.z)/2);
    }

    public Vector3D add(double x, double y, double z){
        return new Vector3D(this.x+x,this.y+y,this.z+z);
    }

    public Vector3D add(Vector3D shift){ return new Vector3D(this.x+shift.x,this.y+shift.y,this.z+shift.z); }

    public Vector3D sub(Vector3D shift){ return this.add(shift.mul(-1)); }
    public Vector3D neg(){return this.mul(-1.);}
    public Vector3D mul(double scale){ return new Vector3D(this.x*scale,this.y*scale,this.z*scale); }

    public Vector3D cross(Vector3D factor) {return new Vector3D(
            this.getY()*factor.getZ()-this.getZ()*factor.getY(),
            this.getZ()*factor.getX()-this.getX()*factor.getZ(),
            this.getX()*factor.getY()-this.getY()*factor.getX());}

    public double dot(Vector3D factor){
        return this.x*factor.x+this.y*factor.y+this.z*factor.z;
    }

    public double angleTo(Vector3D v){
        return Math.acos(dot(v)/Math.sqrt(v.getNorm2()*this.getNorm2()));
    }

    public Vector3D linMap(Matrix m){
        Matrix vec = new Matrix(this);
        Matrix newVec = m.multiply(vec);
        return new Vector3D(newVec.getValue(0,0),newVec.getValue(1,0),newVec.getValue(2,0));
    }


    /******************************/
    /****     private methods   ***/
    /******************************/


    /******************************/
    /****     overrides         ***/
    /******************************/

    /**
     * Create a hash code that is relatively unique
     * @return
     */
    @Override
    public int hashCode() {
        return (int) ( getX()* 32582657+getY()* 37156667+getZ()*42643801);
    }

    /******************************/
    /****     toString()        ***/
    /******************************/

    @Override
    public String toString() {
        return "("+String.format(Locale.US,"%.6f",x)+"|"+String.format(Locale.US,"%.6f",y)+"|"+String.format(Locale.US,"%.6f",z)+")";
    }


    public String toString(int precision) {
        return "("+String.format("%."+precision+"f",x)+"|"+String.format("%."+precision+"f",y)+"|"+String.format("%."+precision+"f",z)+")";
    }

    public String toLatexString(int precision){
        StringBuilder out = new StringBuilder();
        out.append("\\left(\n")
                .append("\\begin{array}{c}\n")
                .append("\t"+String.format("%."+precision+"f\\\\\n",getX()))
                .append("\t"+String.format("%."+precision+"f\\\\\n",getY()))
                .append("\t"+String.format("%."+precision+"f\n",getZ()))
                .append("\\end{array}\n")
                .append("\\right)\n");
        return out.toString();
    }

    public String toLatexPositionString(int precision){
        StringBuilder out = new StringBuilder();
        out.append("\\left(\n")
                .append(String.format("%."+precision+"f|",getX()))
                .append("\t"+String.format("%."+precision+"f|",getY()))
                .append("\t"+String.format("%."+precision+"f",getZ()))
                .append("\\right)\n");
        return out.toString();
    }

    public String toPovString() {
        return "<"+x+","+y+","+z+">";
    }


}
