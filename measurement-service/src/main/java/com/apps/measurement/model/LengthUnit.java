package com.apps.measurement.model;

public enum LengthUnit {
	KM(1000.0),
	METER(1.0),
	CM(0.01),
	MM(0.001),
	MILE(1609.34),
	YARD(0.9144),
	FEET(0.3048),
	INCH(0.0254);

	private final double toMeter;

	LengthUnit(double toMeter) {
		this.toMeter = toMeter;
	}

	public double getToMeter() {
		return toMeter;
	}

	public double convert(double value, LengthUnit to) {
		return (value * this.toMeter) / to.toMeter;
	}
}
