package com.example.mdc;

import io.quarkus.arc.Unremovable;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import org.apache.camel.Exchange;
import org.apache.camel.spi.UnitOfWork;
import org.apache.camel.spi.UnitOfWorkFactory;

@ApplicationScoped
@Unremovable
@Named("unitOfWorkFactory")
public class CustomMDCUnitOfWorkFactory implements UnitOfWorkFactory {

    @Override
    public UnitOfWork createUnitOfWork(Exchange exchange) {
        return new CustomMDCUnitOfWork(exchange);
    }
}
