package com.example.mdc;

import org.apache.camel.AsyncCallback;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.Route;
import org.apache.camel.impl.engine.DefaultUnitOfWork;
import org.slf4j.MDC;

public class CustomMDCUnitOfWork extends DefaultUnitOfWork {

    public CustomMDCUnitOfWork(Exchange exchange) {
        super(exchange);
    }

    @Override
    public void beforeRoute(Exchange exchange, Route route) {
        super.beforeRoute(exchange, route);

        // Set custom MDC keys when route processing begins
        if (exchange.getExchangeId() != null) {
            MDC.put("exchangeId", exchange.getExchangeId());
        }

        if (route != null && route.getId() != null) {
            MDC.put("routeId", route.getId());
        }

        // Set custom field
        MDC.put("customField", "myCustomValue");
    }

    @Override
    public AsyncCallback beforeProcess(Processor processor, Exchange exchange, AsyncCallback callback) {
        // Ensure MDC is set before each processor
        if (exchange.getExchangeId() != null) {
            MDC.put("exchangeId", exchange.getExchangeId());
        }

        if (exchange.getFromRouteId() != null) {
            MDC.put("routeId", exchange.getFromRouteId());
        }

        MDC.put("customField", "myCustomValue");

        return super.beforeProcess(processor, exchange, callback);
    }

    @Override
    public void done(Exchange exchange) {
        try {
            super.done(exchange);
        } finally {
            // Clear MDC to prevent leakage
            MDC.clear();
        }
    }
}
