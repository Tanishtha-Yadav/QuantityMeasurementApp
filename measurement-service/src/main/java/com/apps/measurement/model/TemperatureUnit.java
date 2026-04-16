package com.apps.measurement.model;

public enum TemperatureUnit {
	CELSIUS("C"),
	FAHRENHEIT("F"),
	KELVIN("K");

	private final String symbol;

	TemperatureUnit(String symbol) {
		this.symbol = symbol;
	}

	public String getSymbol() {
		return symbol;
	}

	public double convert(double value, TemperatureUnit to) {
		double celsius = toCelsius(value);
		return toTemp(celsius, to);
	}

	private double toCelsius(double value) {
		return switch (this) {
			case CELSIUS -> value;
			case FAHRENHEIT -> (value - 32) * 5.0 / 9.0;
			case KELVIN -> value - 273.15;
		};
	}

	private static double toTemp(double celsius, TemperatureUnit to) {
		return switch (to) {
			case CELSIUS -> celsius;
			case FAHRENHEIT -> (celsius * 9.0 / 5.0) + 32;
			case KELVIN -> celsius + 273.15;
		};
	}
}
