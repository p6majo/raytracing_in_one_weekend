package com.p6majo.math.complex;

import java.util.function.Function;


public abstract class ComplexFunction implements Function<Complex,Complex> {
	
	protected Complex coefficient= Complex.ONE;
	protected boolean inverted = false;
	
	public abstract Complex eval(Complex z);
	public abstract ComplexFunction derivative();
	public abstract Complex evalDerivative(Complex z);

	public void setInverted(boolean inverted){
		this.inverted = inverted;
	}
	
	public Complex getCoefficient() {
		return coefficient;
	}
	public void setCoefficient(Complex coefficient) {
		this.coefficient = coefficient;
	}

	@Override
	public Complex apply(Complex z) {
		return eval(z);
	}
}
