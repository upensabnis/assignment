package com.homes.tabletoprobot.metrics;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.MetricRegistry;

import java.util.concurrent.TimeUnit;

public class MetricsMeters {

    public MetricRegistry registry;
    private ConsoleReporter reporter;
    private boolean metricsEnabled;

    public MetricsMeters(boolean enabled) {
        this.metricsEnabled = enabled;
        registry = new MetricRegistry();

        reporter = ConsoleReporter.forRegistry(registry)
                    .convertRatesTo(TimeUnit.SECONDS)
                    .convertDurationsTo(TimeUnit.MILLISECONDS)
                    .build();
    }

    public MetricRegistry getRegistry() {
        return registry;
    }

    public void reportMetrics() {
        if(metricsEnabled) {
            reporter.report();
        }
    }
}
