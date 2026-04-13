package com.app.quantitymeasurement.core;

public enum WeightUnit implements IMeasurable {

    MILLIGRAM(0.001),
    GRAM(1.0),
    KILOGRAM(1000.0),
    POUND(453.592),
    TONNE(1_000_000.0);

    private final double conversionFactor; // conversion to base (grams)

    WeightUnit(double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    @Override
    public double getConversionFactor() {
        return conversionFactor;
    }

    @Override
    public double convertToBaseUnit(double value) {
        // Don't round here - let final rounding happen at the end
        return value * conversionFactor; // to grams
    }

    @Override
    public double convertFromBaseUnit(double baseValue) {
        // Don't round here - let final rounding happen at the end
        return baseValue / conversionFactor; // from grams
    }

    @Override
    public String getUnitName() {
        return this.name(); // unit name
    }
}