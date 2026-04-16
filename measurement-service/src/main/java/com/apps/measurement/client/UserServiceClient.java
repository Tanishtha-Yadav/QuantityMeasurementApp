package com.apps.measurement.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.apps.measurement.dto.ConversionHistoryDTO;

@FeignClient(name = "user-service", fallback = UserServiceClientFallback.class)
public interface UserServiceClient {

	@PostMapping("/api/users/{userId}/history")
	void saveConversionHistory(
		@PathVariable("userId") Long userId,
		@RequestBody ConversionHistoryDTO history
	);
}
