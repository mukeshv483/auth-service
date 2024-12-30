package com.myecom.myapp.metrices;

import io.micrometer.core.instrument.MeterRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MetricesCollector {
    private final MeterRegistry meterRegistry;

    private static  final String EXCEPTION_COUNT="exception.count";
    private static  final String EMPLOYEE_CREATED="employee_count";

    public void incrementExceptionCount(){
        meterRegistry.counter(EXCEPTION_COUNT)
                .increment();
    }

    public void incrementEmployeeCreationCount() {
        meterRegistry.counter(EMPLOYEE_CREATED)
                .increment();
    }
}
