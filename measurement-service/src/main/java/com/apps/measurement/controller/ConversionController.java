package com.apps.measurement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apps.measurement.dto.ConversionInputDTO;
import com.apps.measurement.dto.ConversionResultDTO;
import com.apps.measurement.service.ConversionService;

@RestController
@RequestMapping("/api/convert")
public class ConversionController {

	@Autowired
	private ConversionService conversionService;

	@PostMapping
	public ResponseEntity<ConversionResultDTO> convert(@RequestBody ConversionInputDTO input) {
		ConversionResultDTO result = conversionService.convert(input);
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}
}
