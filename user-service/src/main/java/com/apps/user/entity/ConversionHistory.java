package com.apps.user.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "conversion_history")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConversionHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private User user;

	private String type; // LENGTH, WEIGHT, TEMPERATURE, VOLUME
	private String fromUnit;
	private String toUnit;
	private double inputValue;
	private double outputValue;
	private String action; // CONVERT, COMPARE, ADD, SUBTRACT, DIVIDE

	private LocalDateTime createdAt;
}
