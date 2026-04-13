package com.app.quantitymeasurement.core;

public enum LengthUnit implements IMeasurable {

    FEET(12.0),
    INCHES(1.0),
    YARDS(36.0),
    CENTIMETERS(0.393701),
	METERS(39.3701);
	
    private final double conversionFactor;

    LengthUnit(double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    @Override
    public double getConversionFactor() {
        return conversionFactor;
    }

    @Override
    public double convertToBaseUnit(double value) {
        // Don't round here - let final rounding happen at the end
        return value * conversionFactor;
    }

    @Override
    public double convertFromBaseUnit(double baseValue) {
        // Don't round here - let final rounding happen at the end
        return baseValue / conversionFactor;
    }

    @Override
    public String getUnitName() {
        return this.name();
    }
}