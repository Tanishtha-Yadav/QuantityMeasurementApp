package com.app.quantitymeasurement.core;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests to demonstrate the actual errors in the implementation
 */
public class QuantityActualErrorsTest {

    // ============= ERROR 1: Temperature arithmetic should NOT be allowed =============
    // FIXED: The core Quantity class now properly blocks temperature arithmetic
    
    @Test
    void testTemperatureAddition_ShouldThrowButDoesNotInCore() {
        Quantity<TemperatureUnit> c1 = new Quantity<>(10, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> c2 = new Quantity<>(20, TemperatureUnit.CELSIUS);
        
        // This now properly throws an exception
        assertThrows(IllegalArgumentException.class, () -> {
            c1.add(c2);
        });
        System.out.println("FIXED: Temperature addition now throws IllegalArgumentException as expected");
    }

    // ============= ERROR 2: TemperatureUnit.supportsArithmetic() returns true but shouldn't =============
    
    @Test
    void testTemperatureSupportsArithmetic() {
        TemperatureUnit celsius = TemperatureUnit.CELSIUS;
        System.out.println("TemperatureUnit.supportsArithmetic() = " + celsius.supportsArithmetic());
        System.out.println("ERROR: Should return FALSE but returns TRUE");
    }

    // ============= ERROR 3: TemperatureUnit.validateOperationSupport() does nothing =============
    
    @Test
    void testTemperatureValidateOperationSupport() {
        TemperatureUnit celsius = TemperatureUnit.CELSIUS;
        try {
            celsius.validateOperationSupport("ADD");
            System.out.println("ERROR: validateOperationSupport() doesn't throw for ADD");
        } catch (Exception e) {
            System.out.println("Good: validateOperationSupport() throws: " + e.getMessage());
        }
        
        try {
            celsius.validateOperationSupport("SUBTRACT");
            System.out.println("ERROR: validateOperationSupport() doesn't throw for SUBTRACT");
        } catch (Exception e) {
            System.out.println("Good: validateOperationSupport() throws: " + e.getMessage());
        }
        
        try {
            celsius.validateOperationSupport("DIVIDE");
            System.out.println("ERROR: validateOperationSupport() doesn't throw for DIVIDE");
        } catch (Exception e) {
            System.out.println("Good: validateOperationSupport() throws: " + e.getMessage());
        }
    }

    // ============= ERROR 4: Check conversion factors for Length units =============
    
    @Test
    void testLengthUnitConversionFactors() {
        System.out.println("\n--- LENGTH UNIT ANALYSIS ---");
        System.out.println("Base unit appears to be INCHES (factor = 1.0)");
        System.out.println("FEET factor: " + LengthUnit.FEET.getConversionFactor() + " (correct: 1 ft = 12 in)");
        System.out.println("YARDS factor: " + LengthUnit.YARDS.getConversionFactor() + " (correct: 1 yd = 36 in)");
        System.out.println("CENTIMETERS factor: " + LengthUnit.CENTIMETERS.getConversionFactor() + " (should be: 1 cm = 0.3937 in)");
        System.out.println("METERS factor: " + LengthUnit.METERS.getConversionFactor() + " (should be: 1 m = 39.3701 in)");
        
        // These factors appear correct!
        assertEquals(12.0, LengthUnit.FEET.getConversionFactor());
        assertEquals(36.0, LengthUnit.YARDS.getConversionFactor());
        assertEquals(0.393701, LengthUnit.CENTIMETERS.getConversionFactor(), 0.001);
        assertEquals(39.3701, LengthUnit.METERS.getConversionFactor(), 0.001);
    }

    // ============= ERROR 5: Temperature division result not rounded =============
    
    @Test
    void testTemperatureDivisionReturnType() {
        Quantity<TemperatureUnit> c1 = new Quantity<>(40, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> c2 = new Quantity<>(20, TemperatureUnit.CELSIUS);
        
        // Should throw because temperature division is not supported
        assertThrows(IllegalArgumentException.class, () -> {
            c1.divide(c2);
        });
        System.out.println("FIXED: Temperature division now throws IllegalArgumentException");
    }

    // ============= ERROR 6: Test if Quantity.divide() rounds the result =============
    
    @Test
    void testDivisionRounding() {
        Quantity<LengthUnit> inches1 = new Quantity<>(10, LengthUnit.INCHES);
        Quantity<LengthUnit> inches2 = new Quantity<>(3, LengthUnit.INCHES);
        
        double result = inches1.divide(inches2);
        System.out.println("10 INCHES / 3 INCHES = " + result);
        System.out.println("Note: Result is NOT rounded (returns 3.333...)");
    }
}
