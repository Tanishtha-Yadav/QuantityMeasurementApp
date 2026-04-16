package com.apps.measurement.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.apps.measurement.dto.ConversionHistoryDTO;

@Component
public class UserServiceClientFallback implements UserServiceClient {

	private static final Logger log = LoggerFactory.getLogger(UserServiceClientFallback.class);

	@Override
	public void saveConversionHistory(Long userId, ConversionHistoryDTO history) {
		log.warn("user-service unavailable — history not saved for user {}", userId);
		// Gracefully degrade: conversion still succeeds, history skipped
	}

}
