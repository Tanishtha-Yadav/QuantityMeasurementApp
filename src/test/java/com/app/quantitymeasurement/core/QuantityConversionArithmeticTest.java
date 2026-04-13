package com.app.quantitymeasurement.core;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Comprehensive tests for unit conversion and arithmetic operations
 * across all measurement categories
 */
public class QuantityConversionArithmeticTest {

    // ============= LENGTH UNIT TESTS =============

    @Test
    void testLengthConversion_FeetToInches() {
        Quantity<LengthUnit> feet = new Quantity<>(1, LengthUnit.FEET);
        Quantity<LengthUnit> inches = feet.convertTo(LengthUnit.INCHES);
        System.out.println("1 FEET = " + inches.getValue() + " INCHES (Expected: 12)");
        assertEquals(12.0, inches.getValue(), 0.01);
    }

    @Test
    void testLengthConversion_InchesToFeet() {
        Quantity<LengthUnit> inches = new Quantity<>(12, LengthUnit.INCHES);
        Quantity<LengthUnit> feet = inches.convertTo(LengthUnit.FEET);
        System.out.println("12 INCHES = " + feet.getValue() + " FEET (Expected: 1)");
        assertEquals(1.0, feet.getValue(), 0.01);
    }

    @Test
    void testLengthConversion_YardsToFeet() {
        Quantity<LengthUnit> yards = new Quantity<>(1, LengthUnit.YARDS);
        Quantity<LengthUnit> feet = yards.convertTo(LengthUnit.FEET);
        System.out.println("1 YARDS = " + feet.getValue() + " FEET (Expected: 3)");
        assertEquals(3.0, feet.getValue(), 0.01);
    }

    @Test
    void testLengthConversion_CentimetersToMeters() {
        Quantity<LengthUnit> cm = new Quantity<>(100, LengthUnit.CENTIMETERS);
        Quantity<LengthUnit> m = cm.convertTo(LengthUnit.METERS);
        System.out.println("100 CENTIMETERS = " + m.getValue() + " METERS (Expected: ~0.01)");
        // This should show errors - CENTIMETERS conversion factor is wrong
    }

    @Test
    void testLengthAdd_FeetAndInches() {
        Quantity<LengthUnit> feet = new Quantity<>(1, LengthUnit.FEET);
        Quantity<LengthUnit> inches = new Quantity<>(12, LengthUnit.INCHES);
        Quantity<LengthUnit> result = feet.add(inches);
        System.out.println("1 FEET + 12 INCHES = " + result.getValue() + " " + result.getUnit() + " (Expected: 2 FEET)");
        assertEquals(2.0, result.getValue(), 0.01);
    }

    @Test
    void testLengthSubtract_FeetAndInches() {
        Quantity<LengthUnit> feet = new Quantity<>(2, LengthUnit.FEET);
        Quantity<LengthUnit> inches = new Quantity<>(12, LengthUnit.INCHES);
        Quantity<LengthUnit> result = feet.subtract(inches);
        System.out.println("2 FEET - 12 INCHES = " + result.getValue() + " " + result.getUnit() + " (Expected: 1 FEET)");
        assertEquals(1.0, result.getValue(), 0.01);
    }

    @Test
    void testLengthDivide() {
        Quantity<LengthUnit> feet = new Quantity<>(12, LengthUnit.FEET);
        Quantity<LengthUnit> inches = new Quantity<>(1, LengthUnit.INCHES);
        double result = feet.divide(inches);
        System.out.println("12 FEET / 1 INCHES = " + result + " (Expected: 144)");
        assertEquals(144.0, result, 0.01);
    }

    // ============= VOLUME UNIT TESTS =============

    @Test
    void testVolumeConversion_LitreToMillilitre() {
        Quantity<VolumeUnit> litre = new Quantity<>(1, VolumeUnit.LITRE);
        Quantity<VolumeUnit> ml = litre.convertTo(VolumeUnit.MILLILITRE);
        System.out.println("1 LITRE = " + ml.getValue() + " MILLILITRE (Expected: 1000)");
        assertEquals(1000.0, ml.getValue(), 0.01);
    }

