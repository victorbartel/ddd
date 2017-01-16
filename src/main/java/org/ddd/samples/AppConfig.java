package org.ddd.samples;

import org.ddd.samples.data.persistence.facades.writable.handlers.EmployeeHandler;
import org.ddd.samples.data.persistence.facades.writable.handlers.InventoryHandler;
import org.ddd.samples.events.EventBusDelegate;
import org.ddd.samples.events.EventBusDelegateImpl;
import org.ddd.samples.repositories.ModelMapperSupplier;
import org.ddd.samples.repositories.ModelMapperSupplierImpl;
import org.h2.server.web.WebServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.orm.jpa.JpaTransactionManager;

@Configuration
@ComponentScan
public class AppConfig {


    @Bean(name = "transactionManager")
    public JpaTransactionManager entityManagerFactory()
    {
        return new JpaTransactionManager();
    }

    @Bean
    @Scope("singleton")
    public EventBusDelegate eventBus(InventoryHandler inventoryHandler,
                                     EmployeeHandler employeeHandler) {

        final EventBusDelegate busDelegate = EventBusDelegateImpl.INSTANCE;

        busDelegate.register(inventoryHandler);
        busDelegate.register(employeeHandler);

        return busDelegate;
    }

    @Bean
    public ServletRegistrationBean h2servletRegistration() {
        ServletRegistrationBean registration = new ServletRegistrationBean(new WebServlet());
        registration.addUrlMappings("/console/*");
        return registration;
    }

    @Bean
    public ModelMapperSupplier modelMapperSupplier() {
        return ModelMapperSupplierImpl.INSTANCE;
    }
}
