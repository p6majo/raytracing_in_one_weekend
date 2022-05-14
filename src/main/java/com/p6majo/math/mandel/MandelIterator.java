package com.p6majo.math.mandel;

import com.p6majo.math.complex.Complex;
import com.p6majo.math.complex.ComplexFunction;

public class MandelIterator {

	private int iterationDepth=255;
	private double threshold = 100;
	private double scale = 1;
	private Complex rot = Complex.ONE;
	private final ComplexFunction function;

	public MandelIterator(ComplexFunction function,int iterationDepth, double threshold,double scale, Complex rot) {
		this.function = function;
		this.iterationDepth=iterationDepth;
		this.threshold=threshold;
		this.scale = scale;
		this.rot = rot;

	}

	public MandelIterator(ComplexFunction function){
		this.function=function;
	}

	public int iterations(Complex z){
		z=z.mul(scale).mul(rot);
		Complex z1 = Complex.NULL;
		double distance = z1.abs();
		int steps = 0;

		while (distance < this.threshold && steps < this.iterationDepth) {
			z1 = function.eval(z1).plus(z);
			distance = z1.abs();
			steps++;
		}

		return steps;
	}

}