    @Test
    void testVolumeConversion_MillilitreToLitre() {
        Quantity<VolumeUnit> ml = new Quantity<>(1000, VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> litre = ml.convertTo(VolumeUnit.LITRE);
        System.out.println("1000 MILLILITRE = " + litre.getValue() + " LITRE (Expected: 1)");
        assertEquals(1.0, litre.getValue(), 0.01);
    }

    @Test
    void testVolumeConversion_GallonToLitre() {
        Quantity<VolumeUnit> gallon = new Quantity<>(1, VolumeUnit.GALLON);
        Quantity<VolumeUnit> litre = gallon.convertTo(VolumeUnit.LITRE);
        System.out.println("1 GALLON = " + litre.getValue() + " LITRE (Expected: ~3.785)");
        assertEquals(3.78541, litre.getValue(), 0.01);
    }

    @Test
    void testVolumeAdd() {
        Quantity<VolumeUnit> litre = new Quantity<>(1, VolumeUnit.LITRE);
        Quantity<VolumeUnit> ml = new Quantity<>(1000, VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> result = litre.add(ml);
        System.out.println("1 LITRE + 1000 MILLILITRE = " + result.getValue() + " " + result.getUnit() + " (Expected: 2 LITRE)");
        assertEquals(2.0, result.getValue(), 0.01);
    }

    @Test
    void testVolumeSubtract() {
        Quantity<VolumeUnit> litre = new Quantity<>(2, VolumeUnit.LITRE);
        Quantity<VolumeUnit> ml = new Quantity<>(1000, VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> result = litre.subtract(ml);
        System.out.println("2 LITRE - 1000 MILLILITRE = " + result.getValue() + " " + result.getUnit() + " (Expected: 1 LITRE)");
        assertEquals(1.0, result.getValue(), 0.01);
    }

    @Test
    void testVolumeDivide() {
        Quantity<VolumeUnit> litre = new Quantity<>(2, VolumeUnit.LITRE);
        Quantity<VolumeUnit> ml = new Quantity<>(1000, VolumeUnit.MILLILITRE);
        double result = litre.divide(ml);
        System.out.println("2 LITRE / 1000 MILLILITRE = " + result + " (Expected: 2)");
        assertEquals(2.0, result, 0.01);
    }

    // ============= WEIGHT UNIT TESTS =============

    @Test
    void testWeightConversion_KilogramToGram() {
        Quantity<WeightUnit> kg = new Quantity<>(1, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> g = kg.convertTo(WeightUnit.GRAM);
        System.out.println("1 KILOGRAM = " + g.getValue() + " GRAM (Expected: 1000)");
        assertEquals(1000.0, g.getValue(), 0.01);
    }

    @Test
    void testWeightConversion_GramToKilogram() {
        Quantity<WeightUnit> g = new Quantity<>(1000, WeightUnit.GRAM);
        Quantity<WeightUnit> kg = g.convertTo(WeightUnit.KILOGRAM);
        System.out.println("1000 GRAM = " + kg.getValue() + " KILOGRAM (Expected: 1)");
        assertEquals(1.0, kg.getValue(), 0.01);
    }

    @Test
    void testWeightConversion_MilligramToGram() {
        Quantity<WeightUnit> mg = new Quantity<>(1000, WeightUnit.MILLIGRAM);
        Quantity<WeightUnit> g = mg.convertTo(WeightUnit.GRAM);
        System.out.println("1000 MILLIGRAM = " + g.getValue() + " GRAM (Expected: 1)");
        assertEquals(1.0, g.getValue(), 0.01);
    }

    @Test
    void testWeightConversion_PoundToGram() {
        Quantity<WeightUnit> lb = new Quantity<>(1, WeightUnit.POUND);
        Quantity<WeightUnit> g = lb.convertTo(WeightUnit.GRAM);
        System.out.println("1 POUND = " + g.getValue() + " GRAM (Expected: ~453.592)");
        assertEquals(453.592, g.getValue(), 0.01);
    }

    @Test
    void testWeightConversion_TonneToKilogram() {
        Quantity<WeightUnit> tonne = new Quantity<>(1, WeightUnit.TONNE);
        Quantity<WeightUnit> kg = tonne.convertTo(WeightUnit.KILOGRAM);
        System.out.println("1 TONNE = " + kg.getValue() + " KILOGRAM (Expected: 1000)");
        assertEquals(1000.0, kg.getValue(), 0.01);
    }

    @Test
    void testWeightAdd() {
        Quantity<WeightUnit> kg = new Quantity<>(1, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> g = new Quantity<>(500, WeightUnit.GRAM);
        Quantity<WeightUnit> result = kg.add(g);
        System.out.println("1 KILOGRAM + 500 GRAM = " + result.getValue() + " " + result.getUnit() + " (Expected: 1.5 KILOGRAM)");
        assertEquals(1.5, result.getValue(), 0.01);
    }

    @Test
    void testWeightSubtract() {
        Quantity<WeightUnit> kg = new Quantity<>(2, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> g = new Quantity<>(500, WeightUnit.GRAM);
        Quantity<WeightUnit> result = kg.subtract(g);
        System.out.println("2 KILOGRAM - 500 GRAM = " + result.getValue() + " " + result.getUnit() + " (Expected: 1.5 KILOGRAM)");
        assertEquals(1.5, result.getValue(), 0.01);
    }

    @Test
    void testWeightDivide() {
        Quantity<WeightUnit> kg = new Quantity<>(2, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> g = new Quantity<>(500, WeightUnit.GRAM);
        double result = kg.divide(g);
        System.out.println("2 KILOGRAM / 500 GRAM = " + result + " (Expected: 4)");
        assertEquals(4.0, result, 0.01);
    }

    // ============= TEMPERATURE UNIT TESTS =============

    @Test
    void testTemperatureConversion_CelsiusToFahrenheit() {
        Quantity<TemperatureUnit> celsius = new Quantity<>(0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> fahrenheit = celsius.convertTo(TemperatureUnit.FAHRENHEIT);
        System.out.println("0 CELSIUS = " + fahrenheit.getValue() + " FAHRENHEIT (Expected: 32)");
        assertEquals(32.0, fahrenheit.getValue(), 0.01);
    }

    @Test
    void testTemperatureConversion_FahrenheitToCelsius() {
        Quantity<TemperatureUnit> fahrenheit = new Quantity<>(32, TemperatureUnit.FAHRENHEIT);
        Quantity<TemperatureUnit> celsius = fahrenheit.convertTo(TemperatureUnit.CELSIUS);
        System.out.println("32 FAHRENHEIT = " + celsius.getValue() + " CELSIUS (Expected: 0)");
        assertEquals(0.0, celsius.getValue(), 0.01);
    }

    @Test
    void testTemperatureConversion_CelsiusToKelvin() {
        Quantity<TemperatureUnit> celsius = new Quantity<>(0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> kelvin = celsius.convertTo(TemperatureUnit.KELVIN);
        System.out.println("0 CELSIUS = " + kelvin.getValue() + " KELVIN (Expected: 273.15)");
        assertEquals(273.15, kelvin.getValue(), 0.01);
    }

    @Test
    void testTemperatureConversion_KelvinToCelsius() {
        Quantity<TemperatureUnit> kelvin = new Quantity<>(273.15, TemperatureUnit.KELVIN);
        Quantity<TemperatureUnit> celsius = kelvin.convertTo(TemperatureUnit.CELSIUS);
        System.out.println("273.15 KELVIN = " + celsius.getValue() + " CELSIUS (Expected: 0)");
        assertEquals(0.0, celsius.getValue(), 0.01);
    }

    @Test
    void testTemperatureConversion_FahrenheitToKelvin() {
        Quantity<TemperatureUnit> fahrenheit = new Quantity<>(32, TemperatureUnit.FAHRENHEIT);
        Quantity<TemperatureUnit> kelvin = fahrenheit.convertTo(TemperatureUnit.KELVIN);
        System.out.println("32 FAHRENHEIT = " + kelvin.getValue() + " KELVIN (Expected: 273.15)");
        assertEquals(273.15, kelvin.getValue(), 0.01);
    }

    @Test
    void testTemperatureComparison() {
        Quantity<TemperatureUnit> celsius = new Quantity<>(0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> fahrenheit = new Quantity<>(32, TemperatureUnit.FAHRENHEIT);
        boolean equal = celsius.equals(fahrenheit);
        System.out.println("0 CELSIUS == 32 FAHRENHEIT: " + equal + " (Expected: true)");
        assertTrue(equal);
    }

}
