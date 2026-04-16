package com.apps.user.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConversionHistoryDTO {
	private Long id;
	private String type;
	private String fromUnit;
	private String toUnit;
	private double inputValue;
	private double outputValue;
	private String action;
	private LocalDateTime createdAt;
}
