package com.apps.gateway.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@Configuration
public class LoggingFilter implements GlobalFilter {

	private static final Logger log = LoggerFactory.getLogger(LoggingFilter.class);

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, org.springframework.cloud.gateway.filter.GatewayFilterChain chain) {
		ServerHttpRequest req = exchange.getRequest();
		log.info("Incoming: {} {}", req.getMethod(), req.getURI());

		long start = System.currentTimeMillis();

		return chain.filter(exchange).then(Mono.fromRunnable(() -> {
			long duration = System.currentTimeMillis() - start;
			log.info("Completed in {}ms | status: {}", duration, exchange.getResponse().getStatusCode());
		}));
	}

}
