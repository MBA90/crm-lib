package com.crm.lib.config.resilience;

import feign.FeignException;

import java.util.function.Predicate;

public class CircuitBreakerFailurePredicate implements Predicate<Throwable> {

    @Override
    public boolean test(Throwable throwable) {
        if (throwable instanceof FeignException feignException) {
            return feignException.status() >= 500 || feignException.status() == -1;
        }

        return false;
    }
}
