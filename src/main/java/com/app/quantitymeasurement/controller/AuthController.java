package com.app.quantitymeasurement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.quantitymeasurement.dto.AuthRequest;
import com.app.quantitymeasurement.dto.AuthResponse;
import com.app.quantitymeasurement.dto.ApiResponse;
import com.app.quantitymeasurement.dto.RefreshTokenRequest;
import com.app.quantitymeasurement.dto.UserRegistrationDTO;
import com.app.quantitymeasurement.model.User;
import com.app.quantitymeasurement.security.JwtUtil;
import com.app.quantitymeasurement.service.AuthService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private AuthService service;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@PostMapping("/register")
	public ResponseEntity<ApiResponse<AuthResponse>> register(@Valid @RequestBody UserRegistrationDTO registrationDTO) {
		User user = service.register(registrationDTO);
		String token = jwtUtil.generateToken(user);
		
		AuthResponse.UserInfo userInfo = new AuthResponse.UserInfo(user.getId(), user.getName(), user.getEmail());
		AuthResponse authResponse = new AuthResponse(token, userInfo);
		return ResponseEntity.ok(ApiResponse.success(authResponse));
	}
	
	@PostMapping("/login")
	public ResponseEntity<ApiResponse<AuthResponse>> login(@RequestBody AuthRequest request) {
		User user = service.login(request.getEmail(),request.getPassword());
		String token = jwtUtil.generateToken(user);
		
		AuthResponse.UserInfo userInfo = new AuthResponse.UserInfo(user.getId(), user.getName(), user.getEmail());
		AuthResponse authResponse = new AuthResponse(token, userInfo);
		return ResponseEntity.ok(ApiResponse.success(authResponse));
	}

	@PostMapping("/refresh")
	public ResponseEntity<ApiResponse<?>> refresh(@RequestBody RefreshTokenRequest request) {
		try {
			String token = request.getToken();
			if (jwtUtil.isTokenExpired(token)) {
				return ResponseEntity.status(401).body(ApiResponse.error("TOKEN_EXPIRED", "Token has expired. Please login again."));
			}
			String newToken = jwtUtil.refreshToken(token);
			return ResponseEntity.ok(ApiResponse.success(new AuthResponse(newToken, null)));
		} catch (Exception e) {
			return ResponseEntity.status(401).body(ApiResponse.error("INVALID_TOKEN", "Invalid token"));
		}
	}
}