package com.apps.measurement.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apps.measurement.client.UserServiceClient;
import com.apps.measurement.dto.ConversionHistoryDTO;
import com.apps.measurement.dto.ConversionInputDTO;
import com.apps.measurement.dto.ConversionResultDTO;
import com.apps.measurement.model.LengthUnit;
import com.apps.measurement.model.TemperatureUnit;
import com.apps.measurement.model.VolumeUnit;
import com.apps.measurement.model.WeightUnit;

@Service
public class ConversionService {

	private static final Logger log = LoggerFactory.getLogger(ConversionService.class);

	@Autowired
	private UserServiceClient userServiceClient;

	public ConversionResultDTO convert(ConversionInputDTO input) {
		log.info("Converting {} from {} to {}", input.getValue(), input.getFromUnit(), input.getToUnit());

		double result = 0;
		String type = input.getType().toUpperCase();

		try {
			switch (type) {
			case "LENGTH":
				result = convertLength(input);
				break;
			case "WEIGHT":
				result = convertWeight(input);
				break;
			case "TEMPERATURE":
				result = convertTemperature(input);
				break;
			case "VOLUME":
				result = convertVolume(input);
				break;
			default:
				throw new IllegalArgumentException("Unknown type: " + type);
			}
		} catch (Exception e) {
			log.error("Conversion error", e);
			throw new RuntimeException("Conversion failed: " + e.getMessage());
		}

		ConversionResultDTO output = new ConversionResultDTO(
			input.getType(),
			input.getFromUnit(),
			input.getToUnit(),
			input.getValue(),
			result,
			String.format("%.6f %s", result, input.getToUnit())
		);

		// Save to history asynchronously (fire and forget with fallback)
		if (input.getUserId() != null) {
			try {
				ConversionHistoryDTO history = new ConversionHistoryDTO(
					input.getType(),
					input.getFromUnit(),
					input.getToUnit(),
					input.getValue(),
					result,
					"CONVERT"
				);
				userServiceClient.saveConversionHistory(input.getUserId(), history);
			} catch (Exception e) {
				log.warn("Failed to save history: {}", e.getMessage());
				// Continue anyway - conversion succeeded
			}
		}

		return output;
	}

	private double convertLength(ConversionInputDTO input) {
		LengthUnit from = LengthUnit.valueOf(input.getFromUnit().toUpperCase());
		LengthUnit to = LengthUnit.valueOf(input.getToUnit().toUpperCase());
		return from.convert(input.getValue(), to);
	}

	private double convertWeight(ConversionInputDTO input) {
		WeightUnit from = WeightUnit.valueOf(input.getFromUnit().toUpperCase());
		WeightUnit to = WeightUnit.valueOf(input.getToUnit().toUpperCase());
		return from.convert(input.getValue(), to);
	}

	private double convertTemperature(ConversionInputDTO input) {
		TemperatureUnit from = TemperatureUnit.valueOf(input.getFromUnit().toUpperCase());
		TemperatureUnit to = TemperatureUnit.valueOf(input.getToUnit().toUpperCase());
		return from.convert(input.getValue(), to);
	}

	private double convertVolume(ConversionInputDTO input) {
		VolumeUnit from = VolumeUnit.valueOf(input.getFromUnit().toUpperCase());
		VolumeUnit to = VolumeUnit.valueOf(input.getToUnit().toUpperCase());
		return from.convert(input.getValue(), to);
	}
}
