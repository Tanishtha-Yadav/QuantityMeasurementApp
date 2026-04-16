package com.apps.measurement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConversionResultDTO {
	private String type;
	private String fromUnit;
	private String toUnit;
	private double inputValue;
	private double resultValue;
	private String resultString; // Formatted: "5000 METER"
}
