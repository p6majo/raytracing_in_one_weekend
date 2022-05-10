package com.p6majo.math.linalg;



import java.util.Arrays;
import java.util.List;


public class Vector extends Matrix {


      /*
     *************************
     ***** Constructors ******
     *************************
     */


    /**
     * create ZERO vector
     */
    public Vector(int rows){
        super(rows,1,new Double[rows]);
    }

    public Vector(Double... components){
        super(components.length,1,Arrays.asList(components));
    }

    public Vector(List<Double> entries){
        super(entries.size(),1,entries);
    }


    /*
     **********************
     ****** Getter ********
     *********************
     */


    public double getValue(int index){
        return super.getValue(index,0);
    }

    /*
     **********************
     **** Setter **********
     **********************
     */



    /*
     **********************
     *** Operations   *****
     **********************
     */

    @Override
    public Vector sum(Matrix other){
        return new Vector(super.sum(other).entries);
    }

    @Override
    public Vector multiply(double scalar){return new Vector(super.multiply(scalar).entries);}
    /*
     ************************
     **** String output *****
     ************************
     */


    /*
     ********************
     *** Comparison *****
     ********************
     */


    @Override
    public Vector copy(){
        return new Vector(entries);
    }
}
