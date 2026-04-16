package com.apps.measurement.model;

public enum VolumeUnit {
	LITER(1.0),
	MILLILITER(0.001),
	GALLON(3.78541),
	PINT(0.473176);

	private final double toLiter;

	VolumeUnit(double toLiter) {
		this.toLiter = toLiter;
	}

	public double getToLiter() {
		return toLiter;
	}

	public double convert(double value, VolumeUnit to) {
		return (value * this.toLiter) / to.toLiter;
	}
}
