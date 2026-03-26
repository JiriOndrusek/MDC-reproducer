# MDC Reproducer

This is a Quarkus + Camel project demonstrating custom MDC (Mapped Diagnostic Context) values in Camel routes.

## What to expect

When you run `mvn clean compile quarkus:dev`, the application starts and a Camel timer route fires once. You should see console output showing three MDC values: `exchangeId` (Camel's exchange ID), `routeId` (the route name), and `customField` (a custom value "myCustomValue"). These values are automatically set by the custom `CustomMDCUnitOfWork` implementation before the route processes.
