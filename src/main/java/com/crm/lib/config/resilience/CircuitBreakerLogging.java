package com.crm.lib.config.resilience;

import io.github.resilience4j.core.registry.EntryAddedEvent;
import io.github.resilience4j.core.registry.EntryRemovedEvent;
import io.github.resilience4j.core.registry.EntryReplacedEvent;
import io.github.resilience4j.core.registry.RegistryEventConsumer;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CircuitBreakerLogging implements RegistryEventConsumer<CircuitBreaker> {

    @Override
    public void onEntryAddedEvent(EntryAddedEvent<CircuitBreaker> event) {

        CircuitBreaker circuitBreaker = event.getAddedEntry();

        CircuitBreaker.EventPublisher eventPublisher = circuitBreaker.getEventPublisher();

        eventPublisher.onStateTransition(transition -> {
            CircuitBreaker.State toState = transition.getStateTransition().getToState();

            log.warn("Circuit breaker '{}' is now '{}' - transitioned({})",
                    transition.getCircuitBreakerName(),toState, transition.getStateTransition());
        });
    }

    @Override
    public void onEntryRemovedEvent(EntryRemovedEvent<CircuitBreaker> event) {
    }

    @Override
    public void onEntryReplacedEvent(EntryReplacedEvent<CircuitBreaker> event) {
    }
}
