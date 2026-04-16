package com.apps.measurement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConversionHistoryDTO {
	private String type; // LENGTH, WEIGHT, TEMPERATURE, VOLUME
	private String fromUnit;
	private String toUnit;
	private double inputValue;
	private double outputValue;
	private String action; // CONVERT, COMPARE, ADD, SUBTRACT, DIVIDE
}
