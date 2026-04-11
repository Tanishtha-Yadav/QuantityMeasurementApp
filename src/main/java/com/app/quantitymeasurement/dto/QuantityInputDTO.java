package com.app.quantitymeasurement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuantityInputDTO {
	// Original nested objects
	private QuantityDTO thisQuantityDTO;
	private QuantityDTO thatQuantityDTO;
	
	// Flattened format (for easier API usage)
	private double operand1;
	private String operand1Unit;
	private double operand2;
	private String operand2Unit;
	private String operation;
	
	// Helper method to convert flattened format to nested
	public void normalizeDTO() {
		if (this.operand1 > 0 && this.operand1Unit != null) {
			this.thisQuantityDTO = new QuantityDTO(this.operand1, this.operand1Unit);
		}
		if (this.operand2 > 0 && this.operand2Unit != null) {
			this.thatQuantityDTO = new QuantityDTO(this.operand2, this.operand2Unit);
		}
	}
}