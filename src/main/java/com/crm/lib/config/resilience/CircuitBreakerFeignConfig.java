package com.crm.lib.config.resilience;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.core.registry.RegistryEventConsumer;
import org.springframework.cloud.openfeign.CircuitBreakerNameResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CircuitBreakerFeignConfig {

    @Bean
    public CircuitBreakerNameResolver  circuitBreakerFeignNameResolver() {
        return new CircuitBreakerFeignNameResolver();
    }

    @Bean
    public RegistryEventConsumer<CircuitBreaker> circuitBreakerLogging() {
        return new CircuitBreakerLogging();
    }
}
