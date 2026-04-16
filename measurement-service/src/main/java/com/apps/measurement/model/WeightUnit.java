package com.apps.measurement.model;

public enum WeightUnit {
	KG(1.0),
	GRAM(0.001),
	POUND(0.453592),
	OUNCE(0.0283495);

	private final double toKg;

	WeightUnit(double toKg) {
		this.toKg = toKg;
	}

	public double getToKg() {
		return toKg;
	}

	public double convert(double value, WeightUnit to) {
		return (value * this.toKg) / to.toKg;
	}
}
