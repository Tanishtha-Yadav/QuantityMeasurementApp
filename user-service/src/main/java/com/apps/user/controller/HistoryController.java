package com.apps.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apps.user.dto.ConversionHistoryDTO;
import com.apps.user.dto.ConversionHistoryInputDTO;
import com.apps.user.service.UserService;

@RestController
@RequestMapping("/api/users")
public class HistoryController {

	@Autowired
	private UserService userService;

	@PostMapping("/{userId}/history")
	public ResponseEntity<ConversionHistoryDTO> saveConversionHistory(
		@PathVariable Long userId,
		@RequestBody ConversionHistoryInputDTO historyDTO
	) {
		ConversionHistoryDTO saved = userService.saveConversionHistory(userId, historyDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(saved);
	}

	@GetMapping("/{userId}/history")
	public ResponseEntity<List<ConversionHistoryDTO>> getConversionHistory(@PathVariable Long userId) {
		List<ConversionHistoryDTO> history = userService.getUserConversionHistory(userId);
		return ResponseEntity.ok(history);
	}

	@GetMapping("/{userId}/history/type/{type}")
	public ResponseEntity<List<ConversionHistoryDTO>> getConversionHistoryByType(
		@PathVariable Long userId,
		@PathVariable String type
	) {
		List<ConversionHistoryDTO> history = userService.getUserConversionHistoryByType(userId, type);
		return ResponseEntity.ok(history);
	}

	@DeleteMapping("/history/{historyId}")
	public ResponseEntity<Void> deleteConversionHistory(@PathVariable Long historyId) {
		userService.deleteConversionHistory(historyId);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}
