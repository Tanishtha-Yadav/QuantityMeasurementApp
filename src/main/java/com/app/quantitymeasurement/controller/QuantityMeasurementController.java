package com.app.quantitymeasurement.controller;

import com.app.quantitymeasurement.model.QuantityMeasurementEntity;
import com.app.quantitymeasurement.dto.QuantityDTO;
import com.app.quantitymeasurement.dto.QuantityInputDTO;
import com.app.quantitymeasurement.dto.ApiResponse;
import com.app.quantitymeasurement.service.IQuantityMeasurementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/quantities")
public class QuantityMeasurementController {

    @Autowired
    private IQuantityMeasurementService service;

    @PostMapping("/compare")
    public ResponseEntity<ApiResponse<?>> compare(@RequestBody QuantityInputDTO input) {
        input.normalizeDTO();
        boolean result = service.compare(input.getThisQuantityDTO(), input.getThatQuantityDTO());
        return ResponseEntity.ok(ApiResponse.success(result));
    }

    @PostMapping("/compareWithSign")
    public ResponseEntity<ApiResponse<String>> compareWithSign(@RequestBody QuantityInputDTO input) {
        input.normalizeDTO();
        String result = service.compareWithSign(input.getThisQuantityDTO(), input.getThatQuantityDTO());
        return ResponseEntity.ok(ApiResponse.success(result));
    }

    @PostMapping("/convert/{targetUnit}")
    public ResponseEntity<ApiResponse<QuantityDTO>> convert(@RequestBody QuantityDTO input, @PathVariable String targetUnit) {
        QuantityDTO result = service.convert(input, targetUnit);
        return ResponseEntity.ok(ApiResponse.success(result));
    }

    @GetMapping("/convert/{sourceUnit}/{targetUnit}")
    public ResponseEntity<ApiResponse<QuantityDTO>> convertWithQuery(
            @PathVariable String sourceUnit,
            @PathVariable String targetUnit,
            @RequestParam double value) {
        QuantityDTO input = new QuantityDTO(value, sourceUnit);
        QuantityDTO result = service.convert(input, targetUnit);
        return ResponseEntity.ok(ApiResponse.success(result));
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<QuantityDTO>> add(@RequestBody QuantityInputDTO input) {
        input.normalizeDTO();
        QuantityDTO result = service.add(input.getThisQuantityDTO(), input.getThatQuantityDTO());
        return ResponseEntity.ok(ApiResponse.success(result));
    }

    @PostMapping("/subtract")
    public ResponseEntity<ApiResponse<QuantityDTO>> subtract(@RequestBody QuantityInputDTO input) {
        input.normalizeDTO();
        QuantityDTO result = service.subtract(input.getThisQuantityDTO(), input.getThatQuantityDTO());
        return ResponseEntity.ok(ApiResponse.success(result));
    }

    @PostMapping("/divide")
    public ResponseEntity<ApiResponse<?>> divide(@RequestBody QuantityInputDTO input) {
        input.normalizeDTO();
        if (input.getThatQuantityDTO().getValue() == 0) {
            return ResponseEntity.badRequest().body(ApiResponse.error("DIVISION_BY_ZERO", "Cannot divide by zero"));
        }
        double result = service.divide(input.getThisQuantityDTO(), input.getThatQuantityDTO());
        return ResponseEntity.ok(ApiResponse.success(result));
    }

    @GetMapping("/history")
    public ResponseEntity<ApiResponse<List<QuantityMeasurementEntity>>> getHistory() {
        List<QuantityMeasurementEntity> history = service.getAllMeasurements();
        return ResponseEntity.ok(ApiResponse.success(history));
    }
}