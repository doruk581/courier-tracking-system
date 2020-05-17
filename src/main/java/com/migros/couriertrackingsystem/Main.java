package com.migros.couriertrackingsystem;

import com.migros.couriertrackingsystem.application.CourierTrackingService;
import com.migros.couriertrackingsystem.application.validation.CourierControlValidationService;
import com.migros.couriertrackingsystem.application.validation.ValidationService;
import com.migros.couriertrackingsystem.domain.CourierFactory;
import com.migros.couriertrackingsystem.domain.CourierTrackingRepository;
import com.migros.couriertrackingsystem.domain.DefaultCourierFactory;
import com.migros.couriertrackingsystem.domain.DefaultCourierTrackingService;
import com.migros.couriertrackingsystem.domain.calculate.CalculationService;
import com.migros.couriertrackingsystem.domain.calculate.DefaultCalculationService;
import com.migros.couriertrackingsystem.domain.specification.DefaultSpecificationFactory;
import com.migros.couriertrackingsystem.domain.specification.SpecificationFactory;
import com.migros.couriertrackingsystem.infrastructure.repository.MongoConfiguration;
import com.migros.couriertrackingsystem.infrastructure.repository.MongoCourierTrackingRepository;
import com.migros.couriertrackingsystem.infrastructure.storeservice.StoreService;
import com.migros.couriertrackingsystem.infrastructure.storeservice.StoreServiceGateway;
import com.migros.couriertrackingsystem.interfaces.request.CourierControlRequest;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
public class Main {


    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    ValidationService<CourierControlRequest> getCourierValidationService() {
        return new CourierControlValidationService();
    }

    @Bean
    public MongoClient getMongoClient(MongoConfiguration configuration) {
        final String mongoConnectionString = configuration.getMongoDbConnectionString();
        return MongoClients.create(mongoConnectionString);
    }

    @Bean
    CourierTrackingRepository getCourierTrackingRepository(MongoConfiguration mongoConfiguration, MongoClient mongoClient) {
        return new MongoCourierTrackingRepository(mongoConfiguration, mongoClient);
    }

    @Bean
    CourierFactory getCourierFactory() {
        return new DefaultCourierFactory();
    }

    @Bean
    StoreService getStoreService() {
        return new StoreServiceGateway();
    }

    @Bean
    SpecificationFactory specificationFactory() {
        return new DefaultSpecificationFactory();
    }

    @Bean
    CalculationService getCalculationService() {
        return new DefaultCalculationService();
    }

    @Bean
    CourierTrackingService getCourierTrackingService(CourierTrackingRepository repository,
                                                     StoreService storeService,
                                                     SpecificationFactory specificationFactory,
                                                     CourierFactory courierFactory,
                                                     CalculationService calculationService) {
        return new DefaultCourierTrackingService(repository, storeService, specificationFactory, courierFactory, calculationService);
    }
}
