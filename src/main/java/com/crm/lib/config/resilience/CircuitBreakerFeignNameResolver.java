package com.crm.lib.config.resilience;

import feign.Target;
import org.springframework.cloud.openfeign.CircuitBreakerNameResolver;

import java.lang.reflect.Method;

public class CircuitBreakerFeignNameResolver implements CircuitBreakerNameResolver {

    @Override
    public String resolveCircuitBreakerName(String feignClientName, Target<?> target, Method method) {
        return feignClientName;
    }
}
