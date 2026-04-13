package com.app.quantitymeasurement.core;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Advanced edge case tests to find potential errors
 */
public class QuantityEdgeCasesTest {

    // ============= CENTIMETER & METER ANALYSIS =============
    // The base unit for length appears to be INCHES
    // INCHES(1.0) = base
    // CENTIMETERS(0.393701) means: 1 CM = 0.393701 INCHES (approximately 1/2.54)
    // METERS(39.3701) means: 1 METER = 39.3701 INCHES
    // This is CORRECT!

    @Test
    void testCentimeterToMeterConversion() {
        Quantity<LengthUnit> cm = new Quantity<>(100, LengthUnit.CENTIMETERS);
        Quantity<LengthUnit> m = cm.convertTo(LengthUnit.METERS);
        System.out.println("Expected: 100 cm = 1 m, Got: " + m.getValue() + " " + m.getUnit());
        assertEquals(1.0, m.getValue(), 0.01);
    }

    @Test
    void testMeterToCentimeterConversion() {
        Quantity<LengthUnit> m = new Quantity<>(1, LengthUnit.METERS);
        Quantity<LengthUnit> cm = m.convertTo(LengthUnit.CENTIMETERS);
        System.out.println("Expected: 1 m = 100 cm, Got: " + cm.getValue() + " " + cm.getUnit());
        assertEquals(100.0, cm.getValue(), 0.01);
    }

    @Test
    void testComplexLengthConversion() {
        // 1 meter in yards
        Quantity<LengthUnit> m = new Quantity<>(1, LengthUnit.METERS);
        Quantity<LengthUnit> yards = m.convertTo(LengthUnit.YARDS);
        System.out.println("Expected: 1 m ≈ 1.094 yards, Got: " + yards.getValue() + " " + yards.getUnit());
        assertEquals(1.094, yards.getValue(), 0.01);
    }

    // ============= TEMPERATURE ADDITION (Should these even work?) =============
    // UC14 says temperature arithmetic is "meaningless" and should throw exceptions

    @Test
    void testTemperatureAddition_CelsiusPlusThrowsException() {
        Quantity<TemperatureUnit> c1 = new Quantity<>(10, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> c2 = new Quantity<>(20, TemperatureUnit.CELSIUS);
        
        assertThrows(IllegalArgumentException.class, () -> {
            c1.add(c2);
        });
        System.out.println("CORRECT: Temperature addition throws IllegalArgumentException");
    }

    @Test
    void testTemperatureSubtraction_ThrowsException() {
        Quantity<TemperatureUnit> c1 = new Quantity<>(30, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> c2 = new Quantity<>(10, TemperatureUnit.CELSIUS);
        
        assertThrows(IllegalArgumentException.class, () -> {
            c1.subtract(c2);
        });
        System.out.println("CORRECT: Temperature subtraction throws IllegalArgumentException");
    }

    @Test
    void testTemperatureDivision_ThrowsException() {
        Quantity<TemperatureUnit> c1 = new Quantity<>(40, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> c2 = new Quantity<>(20, TemperatureUnit.CELSIUS);
        
        assertThrows(IllegalArgumentException.class, () -> {
            c1.divide(c2);
        });
        System.out.println("CORRECT: Temperature division throws IllegalArgumentException");
    }

    // ============= CROSS-UNIT ARITHMETIC VALIDATION =============

    @Test
    void testInvalidCrossUnitArithmetic_LengthAndVolume() {
        Quantity<LengthUnit> length = new Quantity<>(1, LengthUnit.FEET);
        Quantity<VolumeUnit> volume = new Quantity<>(1, VolumeUnit.LITRE);
        
        assertThrows(Exception.class, () -> {
            // This should fail - can't add length + volume
            @SuppressWarnings("unchecked")
            Quantity<?>result = length.add((Quantity<LengthUnit>)(Quantity<?>) volume);
        });
    }

    // ============= PRECISION TESTS =============

    @Test
    void testArithmeticPrecision() {
        // Multiple operations should maintain precision
        Quantity<LengthUnit> inches = new Quantity<>(3.33, LengthUnit.INCHES);
        Quantity<LengthUnit> feet = new Quantity<>(0.25, LengthUnit.FEET);
        Quantity<LengthUnit> result = inches.add(feet);
        System.out.println("3.33 IN + 0.25 FT = " + result.getValue() + " IN");
        // Result should be 3.33 + 3 = 6.33 inches
        assertEquals(6.33, result.getValue(), 0.01);
    }

    @Test
    void testVolumeArithmeticWithDifferentUnits() {
        Quantity<VolumeUnit> litre = new Quantity<>(1, VolumeUnit.LITRE);
        Quantity<VolumeUnit> gallon = new Quantity<>(1, VolumeUnit.GALLON);
        Quantity<VolumeUnit> result = litre.add(gallon);
        System.out.println("1 L + 1 GAL = " + result.getValue() + " " + result.getUnit());
        // Expected: ~4.78541 L (1 + 3.78541)
        assertEquals(4.785, result.getValue(), 0.01);
    }

    @Test
    void testWeightArithmeticComplexScenario() {
        Quantity<WeightUnit> tonne = new Quantity<>(2, WeightUnit.TONNE);
        Quantity<WeightUnit> pound = new Quantity<>(1000, WeightUnit.POUND);
        Quantity<WeightUnit> result = tonne.add(pound);
        System.out.println("2 TONNE + 1000 LB = " + result.getValue() + " " + result.getUnit());
        // 2 t = 2,000,000 g
        // 1000 lb = 453,592 g
        // Result in grams = 2,453,592 g = 2.453592 tonnes
        assertEquals(2.453592, result.getValue(), 0.01);
    }

    // ============= NEGATIVE VALUE TESTS =============

    @Test
    void testNegativeLengthValues() {
        Quantity<LengthUnit> negFeet = new Quantity<>(-5, LengthUnit.FEET);
        Quantity<LengthUnit> converted = negFeet.convertTo(LengthUnit.INCHES);
        System.out.println("-5 FEET = " + converted.getValue() + " INCHES (Expected: -60)");
        assertEquals(-60.0, converted.getValue(), 0.01);
    }

    @Test
    void testNegativeArithmetic() {
        Quantity<WeightUnit> kg1 = new Quantity<>(5, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> kg2 = new Quantity<>(10, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> result = kg1.subtract(kg2);
        System.out.println("5 KG - 10 KG = " + result.getValue() + " KG (Expected: -5)");
        assertEquals(-5.0, result.getValue(), 0.01);
    }

    // ============= ROUNDING TESTS =============

    @Test
    void testRoundingInAddition() {
        Quantity<VolumeUnit> ml1 = new Quantity<>(0.333, VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> ml2 = new Quantity<>(0.333, VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> ml3 = new Quantity<>(0.333, VolumeUnit.MILLILITRE);
        
        Quantity<VolumeUnit> result = ml1.add(ml2).add(ml3);
        System.out.println("0.333 + 0.333 + 0.333 ML = " + result.getValue() + " ML");
        System.out.println("With 2-decimal rounding, expected values like ~0.99 or 1.00");
    }

}
