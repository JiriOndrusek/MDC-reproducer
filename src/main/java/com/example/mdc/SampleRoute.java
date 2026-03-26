package com.example.mdc;

import jakarta.enterprise.context.ApplicationScoped;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.MDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class SampleRoute extends RouteBuilder {

    private static final Logger logger = LoggerFactory.getLogger(SampleRoute.class);

    @Override
    public void configure() throws Exception {
        from("timer:myTimer?repeatCount=1")
            .routeId("test-route")
            .process(exchange -> {
                // Log MDC values to console
                String exchangeId = MDC.get("exchangeId");
                String routeId = MDC.get("routeId");
                String customField = MDC.get("customField");

                logger.info("=== MDC Values ===");
                logger.info("exchangeId: {}", exchangeId);
                logger.info("routeId: {}", routeId);
                logger.info("customField: {}", customField);
                logger.info("==================");
            });
    }
}
