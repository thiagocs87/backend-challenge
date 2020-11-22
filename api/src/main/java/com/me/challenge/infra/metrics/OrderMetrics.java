package com.me.challenge.infra.metrics;

import io.micrometer.core.instrument.MeterRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderMetrics implements Metrics {

    private final MeterRegistry meterRegistry;

    public void increment(final String metric) {
        System.out.println(metric);
        this.meterRegistry.counter(metric).increment();
    }
}
